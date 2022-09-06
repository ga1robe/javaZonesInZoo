package com.github.ga1robe.zonesinzoo.controller;

import com.github.ga1robe.zonesinzoo.model.AnimalRecord;
import com.github.ga1robe.zonesinzoo.model.ZoneRecord;
import com.github.ga1robe.zonesinzoo.service.ZooService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("name")
public class ZooController {
    @Autowired
    ZooService zooService;

    @GetMapping("/")
    public RedirectView index(ModelMap model){
        return new RedirectView("/page_zones");
    }

    @RequestMapping(value="/page_zones", method = RequestMethod.GET)
    String showZones(ModelMap model) {
        model.put(
                "zones", this.zooService.getZones()
        );
        return "page_zones";
    }

    @RequestMapping(value="/page_zones", method = RequestMethod.POST)
    String addZone(ModelMap model, @RequestParam String zone) {
        try {
            if (zone.isEmpty()) {
                throw new IllegalArgumentException("Missing zone parameter");
            }
            model.put("success","Dane wprowadzone");
        } catch (NumberFormatException e) {
            model.put("error","Nieprawidłowe dane strefy");
            return "page_zones";
        } catch (Exception e) {
            model.put("error","Bład wprowadzania danych, podaj nazwę strefy");
            return "page_zones";
        }
        return "redirect:page_zones";
    }

    @RequestMapping(value="/page_animals", method = RequestMethod.GET)
    String showAnimals(ModelMap model, @RequestParam(required = false) String name,
                               @RequestParam(required = false) String zoneName) {
        model.put(
                "animals", this.zooService.getAnimals(name, zoneName)
        );
        return "page_animals";
    }

    @RequestMapping(value="/page_animals", method = RequestMethod.POST)
    String addAnimal(ModelMap model, @RequestParam String type, @RequestParam String name, @RequestParam String zone) {
        try {
            if (type.isEmpty()) {
                throw new IllegalArgumentException("Missing animal parameter");
            }
            model.put("success","Dane wprowadzone");
        } catch (NumberFormatException e) {
            model.put("error","Nieprawidłowe dane zwierzęcia");
            return "page_animals";
        } catch (Exception e) {
            model.put("error","Bład wprowadzania danych, podaj imię, typ zwierzęcia oraz sprefę");
            return "page_animals";
        }
        return "redirect:page_animals";
    }

    @RequestMapping(value="/page_report_zoneFeeds", method = RequestMethod.GET)
    String showReportZoneFeeds(ModelMap model) {
        model.put(
                "zoneFeeds", this.zooService.calcZoneFeeds()
        );
        return "page_report_zoneFeeds";
    }

    @RequestMapping(value="/page_report_zoneMaxFeed", method = RequestMethod.GET)
    String showReportZoneMaxFeed(ModelMap model) {
        model.put(
                "zoneMaxFeed", this.zooService.calcZoneWithMaxFeed().get()
        );
        return "page_report_zoneMaxFeed";
    }

    @RequestMapping(value="/page_report_minAnimalCount", method = RequestMethod.GET)
    String showReportZoneMinAnimals(ModelMap model) {
        model.put(
                "zoneMinAnimalCount", this.zooService.calcZoneWithMinAnimals().get()
        );
        return "page_report_minAnimalCount";
    }
}
