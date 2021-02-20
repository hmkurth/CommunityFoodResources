package com.hmkurth.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * class to represent user roles
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
    @Column(name="user_name")
    private String userName;
    /**
     * The Role name.
     */
    @Column(name="role_name")
    private String roleName;
    /**
     * The User id.
     */
    @ManyToOne
    private  User user;//hibernate maps this

    /**
     * Instantiates a new User roles.
     */
    public UserRoles() {

    }

    /**
     * Instantiates a new User roles.
     *
     * @param userName the user name
     * @param roleName the role name
     * @param user     the user
     */
    public UserRoles(String userName, String roleName, User user) {
        this.userName = userName;
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
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
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


    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", user=" + user +
                '}';
    }
}
