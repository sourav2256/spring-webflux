package com.sourav.webflux.restEndpoint.service;

import com.sourav.webflux.restEndpoint.dao.CustomerDao;
import com.sourav.webflux.restEndpoint.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getAllCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start));
        return customers;
    }
}
