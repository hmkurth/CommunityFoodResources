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

    public int getId() {
        return this.id;
    }

    public @NonNull String getName() {
        return this.name;
    }

    public @NonNull Type getType_id() {
        return this.type_id;
    }

    public ResourceOwner getOwner() {
        return this.owner;
    }

    public @NonNull String getDescription() {
        return this.description;
    }

    public Location getLocation() {
        return this.location;
    }

    public Contact getContactId() {
        return this.contactId;
    }

    public String getComments() {
        return this.comments;
    }

    public String getServiceArea() {
        return this.serviceArea;
    }

    public String getWebsite() {
        return this.website;
    }

    public @NonNull String getDocumentation() {
        return this.documentation;
    }

    public String getDaysOfWeek() {
        return this.daysOfWeek;
    }

    public boolean isDeliveryOffered() {
        return this.deliveryOffered;
    }

    public String getDeliveryDescription() {
        return this.deliveryDescription;
    }

    public String getDietaryConsiderations() {
        return this.dietaryConsiderations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setType_id(@NonNull Type type_id) {
        this.type_id = type_id;
    }

    public void setOwner(ResourceOwner owner) {
        this.owner = owner;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDocumentation(@NonNull String documentation) {
        this.documentation = documentation;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public void setDeliveryOffered(boolean deliveryOffered) {
        this.deliveryOffered = deliveryOffered;
    }

    public void setDeliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }

    public void setDietaryConsiderations(String dietaryConsiderations) {
        this.dietaryConsiderations = dietaryConsiderations;
    }

    public String toString() {
        return "FoodResource(id=" + this.getId() + ", name=" + this.getName() + ", type_id=" + this.getType_id() + ", owner=" + this.getOwner() + ", description=" + this.getDescription() + ", location=" + this.getLocation() + ", contactId=" + this.getContactId() + ", comments=" + this.getComments() + ", serviceArea=" + this.getServiceArea() + ", website=" + this.getWebsite() + ", documentation=" + this.getDocumentation() + ", daysOfWeek=" + this.getDaysOfWeek() + ", deliveryOffered=" + this.isDeliveryOffered() + ", deliveryDescription=" + this.getDeliveryDescription() + ", dietaryConsiderations=" + this.getDietaryConsiderations() + ")";
    }
}



