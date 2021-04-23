package br.com.poatransporte.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseController<T> {

  Flux<T> findAll();

  Mono<T> findById(Long id);

  Mono<Void> delete(Long id);

  Mono<T> create(T dto);

  Mono<T> update(Long id, T dto);
}
