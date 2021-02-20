package com.hmkurth.persistence;

import com.hmkurth.entity.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The dao of the UserRoles class
 * much of the code is borrowed from examples, may need to be edited
 */
public class UserRolesDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all UserRoles
     * @return all the UserRoles
     */
    public List<UserRoles> getAllUserRoles() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery(UserRoles.class);
        Root<UserRoles> root = query.from(UserRoles.class);
        List<UserRoles> UserRoles = session.createQuery(query).getResultList();
        session.close();
        logger.debug("The list of UserRoles " + UserRoles);
        return UserRoles;

    }
    /**
     * get UserRoles by  role name
     * @return UserRoles by that  name
     */
    public List<UserRoles> getUserRolesByName(String roleName){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery(UserRoles.class);
        Root<UserRoles> root = query.from(UserRoles.class);
        Expression<String> propertyPath = root.get("roleName");
        query.where(builder.like(propertyPath, "%" + roleName + "%"));// the where part, instance variables
        List<UserRoles> UserRoles = session.createQuery(query).getResultList();
        session.close();
        logger.debug("The list of UserRoles by rolename " + UserRoles);
        return UserRoles;
    }
    /**
     * Get UserRoles by id
     * @return UserRoles
     */
    public UserRoles getById(int id) {
        Session session = sessionFactory.openSession();
        UserRoles UserRoles = session.get( UserRoles.class, id );//functionality included in hibernate for getting by ids
        session.close();
        return UserRoles;
    }


    /**
     * update UserRoles
     * @param UserRoles  UserRoles to be inserted or updated
     */
    public void saveOrUpdate(UserRoles UserRoles) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //id = (int)session.save(UserRoles);
       // transaction.commit();
        session.saveOrUpdate(UserRoles);
        transaction.commit();
        session.close();
    }
    /**
     * update UserRoles
     * @param userRoles  UserRoles to be inserted or updated
     */
    public int insert(UserRoles userRoles) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(userRoles);
        transaction.commit();
        logger.debug("inserting UserRoles {}", userRoles);
        session.close();
        return id;
    }

    /**
     * Delete a UserRoles
     * @param userRoles UserRoles to be deleted
     */
    public void delete(UserRoles userRoles) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(userRoles);
        transaction.commit();

        session.close();
    }
    /** Return a list of all UserRoless
     *
     * @return All UserRoless
     */
    public List<UserRoles> getAll() {

        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery( UserRoles.class );
        Root<UserRoles> root = query.from( UserRoles.class );
        List<UserRoles> UserRoless = session.createQuery( query ).getResultList();

        logger.debug("The list of UserRoless " + UserRoless);
        session.close();

        return UserRoless;
    }

    /**
     * Get UserRoles by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<UserRoles> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for UserRoles with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery( UserRoles.class );
        Root<UserRoles> root = query.from( UserRoles.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<UserRoles> UserRoless = session.createQuery( query ).getResultList();

        session.close();
        return UserRoless;
    }

    /**
     * Get UserRoles by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<UserRoles> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for UserRoles with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserRoles> query = builder.createQuery( UserRoles.class );
        Root<UserRoles> root = query.from( UserRoles.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<UserRoles> UserRoles = session.createQuery( query ).getResultList();
        session.close();
        return UserRoles;
    }


}
