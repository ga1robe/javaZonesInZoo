package com.github.ga1robe.zonesinzoo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.github.ga1robe.zonesinzoo.model.ZoneRecord;
import com.github.ga1robe.zonesinzoo.service.ZooService;

@SpringBootTest
class ZonesinzooApplicationTests {
	@Autowired
	private ZooService service;

	@Test
	void contextLoads() {
	}

	@Test
	void testAddZone() {
		service.clear();
		Assert.isTrue(service.getZones().size() == 0,"Clear not done");
		ZoneRecord z1 = new ZoneRecord("Zone1");
		service.addZone(z1);
		Assert.isTrue(service.getZones().size() == 1,"Zone not added");
	}

}
