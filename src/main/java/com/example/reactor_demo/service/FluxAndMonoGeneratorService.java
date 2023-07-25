package com.example.reactor_demo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .log();
    }

    public Mono<String> nameMono() {
        return Mono.just("alex")
                .log();
    }

    public Flux<String> namesFluxMap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + " - " + s)
                .log();
    }

    public Flux<String> namesFluxImmutability() {
        Flux<String> namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase).log();
        return namesFlux;
    }

    public Mono<String> nameMonoFilter(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + " - " + s)
                .log();
    }

    public Mono<List<String>> nameMonoFlatMap(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono)
                .log();
    }

    public Flux<String> nameMonoFlatMapMany(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString)
                .log();
    }

    private Mono<List<String>> splitStringMono(String name) {
        return Mono.just(List.of(name.split("")));
    }

    public Flux<String> namesFluxFlatMap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> namesFluxFlatMapAsync(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> namesFluxConcatMap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .concatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> namesFluxTransform(int stringLength) {

        Function<Flux<String>, Flux<String>> filterMap = name ->
                name
                        .map(String::toUpperCase)
                        .filter(s -> s.length() > stringLength);

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .flatMap(this::splitString)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> namesFluxTransformSwitchIfEmpty(int stringLength) {

        Function<Flux<String>, Flux<String>> filterMap = name ->
                name
                        .map(String::toUpperCase)
                        .filter(s -> s.length() > stringLength);


        Flux<String> defaultFlux = Flux.just("default")
                .transform(filterMap)
                .flatMap(this::splitString);

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    // ALEX -> Flux(A, L, E, X)
    public Flux<String> splitString(String name) {
        return Flux.fromArray(name.split(""));
    }

    public Flux<String> splitStringWithDelay(String name) {
        int delay = new SecureRandom().nextInt(1000);
        return Flux.fromArray(name.split(""))
                .delayElements(Duration.ofMillis(delay));
    }

    public static void main(String[] args) {

        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> System.out.println("Name is : " + name));

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> System.out.println("Name is : " + name));

    }

}
