package com.sourav.webflux.functionalEndpoint.router;

import com.sourav.webflux.functionalEndpoint.handler.CustomerHandler;
import com.sourav.webflux.functionalEndpoint.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler customerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream", customerStreamHandler::loadCustomers)
                .GET("/router/customers/{input}", handler::findCustomers)
                .POST("/router/customer/save", handler::saveCustomers)
                .build();
    }
}
