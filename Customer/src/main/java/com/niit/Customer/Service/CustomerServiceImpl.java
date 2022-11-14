package com.niit.Customer.Service;

import com.niit.Customer.CustomerRepo.CustomerRepo;
import com.niit.Customer.Domain.Customer;
import com.niit.Customer.Exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer findByUserNameAndPassword(String userName, String password) throws CustomerNotFoundException {
        Customer customer = customerRepo.findByUserNameAndPassword(userName,password);
        if (customer==null){
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {

            if (customerRepo.findById(customerId).isEmpty()) {
                throw new CustomerNotFoundException();
            } else {
                customerRepo.deleteById(customerId);
                return true;
            }

    }
}
