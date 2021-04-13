package com.hmkurth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data

@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "MapLocation")
public class MapLocation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;
    @NonNull
    @JsonProperty("lat")
    private Double lat;
    @NonNull
    @JsonProperty("lng")
    private Double lng;

    @JsonProperty("name")
    private String name;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "MapLocation{" +
                "id=" + id +
                ", lat=" + lat +
                ", Long=" + lng +
                ", name='" + name + '\'' +
                '}';
    }
}
