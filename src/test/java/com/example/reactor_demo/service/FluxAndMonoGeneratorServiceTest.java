package com.example.reactor_demo.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        //given

        //when
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFlux();

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

    @Test
    void namesFluxMap() {
        //gien
        int stringLength = 3;

        //when
        Flux<String> namesFluxMap = fluxAndMonoGeneratorService.namesFluxMap(stringLength);

        //then
        StepVerifier.create(namesFluxMap)
//                .expectNext("ALEX", "BEN", "CHLOE")
                .expectNext("4 - ALEX", "5 - CHLOE")
                .verifyComplete();

    }

    @Test
    void namesFluxImmutability() {
        //gien

        //when
        Flux<String> namesFluxMap = fluxAndMonoGeneratorService.namesFluxImmutability();

        //then
        StepVerifier.create(namesFluxMap)
                .expectNext("alex", "ben", "chloe")
                .verifyComplete();

    }

    @Test
    void nameMonoFilter() {
        //given

        //when
        Mono<String> nameMonoFilter = fluxAndMonoGeneratorService.nameMonoFilter(3);

        //then
        StepVerifier.create(nameMonoFilter)
                .expectNext("4 - ALEX")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        //given
        int stringLength = 3;

        //when
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap(stringLength);

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();

    }

    @Test
    void namesFluxFlatMapAsync() {
        //given
        int stringLength = 3;

        //when
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMapAsync(stringLength);

        //then
        StepVerifier.create(namesFlux)
                .expectNextCount(9)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();

    }

    @Test
    void namesFluxConcatMap() {
        //given
        int stringLength = 3;

        //when
        Flux<String> namesFlux = fluxAndMonoGeneratorService.namesFluxConcatMap(stringLength);

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMap() {
        //given
        int stringLength = 3;

        //when
        Mono<List<String>> nameMonoFlatMap = fluxAndMonoGeneratorService.nameMonoFlatMap(stringLength);

        //then
        StepVerifier.create(nameMonoFlatMap)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapMany() {
        //given
        int stringLength = 3;

        //when
        Flux<String> nameMonoFlatMap = fluxAndMonoGeneratorService.nameMonoFlatMapMany(stringLength);

        //then
        StepVerifier.create(nameMonoFlatMap)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void namesFluxTransform() {
        //given
        int stringLength = 3;

        //when
        Flux<String> nameFluxTransform = fluxAndMonoGeneratorService.namesFluxTransform(stringLength);

        //then
        StepVerifier.create(nameFluxTransform)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();

    }

    @Test
    void namesFluxTransform2() {
        //given
        int stringLength = 6;

        //when
        Flux<String> nameFluxTransform = fluxAndMonoGeneratorService.namesFluxTransform(stringLength);

        //then
        StepVerifier.create(nameFluxTransform)
                .expectNext("default")
                .verifyComplete();

    }

    @Test
    void namesFluxTransformSwitchIfEmpty() {
        //given
        int stringLength = 6;

        //when
        Flux<String> nameFluxTransform = fluxAndMonoGeneratorService.namesFluxTransformSwitchIfEmpty(stringLength);

        //then
        StepVerifier.create(nameFluxTransform)
                .expectNext("D", "E", "F", "A", "U", "L", "T")
                .verifyComplete();
    }
}
