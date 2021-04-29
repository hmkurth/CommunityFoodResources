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

    public FoodResource(int id, @NonNull String name, @NonNull Type type_id, ResourceOwner owner, @NonNull String description, Location location,String documentation, boolean deliveryOffered) {
        this.id = id;
        this.name = name;
        this.type_id = type_id;
        this.owner = owner;
        this.description = description;
        this.location = location;
        this.documentation = documentation;
        this.deliveryOffered = deliveryOffered;
    }

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
        return getId() == that.getId() && isDeliveryOffered() == that.isDeliveryOffered() && getName().equals(that.getName()) && getDescription().equals(that.getDescription()) && Objects.equals(getComments(), that.getComments()) && Objects.equals(getServiceArea(), that.getServiceArea()) && Objects.equals(getWebsite(), that.getWebsite()) && Objects.equals(getDocumentation(), that.getDocumentation()) && Objects.equals(getDaysOfWeek(), that.getDaysOfWeek()) && Objects.equals(getDeliveryDescription(), that.getDeliveryDescription()) && Objects.equals(getDietaryConsiderations(), that.getDietaryConsiderations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getComments(), getServiceArea(), getWebsite(), getDocumentation(), getDaysOfWeek(), isDeliveryOffered(), getDeliveryDescription(), getDietaryConsiderations());
    }
}



