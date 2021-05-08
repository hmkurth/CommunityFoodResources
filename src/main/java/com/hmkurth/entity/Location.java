package com.hmkurth.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.GeoPointBinding;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.Latitude;
import org.hibernate.search.mapper.pojo.bridge.builtin.annotation.Longitude;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.util.Objects;

/**
 * A class to represent a location.
 *
 * @author hmkurth
 */
@Data
@Indexed
@GeoPointBinding(fieldName = "placeOfBirth")
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Location")
@Table(name = "location")//case sensitive
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;
    @NonNull
    @Column(name="name_description")  //don't need if names are the same
    private String nameDesc;
    @NonNull
    @Column(name="streetaddr_or_intersection")
    private String streetAddressOrIntersection;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private String zip;
    @Column(name="bus_info")
    private String busInfo;
    private String comments;
    @Longitude
    private Double lng;
    @Latitude
    private Double lat;
    @OneToOne
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    //@Column(name="resource_id")
    private FoodResource resourceId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getId() == location.getId() && getNameDesc().equals(location.getNameDesc()) && getStreetAddressOrIntersection().equals(location.getStreetAddressOrIntersection()) && getCity().equals(location.getCity()) && getState().equals(location.getState()) && getZip().equals(location.getZip()) && Objects.equals(getBusInfo(), location.getBusInfo()) && Objects.equals(getComments(), location.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameDesc(), getStreetAddressOrIntersection(), getCity(), getState(), getZip(), getBusInfo(), getComments());
    }
}
