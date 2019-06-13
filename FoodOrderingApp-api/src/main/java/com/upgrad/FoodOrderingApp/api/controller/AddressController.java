package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.*;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//RestController annotation specifies that this class represents a REST API(equivalent of @Controller + @ResponseBody)
@RestController
//"@CrossOrigin” annotation enables cross-origin requests for all methods in that specific controller class.
@CrossOrigin
@RequestMapping("/")
public class AddressController {

    //Required services are autowired to enable access to methods defined in respective Business services
    @Autowired
    private SaveAddressBusinessService saveAddressBusinessService;

    @Autowired
    private AuthorizeAccessTokenBusinessService authorizationBusinessService;

    @Autowired
    private GetAllSavedAddressesBusinessService getAllSavedAddressesBusinessService;

    //@Autowired
    //private DeleteSavedAddressBusinessService deleteSavedAddressBusinessService;

    @Autowired
    private GetAllStatesBusinessService getAllStatesBusinessService;

    /*WORK IN PROGRESS */
    //saveaddress  endpoint requests for all the attributes in “SaveAddressRequest” about the customer and saves the address of a customer successfully.
    //PLEASE NOTE @RequestBody(required = false) inside saveaddress function will disable parameters in request body in request model.
    @RequestMapping(method = RequestMethod.POST, path = "/address", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SaveAddressResponse> saveaddress(@RequestBody(required = false) final SaveAddressRequest saveAddressRequest,
                                                           @RequestHeader("accessToken") final String accessToken) throws AuthorizationFailedException, SaveAddressException, AddressNotFoundException {

        String [] bearerToken = accessToken.split("Bearer ");
        authorizationBusinessService.verifyAuthToken(bearerToken[1]);


        final AddressEntity savedAddressEntity = saveAddressBusinessService.verifyAndSaveAddressDetails(accessToken, saveAddressRequest.getFlatBuildingName(), saveAddressRequest.getLocality(), saveAddressRequest.getCity(), saveAddressRequest.getPincode(), saveAddressRequest.getStateUuid());

        SaveAddressResponse saveAddressResponse = new SaveAddressResponse()
                .id(savedAddressEntity.getUuid())
                .status("ADDRESS SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SaveAddressResponse>(saveAddressResponse, HttpStatus.OK);

    }

/* WORK IN PROGRESS */
    //getallsavedaddresses endpoint retrieves all the addresses of a valid customer present in the database

    @RequestMapping(method = RequestMethod.GET, path = "/address/customer",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AddressListResponse> getallsavedaddresses(@RequestHeader("accessToken") final String accessToken) throws AuthorizationFailedException  {

        String [] bearerToken = accessToken.split("Bearer ");
        authorizationBusinessService.verifyAuthToken(bearerToken[1]);

        List<AddressEntity> addressEntityList=new ArrayList<AddressEntity>();
        addressEntityList.addAll(getAllSavedAddressesBusinessService.getAllSavedAddresses(accessToken));
        AddressListResponse addressListResponse=new AddressListResponse();

        for (AddressEntity addressEntity : addressEntityList) {

            AddressList addressList =new AddressList();
            addressList.setId(UUID.fromString(addressEntity.getUuid()));
            //statesList.setStateName(stateEntity.getStateName());
            addressListResponse.addAddressesItem(addressList);
        }


        return new ResponseEntity<AddressListResponse>(addressListResponse,HttpStatus.OK);
    }


    //getallstates endpoint retrieves all the states present in the database
    @RequestMapping(method = RequestMethod.GET, path = "/states",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StatesListResponse> getallstates(){


        List<StateEntity> stateEntityList=new ArrayList<StateEntity>();
        stateEntityList.addAll(getAllStatesBusinessService.getAllStates());
        StatesListResponse statesListResponse=new StatesListResponse();

        for (StateEntity stateEntity : stateEntityList) {

           // List<StatesList> statesListList=new ArrayList<StatesList>();
             StatesList statesList =new StatesList();
            statesList.setId(UUID.fromString(stateEntity.getUuid()));
            statesList.setStateName(stateEntity.getStateName());
            statesListResponse.addStatesItem(statesList);
        }


        return new ResponseEntity<StatesListResponse>(statesListResponse,HttpStatus.OK);
    }

}
