package br.com.poatransporte.repository;

import br.com.poatransporte.entity.Itinerario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ItinerarioRepository extends ReactiveCrudRepository<Itinerario, Long> {

  Mono<Itinerario> findByIdLinhaAndLatAndLng(Long idLinha, BigDecimal lat, BigDecimal lng);

}
