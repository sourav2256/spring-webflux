package com.sourav.webflux.functionalEndpoint.handler;

import com.sourav.webflux.restEndpoint.dao.CustomerDao;
import com.sourav.webflux.restEndpoint.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> allCustomers = customerDao.getAllCustomers();
        return ServerResponse.ok().body(allCustomers, Customer.class);
    }

    public Mono<ServerResponse> findCustomers(ServerRequest request) {
        int customerID = Integer.parseInt(request.pathVariable("input"));
        Mono<Customer> customerMono = customerDao.getAllCustomers().filter(custId -> custId.getId() == customerID).next();
        //customerDao.getAllCustomers().filter(custId -> custId.getId() == customerID).take(1).single();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomers(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
