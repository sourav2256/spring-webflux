package com.sourav.webflux.dao;

import com.sourav.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(id -> System.out.println("processing count : " + id))
                .mapToObj(id -> new Customer(id, "customer" + id))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getAllCustomersStream() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(id -> System.out.println("processing count in stream flow : " + id))
                .map(id -> new Customer(id, "customer" + id));
    }
}
