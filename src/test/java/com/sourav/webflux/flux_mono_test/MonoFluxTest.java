package com.sourav.webflux.flux_mono_test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> stringMono = Mono.just("Sourav")
                .then(Mono.error(new RuntimeException("Exception occurred")))
                .log();
        stringMono.subscribe(System.out::println,
                (err) -> System.out.println(err.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<?> stringFlux = Flux.just("Sourav", "Coder")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in Flux")))
                .concatWithValues("cloud")
                .log();
        stringFlux.subscribe(System.out::println,
                (err) -> System.out.println(err.getMessage()));
    }
}
