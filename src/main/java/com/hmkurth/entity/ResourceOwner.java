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
    @ToString.Exclude
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
    private Set<Contact> contacts = new HashSet<Contact>();

    /**
     * Instantiates a new Resource owner.
     *
     * @param name     the name
     * @param website  the website
     * @param contacts the contacts
     */
    public ResourceOwner(@NonNull String name, String website, Set<Contact> contacts) {
        this.name = name;
        this.website = website;
        this.contacts = contacts;
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
     * Add contact.
     *
     * @param contact the contact
     */
    public void addContact(Contact contact) {
        contacts.add(contact);


    }


    /**
     * Gets contacts.
     *
     * @param i the
     * @return the contacts
     */
    public Set<Contact> getContacts(int i) {
        return contacts;
    }

    @Override

    public String toString(){
        String thisString = name + "  , website(opt): " + website ;
        return thisString;
    }

}
