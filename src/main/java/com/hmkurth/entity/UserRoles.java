package com.hmkurth.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Column(name= "user_name")
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
    @JoinColumn(name = "users_id",
            foreignKey = @ForeignKey(name = "user_roles_users_id_fk")
    )

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
     * @param userName the userName of the user
     */
    public UserRoles(String roleName, User user, String userName) {
        this.roleName = roleName;
        this.user = user;
        this.userName = userName;
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
                ", userName='" + userName + '\'' +
                ", roleName='" + roleName + '\'' +

                '}';
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return id == userRoles.id && userName.equals(userRoles.userName) && roleName.equals(userRoles.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, roleName);
    }*/
}
