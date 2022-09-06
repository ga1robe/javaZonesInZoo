package com.github.ga1robe.zonesinzoo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.ga1robe.zonesinzoo.model.AnimalRecord;
import com.github.ga1robe.zonesinzoo.model.ZoneRecord;
import com.github.ga1robe.zonesinzoo.service.ZooService;

@RestController
public class ZooRestController {
    @Autowired
    ZooService zooService;

    @GetMapping("/zones")
    List<ZoneRecord> zones() {
        return zooService.getZones();
    }

    @PostMapping("/zones")
    ZoneRecord newZone(@Valid @RequestBody ZoneRecord zone) {
        return zooService.addZone(zone);
    }

    @GetMapping("/animals")
    List<AnimalRecord> animals(@RequestParam(required = false) String name,
            @RequestParam(required = false) String zoneName) {
        return this.zooService.getAnimals(name, zoneName);
    }

    @PostMapping("/animals")
    AnimalRecord newAnimal(@Valid @RequestBody AnimalRecord animal) {
        return zooService.addAnimal(animal);
    }

    @GetMapping("/report/zoneFeeds")
    Map<ZoneRecord,Integer> reportZoneFeeds() {
        return this.zooService.calcZoneFeeds();
    }

    @GetMapping("/report/zoneMaxFeed")
    Optional<ZoneRecord> reportZoneMaxFeed() {
        return this.zooService.calcZoneWithMaxFeed();
    }

    @GetMapping("/report/animalCount")
    Optional<ZoneRecord> reportZoneMinAnimals() {
        return this.zooService.calcZoneWithMinAnimals();
    }
}
