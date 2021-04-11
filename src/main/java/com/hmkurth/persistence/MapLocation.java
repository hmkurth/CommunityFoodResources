package com.hmkurth.persistence;

import lombok.Data;

@Data
public class MapLocation {
    private Double lat;
    private Double Long;
    private int id;

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }
}
