package com.github.ga1robe.zonesinzoo.service;

import com.github.ga1robe.zonesinzoo.model.AnimalRecord;
import com.github.ga1robe.zonesinzoo.model.ZoneRecord;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ZooServiceTest {

    @Autowired
    ZooService zooService;
    private List<ZoneRecord> zones = new ArrayList<ZoneRecord>();
    private List<AnimalRecord> animals = new ArrayList<AnimalRecord>();

    @Test
    void addSampleData() {
        // given
        // when
        zones.add(new ZoneRecord("Zone1"));
        zones.add(new ZoneRecord("Zone2"));

        animals.add(new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1"));
        animals.add(new AnimalRecord("Bugs", AnimalRecord.Type.RABBIT, "Zone1"));
        animals.add(new AnimalRecord("Król", AnimalRecord.Type.LION, "Zone2"));

        // then
        assertEquals(2, zones.size());
        assertEquals(3, animals.size());
    }

    @Test
    void addZone() {
        // given
        // when
        zones.add(new ZoneRecord("Zone1"));

        // then
        assertEquals(1, zones.size());
        assertEquals("Zone1", zones.get(0).getName());
    }

    @Test
    void addAnimal() {
        // given
        // when
        animals.add(new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1"));

        // then
        assertEquals(1, animals.size());
        assertEquals("Trombalski", animals.get(0).getName());
    }

    @Test
    void getAnimals() {
        // given
        animals.add(new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1"));
        animals.add(new AnimalRecord("Bugs", AnimalRecord.Type.RABBIT, "Zone1"));
        animals.add(new AnimalRecord("Król", AnimalRecord.Type.LION, "Zone2"));
        Stream<AnimalRecord> stream = zooService.getAnimals().stream();
        // when
        String name = "Trombalski";
        String zoneName = "Zone1";
        stream = stream.filter(animal -> name.equals(animal.getName()));
        stream = stream.filter(animal -> zoneName.equals(animal.getZone().getName()));

        // then
        assertEquals(1, stream.collect(Collectors.toList()).size());
    }

    @Test
    void calcZoneFeeds() {
        // given
        // when
        zones.add(new ZoneRecord("Zone1"));
        zones.add(new ZoneRecord("Zone2"));

        animals.add(new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1"));
        animals.add(new AnimalRecord("Bugs", AnimalRecord.Type.RABBIT, "Zone1"));
        animals.add(new AnimalRecord("Król", AnimalRecord.Type.LION, "Zone2"));
        // when
        Map<ZoneRecord,Integer> zoneFeeds = zooService.getAnimals().stream().collect(Collectors.groupingBy(AnimalRecord::getZone, Collectors.summingInt(AnimalRecord::getFeed)));
        // then
        assertEquals(true, zoneFeeds.containsValue(11));
    }

    @Test
    void calcZoneAnimalNumber() {
        // given
        // when
        zones.add(new ZoneRecord("Zone1"));
        zones.add(new ZoneRecord("Zone2"));

        animals.add(new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1"));
        animals.add(new AnimalRecord("Bugs", AnimalRecord.Type.RABBIT, "Zone1"));
        animals.add(new AnimalRecord("Król", AnimalRecord.Type.LION, "Zone2"));
        // when
        Map<ZoneRecord,Long> zoneAnimalNumber = zooService.getAnimals().stream().collect(Collectors.groupingBy(AnimalRecord::getZone, Collectors.counting()));
        // then
        assertEquals(false, zoneAnimalNumber.containsValue(2));
    }

    @Test
    void calcZoneWithMaxFeed() {
        // given
        zones.add(new ZoneRecord("Zone1"));
        zones.add(new ZoneRecord("Zone2"));
        animals.add(new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1"));
        animals.add(new AnimalRecord("Bugs", AnimalRecord.Type.RABBIT, "Zone1"));
        animals.add(new AnimalRecord("Król", AnimalRecord.Type.LION, "Zone2"));
        // when
        Optional<Map.Entry<ZoneRecord, Integer>> maxEntry = zooService.calcZoneFeeds().entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
        // then
        assertEquals(true, maxEntry.isPresent());
        assertEquals(Optional.of(24), Optional.of(maxEntry.get().getValue()));
    }

    @Test
    void calcZoneWithMinAnimals() {
        // given
        zones.add(new ZoneRecord("Zone1"));
        zones.add(new ZoneRecord("Zone2"));
        animals.add(new AnimalRecord("Trombalski", AnimalRecord.Type.ELEPHANT, "Zone1"));
        animals.add(new AnimalRecord("Bugs", AnimalRecord.Type.RABBIT, "Zone1"));
        animals.add(new AnimalRecord("Król", AnimalRecord.Type.LION, "Zone2"));
        // when
        Optional<Map.Entry<ZoneRecord, Long>> minEntry = zooService.calcZoneAnimalNumber().entrySet().stream().min(Comparator.comparing(Map.Entry::getValue));
        // then
        assertEquals(true, minEntry.isPresent());
        assertEquals(false, Optional.of(minEntry.get().getValue()).equals(Optional.of(1)));
    }
}