package com.niit.Customer.Controller;

import com.niit.Customer.Domain.Customer;
import com.niit.Customer.Exception.CustomerNotFoundException;
import com.niit.Customer.Service.CustomerService;
import com.niit.Customer.Service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    private ResponseEntity responseEntity;
    private CustomerService customerService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public CustomerController(CustomerService customerService, SecurityTokenGenerator securityTokenGenerator) {
        this.customerService = customerService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer){
        Customer createdCustomer=customerService.saveCustomer(customer);
        return responseEntity=new ResponseEntity<>("Customer created", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer) throws  CustomerNotFoundException {
        Map<String,String> map=null;
        try {
            Customer customer1 =customerService.findByUserNameAndPassword(customer.getUserName(),customer.getPassword());
            if (customer1.getUserName().equals(customer.getUserName())){
                map=securityTokenGenerator.generateToken(customer);

            }
            responseEntity=new ResponseEntity<>(map,HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            throw new CustomerNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>("Try after someTime !!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
        @GetMapping("/api/v1/customerservice/customers")
    public ResponseEntity getAllCustomer() throws CustomerNotFoundException{
        List<Customer> list=customerService.getAllCustomer();
        responseEntity=new ResponseEntity<>(list,HttpStatus.OK);
        return responseEntity;
    }
    @DeleteMapping("/api/v1/customerservice/{customerId}")
    public ResponseEntity deleteCustomerId(@PathVariable("customerId") int customerId) throws CustomerNotFoundException {
        ResponseEntity responseEntity =null;
        try {
            customerService.deleteCustomer(customerId);
            responseEntity = new ResponseEntity("Successfully deleted the 1 record",HttpStatus.OK);
        }
        catch (CustomerNotFoundException e){
            throw  new CustomerNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
