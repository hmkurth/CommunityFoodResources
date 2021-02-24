package com.hmkurth.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a location.
 *
 * @author hmkurth
 */
@Entity(name = "Location")
@Table(name = "location")//case sensitive
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;
    @Column(name="name_description")  //don't need if names are the same
    private String nameDesc;
    @Column(name="streetaddr_or_intersection")
    private String streetAddressOrIntersection;
    private String city;
    private String state;
    private String zip;
    @Column(name="bus_info")
    private String busInfo;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "resource_id",
            foreignKey = @ForeignKey(name = "id"))
    private FoodResource resourceId;



    /**
     * Instantiates a new Location.
     */
    public Location() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name desc.
     *
     * @return the name desc
     */
    public String getNameDesc() {
        return nameDesc;
    }

    /**
     * Sets name desc.
     *
     * @param nameDesc the name desc
     */
    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }

    /**
     * Gets street address or intersection.
     *
     * @return the street address or intersection
     */
    public String getStreetAddressOrIntersection() {
        return streetAddressOrIntersection;
    }

    /**
     * Sets street address or intersection.
     *
     * @param streetAddressOrIntersection the street address or intersection
     */
    public void setStreetAddressOrIntersection(String streetAddressOrIntersection) {
        this.streetAddressOrIntersection = streetAddressOrIntersection;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets bus info.
     *
     * @return the bus info
     */
    public String getBusInfo() {
        return busInfo;
    }

    /**
     * Sets bus info.
     *
     * @param busInfo the bus info
     */
    public void setBusInfo(String busInfo) {
        this.busInfo = busInfo;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
