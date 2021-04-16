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
    @OneToMany(mappedBy = "resource_owners", cascade=CascadeType.ALL, orphanRemoval = true, fetch =FetchType.EAGER )
    private Set<Contact> contacts = new HashSet<>();

    public ResourceOwner(String name,Contact contact, String website) {
    }

    public ResourceOwner(String name, String s) {
    }

    /**
     * Add contact.
     *
     * @param contact, the contact name
     */
    public void addContact(Contact contact) {
        contacts.add(contact);


    }


    public Set<Contact> getContacts(int i) {
        return contacts;
    }
}
