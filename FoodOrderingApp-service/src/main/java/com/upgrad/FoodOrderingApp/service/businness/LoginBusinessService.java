package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthTokenEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Base64;


@Service
public class LoginBusinessService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;


    //Throw exception,If the Basic authentication is not provided in correct format,“Basic username:password” (username:password encoded to Base64 format)
   /* public void checkAuthenticationFormat(final String authentication) throws AuthenticationFailedException {

        byte[] decode = Base64.getDecoder().decode(authentication.split("Basic ")[1]);
        String decodedText = new String(decode);
        String[] decodedArray = decodedText.split(":");
        String contactNumberRegex = "^[0-9]{10}$";


        if(!decodedArray[0].matches(contactNumberRegex)) {
            throw new AuthenticationFailedException("ATH-003","Incorrect format of decoded customer name and password");
        } else if(decodedArray[1].length() < 8 || !decodedArray[1].matches("(?=.*[0-9]).*") || !decodedArray[1].matches("(?=.*[A-Z]).*")|| !decodedArray[1].matches("(?=.*[~!@#$%^&*()_-]).*")) {
            throw new AuthenticationFailedException("ATH-003","Incorrect format of decoded customer name and password");
        }
    }*/


    @Transactional(propagation = Propagation.REQUIRED)

    //Authenticates a customer based on contactNumber(as username) and password when the customer signs in for the first time and throw exception when certain conditions not met
    public CustomerAuthTokenEntity authenticate(final String contactNumber, final String password) throws AuthenticationFailedException {
        //If the contact number provided by the customer does not exist,
        CustomerEntity customerEntity = customerDao.getCustomerByContactNumber(contactNumber);
        if (customerEntity == null) {
            throw new AuthenticationFailedException("ATH-001", "This contact number has not been registered!");
        }

        //If the password matches, save the customer login information in the database
        final String encryptedPassword = cryptographyProvider.encrypt(password, customerEntity.getSalt());
            if (encryptedPassword.equals(customerEntity.getPassword())) {
                JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
                CustomerAuthTokenEntity customerAuthToken = new CustomerAuthTokenEntity();
                customerAuthToken.setCustomer(customerEntity);
                final ZonedDateTime now = ZonedDateTime.now();
                final ZonedDateTime expiresAt = now.plusHours(8);
                customerAuthToken.setAccessToken(jwtTokenProvider.generateToken(customerEntity.getUuid(), now, expiresAt));
                customerAuthToken.setLoginAt(now);
                customerAuthToken.setExpiresAt(expiresAt);
                customerAuthToken.setUuid(customerEntity.getUuid());
                customerDao.createAuthToken(customerAuthToken);
                customerDao.updateCustomer(customerEntity);
                return customerAuthToken;
            } else {
                //If the password provided by the customer does not match the password in the existing database,
                throw new AuthenticationFailedException("ATH-002", "Invalid Credentials");
            }
        }
}
