package com.hmkurth.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent a location.
 *
 * @author hmkurth
 */
@Data

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
    @OneToOne
    @PrimaryKeyJoinColumn
    @Column(name="resource_id")
    private FoodResource resourceId;


}
