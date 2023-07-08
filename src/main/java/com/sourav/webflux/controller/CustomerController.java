package com.sourav.webflux.controller;

import com.sourav.webflux.dto.Customer;
import com.sourav.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerService.loadAllCustomers();
    }
    @GetMapping("/stream")
    public Flux<Customer> getAllCustomersStream() {
        return customerService.loadAllCustomersStream();
    }

}
