package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignupBusinessService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private PasswordCryptographyProvider passwordCryptographyProvider;


    @Transactional(
            propagation = Propagation.REQUIRED
    )
    public CustomerEntity signup(CustomerEntity customerEntity, String firstName,String lastName,String emailAddress,String contactNumber,String password ) throws SignUpRestrictedException {
        if(this.customerDao.getCustomerByContactNumber(contactNumber)!=null) {
            throw new SignUpRestrictedException("SGR-001","This contact number is already registered! Try other contact number.");
        } else if( firstName==null || lastName==null || emailAddress==null || password==null) {
            throw new SignUpRestrictedException("SGR-005","Except last name all fields should be filled");
        } else if (emailAddress.length()< 10) {
            throw new SignUpRestrictedException("SGR-002","Invalid email-id format!");
        } else if (contactNumber.length()!=10) {
            throw new SignUpRestrictedException("SGR-003","Invalid contact number!");
        }else if (password.length()<8) {
            throw new SignUpRestrictedException("SGR-004","Weak password");
        } else {
            String[] encryptedText = this.passwordCryptographyProvider.encrypt(customerEntity.getPassword());
            customerEntity.setSalt(encryptedText[0]);
            customerEntity.setPassword(encryptedText[1]);
            return this.customerDao.createUser(customerEntity);
        }


    }
}
