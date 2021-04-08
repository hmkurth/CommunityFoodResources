package com.hmkurth.entity;


import lombok.*;
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
    @OneToOne//(fetch = FetchType.LAZY, mappedBy = "food_resources", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Type type_id;//fk to resource_type
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne
    @PrimaryKeyJoinColumn
    private ResourceOwner owner;
    @NonNull
    private String description;
    @OneToOne
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
   // @Column(name = "location_id")
    private Location location;//fk to location
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contactId;//fk to contact
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
    private String deliveryDescription;
    @Column(name="dietary_considerations")
    private String dietaryConsiderations;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodResource)) return false;
        FoodResource that = (FoodResource) o;
        return getId() == that.getId() && isDeliveryOffered() == that.isDeliveryOffered() && getName().equals(that.getName()) && Objects.equals(getType_id(), that.getType_id()) && getDescription().equals(that.getDescription()) && Objects.equals(getComments(), that.getComments()) && getServiceArea().equals(that.getServiceArea()) && Objects.equals(getWebsite(), that.getWebsite()) && getDocumentation().equals(that.getDocumentation()) && getDaysOfWeek().equals(that.getDaysOfWeek()) && Objects.equals(getDeliveryDescription(), that.getDeliveryDescription()) && getDietaryConsiderations().equals(that.getDietaryConsiderations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType_id(), getDescription(), getComments(), getServiceArea(), getWebsite(), getDocumentation(), getDaysOfWeek(), isDeliveryOffered(), getDeliveryDescription(), getDietaryConsiderations());
    }
}



