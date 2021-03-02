package com.hmkurth.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * class to represent user roles
 *
 * @author hmkurth
 */
@Entity(name = "UserRoles")
@Table(name = "user_roles")//case sensitive
public class UserRoles {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native",strategy="native")
    private int id;
    /**
     * The User name.
     */
    @Column(name="users_name")
    private String usersName;
    /**
     * The Role name.
     */
    @Column(name="role_name")
    private String roleName;



    /**
     * The User id.
     */
    @ManyToOne
    @JoinColumn(name = "users_id",
            foreignKey = @ForeignKey(name = "users_id")
    )
//@Column(name="users_id")  this is not allowed on many to one property
    private  User user;//hibernate maps this


    /**
     * Instantiates a new User roles.
     */
    public UserRoles() {

    }

    /**
     * Instantiates a new User roles.
     *
     * @param roleName the role name
     * @param user     the user
     */
    public UserRoles(String roleName, User user) {
        this.roleName = roleName;
        this.user = user;
    }




    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
     * Gets user name.
     *
     * @return the user name
     */
    public String getUsersName() {
        return usersName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUsersName(String userName) {
        this.usersName = userName;
    }

    /**
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }
/**
 * to string method
 * */
    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", userName='" + usersName + '\'' +
                ", roleName='" + roleName + '\'' +

                '}';
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return id == userRoles.id && usersName.equals(userRoles.usersName) && roleName.equals(userRoles.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usersName, roleName);
    }*/
}
