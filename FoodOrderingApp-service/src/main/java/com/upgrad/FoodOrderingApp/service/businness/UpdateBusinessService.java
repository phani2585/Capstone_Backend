package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthTokenEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class UpdateBusinessService {


    //Respective Data access object has been autowired to access the method defined in respective Dao
    @Autowired
    private CustomerDao customerDao;

    //Checks whether a customer has signed out based on accessToken

    @Transactional(propagation = Propagation.REQUIRED)

    public CustomerEntity verifyCustomerDetails(final String accessToken, final String firstName,final String lastName) throws AuthorizationFailedException, UpdateCustomerException {
        CustomerAuthTokenEntity customerAuthTokenEntity = customerDao.getCustomerAuthToken(accessToken);

        if (firstName == null) {
            throw new UpdateCustomerException("UCR-002", "First name field should not be empty");
        }
        if (customerAuthTokenEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
        } else if (customerAuthTokenEntity != null && customerAuthTokenEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "Customer is logged out. Log in again to access this endpoint.");
        } else if (customerAuthTokenEntity != null && ZonedDateTime.now().isAfter(customerAuthTokenEntity.getExpiresAt())) {
            throw new AuthorizationFailedException("ATHR-003", "Your session is expired. Log in again to access this endpoint.");
        } else {
            customerAuthTokenEntity.getCustomer().setFirstName(firstName);
            customerAuthTokenEntity.getCustomer().setLastName(lastName);
            return customerAuthTokenEntity.getCustomer();
        }

    }



}
