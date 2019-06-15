package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.CategoryList;
import com.upgrad.FoodOrderingApp.api.model.CategoryListResponse;
import com.upgrad.FoodOrderingApp.service.businness.CategoryService;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//RestController annotation specifies that this class represents a REST API(equivalent of @Controller + @ResponseBody)
@RestController
//"@CrossOrigin” annotation enables cross-origin requests for all methods in that specific controller class.
@CrossOrigin
@RequestMapping("/")
public class CategoryController {

    //Required services are autowired to enable access to methods defined in respective Business services
    @Autowired
    private CategoryService categoryService;


    //getallcategories endpoint retrieves all the categories present in the database, ordered by their name
    @RequestMapping(method = RequestMethod.GET, path = "/category",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CategoryListResponse>> getallcategories(){


        List<CategoryEntity> categoryEntityList=new ArrayList<CategoryEntity>();
        categoryEntityList.addAll(categoryService.getAllCategories());
        List<CategoryListResponse> categoryListResponseList=new ArrayList<CategoryListResponse>();

        for (CategoryEntity categoryEntity : categoryEntityList) {

            CategoryList categoryList =new CategoryList();
            categoryList.setId(UUID.fromString(categoryEntity.getUuid()));
            categoryList.setCategoryName(categoryEntity.getCategoryName());
            CategoryListResponse categoryListResponse=new CategoryListResponse();
            categoryListResponseList.add(categoryListResponse
                    .categoryName(categoryList.getCategoryName())
                    .id(categoryList.getId()));
        }

        return new ResponseEntity<List<CategoryListResponse>>(categoryListResponseList, HttpStatus.OK);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/category/{category_id}",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    //public ResponseEntity<>
}
