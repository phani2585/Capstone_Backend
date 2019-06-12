package com.upgrad.FoodOrderingApp.service.dao;


import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    public AddressEntity getStateByStateUuid(String stateuuid) {
        try {
            return (AddressEntity)this.entityManager.createNamedQuery("stateByStateUuid", AddressEntity.class).setParameter("stateuuid", stateuuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
