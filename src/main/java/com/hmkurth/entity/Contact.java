package com.hmkurth.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * A class to represent a user.
 *
 * @author hmkurth
 */
@Data

@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "Contact")
@Table(name = "contact_details")//case sensitive
public class Contact {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;

    @Column(name="first_name")
    private String firstName;
    @NonNull
    @Column(name="last_name")
    private String lastName;
    private String email;
    private String phone;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private ResourceOwner owner;

    /**
     * Instantiates a new Contact.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param phone     the phone
     */
    public Contact(@NonNull String firstName, @NonNull String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return getId() == contact.getId() && getFirstName().equals(contact.getFirstName()) && getLastName().equals(contact.getLastName()) && Objects.equals(getEmail(), contact.getEmail()) && Objects.equals(getPhone(), contact.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getPhone());
    }


    @Override

    public String toString(){
        String thisString = firstName + "  " + lastName + ", email:  " + email + " ,  phone: " + phone  ;
        return thisString;
    }
}