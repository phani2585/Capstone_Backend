package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthTokenEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetAllSavedAddressesBusinessService {

    //Respective Data access object has been autowired to access the method defined in respective Dao
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AddressDao addressDao;

    @Transactional(propagation = Propagation.REQUIRED)

    public List<AddressEntity> getAllSavedAddresses(final String accessToken) {

        CustomerAuthTokenEntity customerAuthTokenEntity = customerDao.getCustomerAuthToken(accessToken);

        return addressDao.getAllSavedAddresses();
    }
}
