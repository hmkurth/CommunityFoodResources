package com.hmkurth.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

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
    @GenericField
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @NonNull
    @Column(name = "resource_name")
    @FullTextField
    private String name;

    @GenericField
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "type_Id",
            foreignKey = @ForeignKey(name = "resource_type_food_resources_id_fk")
    )
    private @NonNull Type typeId;//fk to resource_type
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne
    @PrimaryKeyJoinColumn
    @FullTextField
    private ResourceOwner owner;
    @NonNull
    @FullTextField
    private String description;
    @ManyToOne
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
   //@Column(s) not allowed on a @OneToOne property: com.hmkurth.entity.FoodResource.location
    private Location location;//fk to location
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = true)
    private Contact contactId;//fk to contact
    @Column(name = "comments")
    @FullTextField
    private String comments;
    @Column(name = "service_area")
    @FullTextField
    private String serviceArea;//the city/county/area served
    @FullTextField
    private String website;
    @Column(name = "documentation_needed")
    @NonNull
    @FullTextField
    private String documentation;
    @Column(name = "day_of_week_offered")
    @FullTextField
    private String daysOfWeek;  //should I make this an array list??how does that work with the DB???
    @Column(name = "delivery_offered")
    private boolean deliveryOffered;
    @FullTextField
    @Column(name = "delivery_desc")//if delivery is offered, description of services
    private String deliveryDescription;
    @FullTextField
    @Column(name="dietary_considerations")
    private String dietaryConsiderations;
    @NonNull
    @Column(name = "is_verified")
    boolean verificationStatus;


    /**
     * Instantiates a new Food resource.
     *
     * @param id              the id
     * @param name            the name
     * @param typeId         the type id
     * @param owner           the owner
     * @param description     the description
     * @param location        the location
     * @param documentation   the documentation
     * @param deliveryOffered the delivery offered
     */
    public FoodResource(int id, @NonNull String name, @NonNull Type typeId, ResourceOwner owner, @NonNull String description, Location location,String documentation, boolean deliveryOffered) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.owner = owner;
        this.description = description;
        this.location = location;
        this.documentation = documentation;
        this.deliveryOffered = deliveryOffered;
    }

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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public @NonNull String getName() {
        return this.name;
    }

    /**
     * Gets type id.
     *
     * @return the type id
     */
    public @NonNull Type getTypeId() {
        return this.typeId;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public ResourceOwner getOwner() {
        return this.owner;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public @NonNull String getDescription() {
        return this.description;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gets contact id.
     *
     * @return the contact id
     */
    public Contact getContactId() {
        return this.contactId;
    }

    /**
     * Gets comments.
     *
     * @return the comments
     */
    public String getComments() {
        return this.comments;
    }

    /**
     * Gets service area.
     *
     * @return the service area
     */
    public String getServiceArea() {
        return this.serviceArea;
    }

    /**
     * Gets website.
     *
     * @return the website
     */
    public String getWebsite() {
        return this.website;
    }

    /**
     * Gets documentation.
     *
     * @return the documentation
     */
    public @NonNull String getDocumentation() {
        return this.documentation;
    }

    /**
     * Gets days of week.
     *
     * @return the days of week
     */
    public String getDaysOfWeek() {
        return this.daysOfWeek;
    }

    /**
     * Is delivery offered boolean.
     *
     * @return the boolean
     */
    public boolean isDeliveryOffered() {
        return this.deliveryOffered;
    }

    /**
     * Gets delivery description.
     *
     * @return the delivery description
     */
    public String getDeliveryDescription() {
        return this.deliveryDescription;
    }

    /**
     * Gets dietary considerations.
     *
     * @return the dietary considerations
     */
    public String getDietaryConsiderations() {
        return this.dietaryConsiderations;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     * Sets type id.
     *
     * @param typeId the type id
     */
    public void setTypeId(Type typeId) {
        this.typeId = typeId;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(ResourceOwner owner) {
        this.owner = owner;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets contact id.
     *
     * @param contactId the contact id
     */
    public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

    /**
     * Sets comments.
     *
     * @param comments the comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Sets service area.
     *
     * @param serviceArea the service area
     */
    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    /**
     * Sets website.
     *
     * @param website the website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Sets documentation.
     *
     * @param documentation the documentation
     */
    public void setDocumentation(@NonNull String documentation) {
        this.documentation = documentation;
    }

    /**
     * Sets days of week.
     *
     * @param daysOfWeek the days of week
     */
    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    /**
     * Sets delivery offered.
     *
     * @param deliveryOffered the delivery offered
     */
    public void setDeliveryOffered(boolean deliveryOffered) {
        this.deliveryOffered = deliveryOffered;
    }

    /**
     * Sets delivery description.
     *
     * @param deliveryDescription the delivery description
     */
    public void setDeliveryDescription(String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }

    /**
     * Sets dietary considerations.
     *
     * @param dietaryConsiderations the dietary considerations
     */
    public void setDietaryConsiderations(String dietaryConsiderations) {
        this.dietaryConsiderations = dietaryConsiderations;
    }

    public String toString() {
        return "FoodResource(id=" + this.getId() + ", name=" + this.getName() + ", type_id=" + this.getTypeId() + ", owner=" + this.getOwner() + ", description=" + this.getDescription() + ", location=" + this.getLocation() + ", contactId=" + this.getContactId() + ", comments=" + this.getComments() + ", serviceArea=" + this.getServiceArea() + ", website=" + this.getWebsite() + ", documentation=" + this.getDocumentation() + ", daysOfWeek=" + this.getDaysOfWeek() + ", deliveryOffered=" + this.isDeliveryOffered() + ", deliveryDescription=" + this.getDeliveryDescription() + ", dietaryConsiderations=" + this.getDietaryConsiderations() + ")";
    }
}



