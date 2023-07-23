package com.example.reactor_demo.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        //given

        //when
        Flux<String> namesFlux = fluxAndMonoGeneratorService.nameFlux();

        //then
        StepVerifier.create(namesFlux)
//                .expectNext("alex", "ben", "chloe")
//                .expectNextCount(3)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();

    }

    @Test
    void namesMono() {
        //given

        //when
        Mono<String> namesMono = fluxAndMonoGeneratorService.nameMono();

        //then
        StepVerifier.create(namesMono)
                .expectNext("alex")
                .expectNextCount(0)
                .verifyComplete();
    }

}
