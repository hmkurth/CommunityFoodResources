package com.hmkurth.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * A class to represent a food resource, such as a pantry or a meal.
 *
 * @author hmkurth
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "FoodResource")
@Table(name = "food_resources")//case sensitive
public class FoodResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @NonNull
    @Column(name = "resource_name")
    private String name;
    @NonNull
    //@Column(name = "type_id")
   //not sure if i need the additional info with the fetch, found in tutorial!!Unknown mappedBy in: com.hmkurth.entity.FoodResource.type_id, referenced property unknown: com.hmkurth.entity.Type.food_resources
    @OneToOne//(fetch = FetchType.LAZY, mappedBy = "food_resources", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Type type_id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ResourceOwner owner;

    private String description;
    @OneToOne
    @PrimaryKeyJoinColumn
   // @Column(name = "location_id")
    private Location location;//fk to location
    @OneToOne
    @PrimaryKeyJoinColumn
   // @Column(name="contact_id")//fk to contact, org.hibernate.AnnotationException: @Column(s) not allowed on a @OneToOne property: com.hmkurth.entity.FoodResource.contactId
    private Contact contactId;
    @Column(name = "comments")
    private String comments;
    @Column(name = "service_area")
    private String serviceArea;//the city/county/area served
    private String website;
    @Column(name = "documentation_needed")
    @NonNull
    private String documentation;

    @Column(name = "day_of_week_offered")
    private String daysOfWeek;  //should I make this an array list??how does that work with the DB???
    @Column(name = "delivery_offered")
    private boolean deliveryOffered;
    @Column(name = "delivery_desc")//if delivery is offered, description of services
    private boolean deliveryDescription;
@Column(name="dietary_considerations")
    private String dietaryConsiderations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodResource)) return false;
        FoodResource that = (FoodResource) o;
        return id == that.id && deliveryOffered == that.deliveryOffered && deliveryDescription == that.deliveryDescription && name.equals(that.name) && Objects.equals(type_id, that.type_id) && Objects.equals(owner, that.owner) && description.equals(that.description) && Objects.equals(location, that.location) && Objects.equals(contactId, that.contactId) && Objects.equals(comments, that.comments) && Objects.equals(serviceArea, that.serviceArea) && Objects.equals(website, that.website) && documentation.equals(that.documentation) && Objects.equals(daysOfWeek, that.daysOfWeek) && Objects.equals(dietaryConsiderations, that.dietaryConsiderations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type_id, owner, description, location, contactId, comments, serviceArea, website, documentation, daysOfWeek, deliveryOffered, deliveryDescription, dietaryConsiderations);
    }
}



