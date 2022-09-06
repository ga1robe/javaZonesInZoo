package com.github.ga1robe.zonesinzoo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.github.ga1robe.zonesinzoo.model.AnimalRecord;
import com.github.ga1robe.zonesinzoo.model.ZoneRecord;

import lombok.Getter;

@Service
@Getter
public class ZooService {
    private List<ZoneRecord> zones = new ArrayList<ZoneRecord>();
    private List<AnimalRecord> animals = new ArrayList<AnimalRecord>();

    @PostConstruct
    public void addSampleData() {
        ZoneRecord zone1 = new ZoneRecord("Zone1");
        this.addZone(zone1);
        
        ZoneRecord zone2 = new ZoneRecord("Zone2");
        this.addZone(zone2);

        AnimalRecord animal1 = new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1");
        this.addAnimal(animal1);
        AnimalRecord animal2 = new AnimalRecord("Bugs", AnimalRecord.Type.RABBIT, "Zone1");
        this.addAnimal(animal2);
        AnimalRecord animal3 = new AnimalRecord("Król", AnimalRecord.Type.LION, "Zone2");
        this.addAnimal(animal3);
    }

    public ZoneRecord addZone(ZoneRecord zone) {
        //check if same zone exists
        if (this.zones.stream().anyMatch(i -> i.getName().equals(zone.getName()))) {
            throw new ValidationException("Zone with this name already exists");
        }
        this.zones.add(zone);
        return zone;
    }

    public AnimalRecord addAnimal(AnimalRecord animal) {
        //fill zone
        Optional<ZoneRecord> zone = this.zones.stream().filter(i -> i.getName().equals(animal.getZoneName())).findFirst();
        if (!zone.isPresent()) throw new ValidationException("Zone is not defined");
        animal.setZone(zone.get());
        this.animals.add(animal);
        return animal;
    }

    public List<AnimalRecord> getAnimals(String name,String zoneName) {
        Stream<AnimalRecord> stream = this.getAnimals().stream();
        if (name != null) {
            stream = stream.filter(animal -> name.equals(animal.getName()));
        }
        if (zoneName != null) {
            stream = stream.filter(animal -> zoneName.equals(animal.getZone().getName()));
        }
        return stream.collect(Collectors.toList());
    }

    public Map<ZoneRecord,Integer> calcZoneFeeds() {
        //find feeds per zone
        return this.getAnimals().stream().collect(Collectors.groupingBy(AnimalRecord::getZone, Collectors.summingInt(AnimalRecord::getFeed)));
    }

    public Map<ZoneRecord,Long> calcZoneAnimalNumber() {
        //find animal number per zone
        return this.getAnimals().stream().collect(Collectors.groupingBy(AnimalRecord::getZone, Collectors.counting()));
    }

    public Optional<ZoneRecord> calcZoneWithMaxFeed() {
        //find record with max feed
        Optional<Entry<ZoneRecord, Integer>> maxEntry = this.calcZoneFeeds().entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
        return Optional.of(maxEntry.isPresent() ? maxEntry.get().getKey() : null);
    }

    public Optional<ZoneRecord> calcZoneWithMinAnimals() {
        //find record with min animals
        Optional<Entry<ZoneRecord, Long>> minEntry = this.calcZoneAnimalNumber().entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));
        return Optional.of(minEntry.isPresent() ? minEntry.get().getKey() : null);
    }

    public void clear() {
        this.animals.clear();
        this.zones.clear();
    }
}
