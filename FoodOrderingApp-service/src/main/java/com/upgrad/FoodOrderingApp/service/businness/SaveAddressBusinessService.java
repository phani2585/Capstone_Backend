package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthTokenEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveAddressBusinessService {

    //Respective Data access object has been autowired to access the method defined in respective Dao
    @Autowired
    private CustomerDao customerDao;


    @Autowired
    private AddressDao addressDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void verifyAddressDetails(final String accessToken, final String flatBuildingName, final String locality, final String city, final String pincode, final String stateUuid) throws SaveAddressException, AddressNotFoundException {
        //StateEntity stateEntity = addressDao.getStateByStateUuid(stateUuid);


        if (flatBuildingName == null || locality == null || city == null || pincode == null || stateUuid == null) {
            throw new SaveAddressException("SAR-001", "No field can be empty");
        } else if (pincode.length() != 6) {
            throw new SaveAddressException("SAR-002", "Invalid pincode");// code to be written for pincode format
        } else if ( stateUuid == null) {
            throw new AddressNotFoundException("ANF-002", "No state by this id");//code needs to be written for this
        }
    }
}
