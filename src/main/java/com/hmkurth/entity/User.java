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

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param userName  the user name
     * @param password  the password
     * @param email     the email
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public @NonNull String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public @NonNull String getLastName() {
        return this.lastName;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public @NonNull String getUserName() {
        return this.userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public @NonNull String getPassword() {
        return this.password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public @NonNull String getEmail() {
        return this.email;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public Set<UserRoles> getRoles() {
        return this.roles;
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
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
