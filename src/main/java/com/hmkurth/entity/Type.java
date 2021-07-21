package com.hmkurth.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * A class to represent a type of resource, such as gov assistance. a social medida support platform, pantry or a meal.
 *
 * @author hmkurth
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(name = "Type")
@Table(name = "resource_type")//case sensitive
public class Type {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @NonNull
    @Column(name = "type_name")
    private String name;
    //TODO should have a set of resources for each type
    //references the foreign key
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "typeId", cascade=CascadeType.ALL, orphanRemoval = true, fetch =FetchType.EAGER )
    private Set<FoodResource> resources = new HashSet<>();


    /**
     * Add resource.
     *
     * @param resource the resource to add
     */
    public void addResource(FoodResource resource) {
        resources.add(resource);
        resource.setTypeId(this);

    }

    /**
     * Delete role.
     *
     * @param resource the resource to add
     */
    public void deleteResource(FoodResource resource) {
        resources.remove(resource);
        resource.setTypeId(null);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
