package br.com.poatransporte.service;

import reactor.core.publisher.Flux;

public interface BaseService <T> {

  Flux<T> findAll();
}
