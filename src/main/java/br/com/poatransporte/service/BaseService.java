package br.com.poatransporte.service;

import reactor.core.publisher.Flux;

public interface BaseService <T> {

  public Flux<T> findAll();
}
