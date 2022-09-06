package com.github.ga1robe.zonesinzoo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneRecord {
    @NotEmpty(message = "Please provide name of Zone")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
