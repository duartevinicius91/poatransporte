package br.com.poatransporte.repository;

import br.com.poatransporte.entity.Linha;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LinhaRepository extends ReactiveCrudRepository<Linha, Long> {

  Mono<Linha> findByCodigo(String codigo);

  Flux<Linha> findByNomeContaining(String nome);
}
