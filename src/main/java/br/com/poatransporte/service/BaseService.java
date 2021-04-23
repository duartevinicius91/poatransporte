package br.com.poatransporte.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService <T> {

  Flux<T> findAll();

  Mono<T> findById(Long id);

  Mono<Void> delete(Long id);
}
