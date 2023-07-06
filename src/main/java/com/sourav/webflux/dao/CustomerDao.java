package com.sourav.webflux.dao;

import com.sourav.webflux.dto.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 50)
                .peek(id -> System.out.println("processing count" + id))
                .mapToObj(id -> new Customer(id, "customer" + id))
                .collect(Collectors.toList());
    }
}
