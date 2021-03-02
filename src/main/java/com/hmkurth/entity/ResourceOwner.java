package com.hmkurth.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * A class to represent an owner of a resource, a company or program/entity.
 *
 * @author hmkurth
 */
@Entity(name = "ResourceOwner")
@Table(name = "resource_owners")//case sensitive
public class ResourceOwner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;
    @Column(name="org_name")  //don't need if names are the same
    private String name;
    @Column(name="contact_id")//fk to contact
    private Contact contactId;
    @Column(name="resource_id")//fk to resource
    private Set<FoodResource> resourceId;//the services/resources offered
    @Column(name="location_id")//fk to location
    private Location locationId;
    private String website;
    @Column(name="types_offered")
    //private Set<FoodResourceType> types;//set of types of resources offered, goes to types


    private String email;
    private String phone;


    }
