package com.upgrad.FoodOrderingApp.service.dao;


import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CategoryEntity> getAllCategories(){

        try {
            return this.entityManager.createNamedQuery("allCategories", CategoryEntity.class).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<CategoryEntity> getCategoriesByRestaurant(String restaurantId){

        try {

            return this.entityManager.createNamedQuery("getAllCategoryById", CategoryEntity.class).setParameter("uuid", restaurantId).getResultList();

        } catch (NoResultException nre) {
            return null;
        }
    }
}
