package br.com.poatransporte.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseController<T> {

  Flux<T> getAll();

  Mono<T> findById(Long id);

}
