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

    private String website;

    @OneToOne
    @PrimaryKeyJoinColumn
    @Column(name="contact_id")//fk to contact
    private Contact contactId;
/** example from docs
    public void setPerson(Person person) {
        this.person = person;
        this.id = person.getId();
    }
*/
    }
