package com.sourav.webflux.functionalEndpoint.handler;

import com.sourav.webflux.restEndpoint.dao.CustomerDao;
import com.sourav.webflux.restEndpoint.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> allCustomers = customerDao.getAllCustomersStream();
        return ServerResponse.ok()
                //.contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allCustomers, Customer.class);
    }
}
