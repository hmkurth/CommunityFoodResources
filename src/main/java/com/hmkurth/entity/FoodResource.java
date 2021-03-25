package com.hmkurth.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Column(name = "type_id")
    @OneToOne
    @PrimaryKeyJoinColumn
    private Type type_id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ResourceOwner owner;
    private String description;
    @OneToOne
    @PrimaryKeyJoinColumn
    @Column(name = "location_id")
    private Location location;//fk to location
    @OneToOne
    @PrimaryKeyJoinColumn
    @Column(name="contact_id")//fk to contact
    private Contact contactId;
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
    @Column(name = "delivery_desc")//if delivery is offered, description of services
    private boolean deliveryDescription;
@Column(name="dietary_considerations")
    private String dietaryConsiderations;
}



