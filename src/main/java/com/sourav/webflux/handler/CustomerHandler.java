package com.sourav.webflux.handler;

import com.sourav.webflux.dao.CustomerDao;
import com.sourav.webflux.dto.Customer;
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
}
