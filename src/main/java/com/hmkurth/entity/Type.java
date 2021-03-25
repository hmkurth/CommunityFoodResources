package com.hmkurth.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



/**
 * A class to represent a type of resource, such as gov assistance. a social medida support platform, pantry or a meal.
 *
 * @author hmkurth
 */
@Entity(name = "Type")
@Table(name = "resource_type")//case sensitive
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name = "type_name")
    private String name;

}
