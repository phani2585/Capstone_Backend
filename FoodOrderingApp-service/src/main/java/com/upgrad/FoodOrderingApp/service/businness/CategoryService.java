package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CategoryDao;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CategoryEntity> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CategoryEntity> getCategoriesByRestaurant(String restaurantId) {
        return categoryDao.getCategoriesByRestaurant(restaurantId);
    }
}
