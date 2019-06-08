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



@Service
public class LoginBusinessService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;


    @Transactional(propagation = Propagation.REQUIRED)

    //Authenticates a customer based on contactNumber(as username) and password when the customer signs in for the first time
    public CustomerAuthTokenEntity authenticate(final String contactNumber, final String password) throws AuthenticationFailedException {
        CustomerEntity customerEntity = customerDao.getCustomerByContactNumber(contactNumber);
        if (customerEntity == null) {
            throw new AuthenticationFailedException("ATH-001", "This contact number has not been registered!");
        }

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
                throw new AuthenticationFailedException("ATH-002", "Invalid Credentials");
            }
        }
//Code needs to be added for ATH-003 =>Incorrect format of decoded customer name and password



}
