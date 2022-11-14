package com.niit.Customer.CustomerRepo;

import com.niit.Customer.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    public Customer findByUserNameAndPassword(String userName,String password);
}
