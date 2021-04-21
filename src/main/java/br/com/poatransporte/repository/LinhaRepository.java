package br.com.poatransporte.repository;

import br.com.poatransporte.entity.Linha;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LinhaRepository extends ReactiveCrudRepository<Linha, Long> {

}
