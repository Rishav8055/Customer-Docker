package com.niit.Customer.Service;

import com.niit.Customer.Domain.Customer;
import com.niit.Customer.Exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
    public Customer findByUserNameAndPassword(String userName,String password) throws CustomerNotFoundException;
    List<Customer> getAllCustomer();
    boolean deleteCustomer(int customerId) throws CustomerNotFoundException;
}