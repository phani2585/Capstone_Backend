package com.upgrad.FoodOrderingApp.api.controller;


import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
        import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;
import com.upgrad.FoodOrderingApp.service.businness.SignupBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.UUID;

//RestController annotation specifies that this class represents a REST API(equivalent of @Controller + @ResponseBody)
@RestController
@CrossOrigin
@RequestMapping("/")
public class CustomerController {

    //Required services are autowired to enable access to methods defined in respective Business services
    @Autowired
    private SignupBusinessService signupBusinessService;

    //@Autowired
    //private LoginBusinessService loginBusinessService;

   // @Autowired
   // private LogoutBusinessService logoutBusinessService;

   // @Autowired
   // private UpdateBusinessService updateBusinessService;

   // @Autowired
   // private ChangePasswordBusinessService changePasswordBusinessService;


    //signup  endpoint requests for all the attributes in “SignupCustomerRequest” about the customer and registers a customer successfully

    @RequestMapping(method = RequestMethod.POST, path = "/customer/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupCustomerResponse> signup(@RequestBody(required = false) final SignupCustomerRequest signupCustomerRequest) throws SignUpRestrictedException {

        final CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setUuid(UUID.randomUUID().toString());
        customerEntity.setFirstName(signupCustomerRequest.getFirstName());
        customerEntity.setLastName(signupCustomerRequest.getLastName());
        customerEntity.setEmail(signupCustomerRequest.getEmailAddress());
        customerEntity.setContactNumber(signupCustomerRequest.getContactNumber());
        customerEntity.setSalt("1234abc");
        customerEntity.setPassword(signupCustomerRequest.getPassword());

        final CustomerEntity createdCustomerEntity = signupBusinessService.signup(customerEntity,signupCustomerRequest.getFirstName(),signupCustomerRequest.getLastName(),signupCustomerRequest.getContactNumber(),signupCustomerRequest.getPassword(),signupCustomerRequest.getEmailAddress());
        SignupCustomerResponse customerResponse = new SignupCustomerResponse()
                .id(createdCustomerEntity.getUuid())
                .status("CUSTOMER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SignupCustomerResponse>(customerResponse, HttpStatus.CREATED);
    }



}
