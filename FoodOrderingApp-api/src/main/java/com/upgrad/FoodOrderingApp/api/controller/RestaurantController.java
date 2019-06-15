package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.AddressService;
import com.upgrad.FoodOrderingApp.service.businness.CategoryService;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
//import com.upgrad.FoodOrderingApp.service.businness.ItemService;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantService;
import com.upgrad.FoodOrderingApp.service.entity.*;
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
public class RestaurantController {

    //Required services are autowired to enable access to methods defined in respective Business services
    @Autowired
    private RestaurantService restaurantService;

    //@Autowired
   // private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;


    @RequestMapping(method = RequestMethod.GET, path = "/restaurant",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getAllRestaurants(){

        List<RestaurantEntity> restaurantEntityList=new ArrayList<RestaurantEntity>();
        restaurantEntityList.addAll(restaurantService.getRestaurants());
        final RestaurantListResponse restaurantListResponse=new RestaurantListResponse();


        for (RestaurantEntity restaurantEntity : restaurantEntityList) {

            final RestaurantList restaurantList=new RestaurantList();
            restaurantList.setId(UUID.fromString(restaurantEntity.getUuid()));
            restaurantList.setRestaurantName(restaurantEntity.getRestaurantName());
            restaurantList.setPhotoURL(restaurantEntity.getPhotoUrl());
            restaurantList.setCustomerRating(restaurantEntity.getCustomerRating());
            restaurantList.setAveragePrice(restaurantEntity.getAveragePriceForTwo());
            restaurantList.setNumberCustomersRated(restaurantEntity.getNumberOfCustomersRated());

            //restaurantList.setCategories(restaurantEntity.getCategories());

            final StateEntity stateEntity =addressService.getStateById(restaurantEntity.getAddress().getState().getId());
            final RestaurantDetailsResponseAddressState restaurantDetailsResponseAddressState=new RestaurantDetailsResponseAddressState();
            restaurantDetailsResponseAddressState.id(UUID.fromString(stateEntity.getUuid()));
            restaurantDetailsResponseAddressState.stateName(stateEntity.getStateName());

            final AddressEntity addressEntity = addressService.getAddressById(restaurantEntity.getAddress().getId());
            final RestaurantDetailsResponseAddress restaurantDetailsResponseAddress=new RestaurantDetailsResponseAddress();
            restaurantDetailsResponseAddress.id(UUID.fromString(addressEntity.getUuid()));
            restaurantDetailsResponseAddress.city(addressEntity.getCity());
            restaurantDetailsResponseAddress.flatBuildingName(addressEntity.getFlatBuilNumber());
            restaurantDetailsResponseAddress.locality(addressEntity.getLocality());
            restaurantDetailsResponseAddress.pincode(addressEntity.getPinCode());


            //final RestaurantCategoryEntity restaurantCategoryEntity=new RestaurantCategoryEntity();
           // restaurantCategoryEntity.setRestaurant(restaurantEntity);
            //restaurantCategoryEntity.setCategory(restaurantEntity.getCategory);

            restaurantDetailsResponseAddress.state(restaurantDetailsResponseAddressState);
            restaurantList.setAddress(restaurantDetailsResponseAddress);

            restaurantListResponse.addRestaurantsItem(restaurantList);
        }

        return new ResponseEntity<RestaurantListResponse>(restaurantListResponse, HttpStatus.OK);
    }
}
