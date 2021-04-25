package br.com.poatransporte.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseService<T> {

  Flux<T> findAll();

  Flux<T> findByNome(String nome);

  Mono<T> findById(Long id);

  Mono<Void> delete(Long id);

  Mono<T> create(T dto);

  Mono<T> update(Long id, T dto);

}
