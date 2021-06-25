package com.hmkurth.entity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent an owner of a resource, a company or program/entity.
 *
 * @author hmkurth
 */
@Data

@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ResourceOwner")
@Table(name = "resource_owners")//case sensitive
public class ResourceOwner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;
    @NonNull
    @Column(name="org_name")  //don't need if names are the same
    private String name;
    private String website;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade=CascadeType.ALL, orphanRemoval = true, fetch =FetchType.EAGER )
    private Set<FoodResource> resources = new HashSet<FoodResource>();




    /**
     * Instantiates a new Resource owner.
     *
     * @param name     the name
     * @param website  the website
     * @param resources the food resources associated with this owner
     */
    public ResourceOwner(@NonNull String name, String website, Set<FoodResource>resources) {
        this.name = name;
        this.website = website;
        this.resources = resources;
    }

    /**
     * Instantiates a new Resource owner.
     *
     * @param name    the name
     * @param website the website
     */
    public ResourceOwner(@NonNull String name, String website) {
        this.name = name;
        this.website = website;
    }

    /**
     * Add resource.
     *
     * @param resource the resource
     */
    public void addResource(FoodResource resource){
       resources.add(resource);


    }


    /**
     * Sets resources
     *
     * @param i the
     * @return the contacts
     */
    public Set<FoodResource> getResources(int i) {
        return resources;
    }

}
