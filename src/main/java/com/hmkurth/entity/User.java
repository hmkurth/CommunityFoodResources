package com.hmkurth.entity;


import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
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

    public User() {
    }

    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String userName, @NonNull String password, @NonNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }


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

    public int getId() {
        return this.id;
    }

    public @NonNull String getFirstName() {
        return this.firstName;
    }

    public @NonNull String getLastName() {
        return this.lastName;
    }

    public @NonNull String getUserName() {
        return this.userName;
    }

    public @NonNull String getPassword() {
        return this.password;
    }

    public @NonNull String getEmail() {
        return this.email;
    }

    public Set<UserRoles> getRoles() {
        return this.roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
