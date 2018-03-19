package com.mgil.java.labs.tickets.dao;

import com.mgil.java.labs.tickets.entities.Seat;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SeatDAO implements DAO<Seat> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<Seat> clazz = Seat.class;


    public Seat find(Object id) {
        return entityManager.find(clazz,id);
    }

    public void remove(Seat entity) {
        entityManager.remove(entity);
    }

    public Seat edit(Seat entity){

        Seat edited = entityManager.merge(entity);
        entityManager.flush();
        return edited;

    }

    public void persist(Seat entity) {
        entityManager.persist(entity);
    }


    public List<Seat> findAll(){

        //Creating a Criteria Builder for make the query of all the entities

        final CriteriaQuery<Seat> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(clazz);

        criteriaQuery.select(criteriaQuery.from(clazz));

        return entityManager.createQuery(criteriaQuery).getResultList();

    }


    public void deleteAll(){

        //Criteria Query for delete

        final CriteriaDelete<Seat> deleteCriteriaQuery = entityManager.getCriteriaBuilder().createCriteriaDelete(clazz);
        deleteCriteriaQuery.from(clazz);

        entityManager.createQuery(deleteCriteriaQuery).executeUpdate();

    }



}
