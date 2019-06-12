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
public class ChangePasswordBusinessService {

    //Respective Data access object has been autowired to access the method defined in respective Dao
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;



    //Validates Customer and updates password as given by the Customer

    @Transactional(propagation = Propagation.REQUIRED)

    public CustomerEntity updateCustomerPassword(final String accessToken, final String oldPassword,final String newPassword) throws  UpdateCustomerException {
        CustomerAuthTokenEntity customerAuthTokenEntity = customerDao.getCustomerAuthToken(accessToken);

       /* System.out.println(oldPassword);
        System.out.println(customerAuthTokenEntity.getCustomer().getPassword());
        System.out.println(customerAuthTokenEntity.getCustomer().getSalt());
       final String decryptedPassword = this.passwordCryptographyProvider.encrypt(oldPassword,customerAuthTokenEntity.getCustomer().getSalt());
       System.out.println(decryptedPassword);
*/
        if (oldPassword == null || newPassword == null) {
            throw new UpdateCustomerException("UCR-003", "No field should be empty");
        } else if(newPassword.length() < 8 || !newPassword.matches("(?=.*[0-9]).*") || !newPassword.matches("(?=.*[A-Z]).*")|| !newPassword.matches("(?=.*[~!@#$%^&*()_-]).*")) {
            throw new UpdateCustomerException("UCR-001","Weak password!");
        } //else if(!(old) ){
            //throw new UpdateCustomerException("UCR-004","Incorrect old password!");
        //}
        else {
             customerAuthTokenEntity.getCustomer().setPassword(newPassword);
            return customerAuthTokenEntity.getCustomer();
        }

    }


}
