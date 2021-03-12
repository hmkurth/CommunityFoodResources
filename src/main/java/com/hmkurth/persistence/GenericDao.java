package com.hmkurth.persistence;

import com.hmkurth.entity.User;
import com.hmkurth.entity.UserRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Entity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

/**
 * generic dao to take in any type of object, any entity managed by hibernate
 *A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 */
public class GenericDao<T> {//T is placeholder, variable for type

//create instance variable of class you are using
    private Class<T>  type;
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
     * @param id entity id to search by
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
     * @param entityName, the table you are searching
     * @param valueName, the name or value being searched for
     * @return Entities by that  name
     */
    public List<T> getEntityByName(String entityName, String valueName){
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(entityName);
        query.where(builder.like(propertyPath, "%" + valueName + "%"));// the where part, instance variables
        List<T> entities = (List<T>) session.createQuery(query).getResultList();
        session.close();
        logger.debug("The list of Entities by entity name " + entities);
        return entities;
    }
    /**
     * update Entity
     * @param entity  entity to be inserted or updated
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
     * Deletes a collection of enties??????????????????????????????????????????????
     *
     * @param type entity to be deleted
     *
     */

    public void deleteMultiple(Set<T> type) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
       // session.delete(type);
        //use remove or remove all????
        //product.getRecommendations().clear();
        session.remove(type);
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
        logger.debug("in get ALL " + list);
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
        logger.debug("The list of Entities by propertyName " + propertyName);
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
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        logger.debug("inserting entity {}", entity);
        session.close();
        return id;
    }




}
