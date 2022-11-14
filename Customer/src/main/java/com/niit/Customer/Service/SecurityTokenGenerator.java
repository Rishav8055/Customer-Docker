package com.niit.Customer.Service;

import com.niit.Customer.Domain.Customer;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(Customer customer);
}
