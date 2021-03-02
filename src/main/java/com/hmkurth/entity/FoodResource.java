package com.hmkurth.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a food resource, such as a pantry or a meal.
 *
 * @author hmkurth
 */
@Entity(name = "FoodResource")
@Table(name = "food_resources")//case sensitive
public class FoodResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name = "resource_name")
    private String name;
    @Column(name = "resource_type")
    //private Type resourceType;//fk to type table
    //@Column(name = "resource_owner")//fk to owners
    private ResourceOwner owner;
    private String description;
    @Column(name = "location_id")
    private Location location;//fk to location
    @Column(name = "contact_id")
    private Contact contact;//fk to contact
    @Column(name = "comments")
    private String comments;
    @Column(name = "service_area")
    private String serviceArea;//the city/county/area served
    @Column(name = "service_area")

    private String website;
    @Column(name = "documentation_needed")
    private String documentation;
    @Column(name = "day_of_week_offered")
    private String daysOfWeek;  //should I make this an array list??how does that work with the DB???
    @Column(name = "delivery_offered")
    private boolean deliveryOffered;
    @Column(name = "delivery_id")//if boolean set to true
    //private Delivery deliveryId;
    //@Column(name = "dietary_considerations")
    private String dietaryConsiderations;
}



