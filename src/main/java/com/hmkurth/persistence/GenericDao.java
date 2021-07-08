package com.hmkurth.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

/**
 * generic dao to take in any type of object, any entity managed by hibernate
 * A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 *
 * @param <T> the type parameter
 */
public class GenericDao<T> {//T is placeholder, variable for type

//create instance variable of class you are using
    private final Class<T>  type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the entity type, for example, User.
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets a entity by id
     *
     * @param <T> the type parameter
     * @param id  entity id to search by
     * @return a entity
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * get entity by  property name
     *
     * @param entityName the entity name
     * @param valueName  the value name
     * @return Entities by that  name
     */
    public List<T> getEntityByName(String entityName, String valueName){
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(entityName);
        query.where(builder.like(propertyPath, "%" + valueName + "%"));// the where part, instance variables
        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        logger.debug("The list of Entities by entity name " + entities);
        return entities;
    }

    /**
     * update Entity
     *
     * @param entity entity to be inserted or updated
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);

       // session.clear();
        transaction.commit();
        session.close();
    }

    /**
     * Gets all entities
     *
     * @return the all entities
     */
    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;



    }

    /** Get entities by property (exact match)
     * sample usage: getByPropertyEqual("lastName", "Curry")
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for person with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from(type );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;
    }


    /** Get entities by property (exact match) equal to an integer
     * sample usage: getByPropertyEqual("lastName", "Curry")
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public List<T> getByPropertyEqualToInt(String propertyName, int value) {
        Session session = getSession();

        logger.debug("Searching for person with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from(type );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;
    }


    /** Get entities by property (exact match)
     * sample usage: getByPropertyEqual("isVerified", True)
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public List<T> getByPropertyEqualToBoolean(String propertyName, Boolean value) {
        Session session = getSession();

        logger.debug("Searching for person with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from(type );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;
    }


    /**
     * get entity by  property name
     * @param propertyName, the property name to search by
     * @param value value of the property to search for
     * @return entity by that  name
     */
    public List<T> getPropertyByName(String propertyName,String value){
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.like(propertyPath, "%" + value + "%"));// the where part, instance variables
        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        logger.debug("The list of Entities by propertyName " + propertyName + " and value/search term: " + value);
        return entities;
    }


    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();

    }
    /**
     * update entity
     * @param entity  entity to be inserted or updated
     */
    public int insert(T entity) {
        int id;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        logger.debug("inserting entity {}", entity);
        session.close();
        return id;
    }

    /**
     * will return a list of objects to populate dropdown menus, trying this from
     * https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database
     * TODO what is actually different in this method from the getAll method?
     * @return
     * @throws SQLException

    public List<T> list(T entity) throws SQLException {
        List<T> listCategory = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(databaseURL, user, password)) {
            String sql = "SELECT * FROM  + "entity" +  ORDER BY name";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt("category_id");
                String name = result.getString("name");
                T category = new T(id, name);

                listCategory.add(category);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listCategory;
    }
}
     */

}
