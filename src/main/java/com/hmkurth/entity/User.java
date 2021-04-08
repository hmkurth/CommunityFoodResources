package com.hmkurth.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a user.
 *
 * @author hmkurth
 */
@Data

@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "User")
@Table(name = "users")//case sensitive
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;
    @NonNull
    @Column(name="first_name")  //don't need if names are the same
    private String firstName;
    @NonNull
    @Column(name="last_name")
    private String lastName;
    @NonNull
   @Column(name="user_name")
    private String userName;
    @NonNull
    @Column(name="user_password")
    private String password;
    @NonNull
    private String email;
    //references the foreign key
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval = true, fetch =FetchType.EAGER )
    //@JoinColumn(name = "id")//changing this...NOPE

    private Set<UserRoles> roles = new HashSet<>();


    /**
     * Add role.
     *
     * @param role the role name
     */
    public void addRole(UserRoles role) {
        roles.add(role);
        role.setUser(this);

    }

    /**
     * Delete role.
     *
     * @param role the role
     */
    public void deleteRole(UserRoles role) {
        roles.remove(role);
        role.setUser(null);

    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && firstName.equals(user.firstName) && lastName.equals(user.lastName) && userName.equals(user.userName) && password.equals(user.password) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userName, password, email);
    }
}
