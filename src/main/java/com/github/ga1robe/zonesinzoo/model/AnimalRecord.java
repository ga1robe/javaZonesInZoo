package com.github.ga1robe.zonesinzoo.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AnimalRecord {
    @NotEmpty(message = "Name field is missing")
    @NonNull
    private String name;
    @NonNull
    @NotNull(message = "Type is missing")
    private Type type;
    @NonNull
    @NotEmpty(message = "Zone name is missing")
    private String zoneName;
    @JsonIgnore
    private ZoneRecord zone;

    @JsonSetter("type")
    public void setType(String type) {
        this.type = Type.getEnum(type);
    }

    @JsonGetter("feed")
    public int getFeed() {
        switch (type) {
            case ELEPHANT:
                return 20;
            case LION:
                return 11;
            case RABBIT:
                return 4;
            default:
                return 0;
        }
    }

    public enum Type {
        ELEPHANT("słoń"), LION("lew"), RABBIT("królik");
        private String value;
        Type(String value) {
            this.value = value;
        }
        public static Type getEnum(String name) {
            for (Type type: Type.values()) {
                if (type.value.equalsIgnoreCase(name) || type.toString().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }
    }
}
