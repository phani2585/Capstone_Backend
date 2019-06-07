package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDao {
    @PersistenceContext
    private EntityManager entityManager;



    public CustomerEntity createUser(CustomerEntity customerEntity) {
        this.entityManager.persist(customerEntity);
        return customerEntity;
    }

    public CustomerEntity getCustomerByContactNumber(String contactNumber) {
        try {
            return (CustomerEntity)this.entityManager.createNamedQuery("customerByContactNumber", CustomerEntity.class).setParameter("contactNumber", contactNumber).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }



}
