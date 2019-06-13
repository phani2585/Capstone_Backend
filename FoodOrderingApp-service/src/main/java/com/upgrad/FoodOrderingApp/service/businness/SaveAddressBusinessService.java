package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.dao.StateDao;
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

    @Autowired
    private StateDao stateDao;


    @Transactional(propagation = Propagation.REQUIRED)/* WORK IN PROGRESS */
    public AddressEntity verifyAndSaveAddressDetails(final String accessToken,final  String flatBuildingName, final String locality, final String city, final String pincode, final String  StateUuid) throws SaveAddressException, AddressNotFoundException {
        CustomerAuthTokenEntity customerAuthTokenEntity = customerDao.getCustomerAuthToken(accessToken);


        StateEntity stateEntity = stateDao.getStateByStateid(StateUuid);
        AddressEntity addressEntityToSave=addressDao.getAddressByAddressUuid(customerAuthTokenEntity.getCustomer().getUuid());

        String pinCodeRegex = "^[0-9]{6}$";

        if (flatBuildingName == null || locality == null || city == null || pincode == null ) {//stateid code needs to be written
            throw new SaveAddressException("SAR-001", "No field can be empty");
        } else if (!pincode.matches(pinCodeRegex)) {
            throw new SaveAddressException("SAR-002", "Invalid pincode");
        } else if (stateEntity == null) {
            throw new AddressNotFoundException("ANF-002", "No state by this id");
        } else {
            addressEntityToSave.setFlatBuilNumber(flatBuildingName);
            addressEntityToSave.setCity(city);
            addressEntityToSave.setLocality(locality);
            addressEntityToSave.setPinCode(pincode);
            addressEntityToSave.setUuid(StateUuid);

            return addressEntityToSave;
        }
    }






}
