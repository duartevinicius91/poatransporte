package br.com.poatransporte.service;

import br.com.poatransporte.converter.ItinerarioConverter;
import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.entity.Itinerario;
import br.com.poatransporte.repository.ItinerarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItinerarioService implements BaseService<ItinerarioDto> {

  public static final String ITINERARIO_NAO_LOCALIZADO = "Itinerario %s não localizado. ";
  private final ItinerarioRepository repository;
  private final ItinerarioConverter converter;
  
  public ItinerarioService(ItinerarioRepository itinerarioRepository, ItinerarioConverter converter) {
    this.repository = itinerarioRepository;
    this.converter = converter;
  }

  @Override
  public Flux<ItinerarioDto> findAll() {
    return repository.findAll().map(converter::toDto);
  }

  @Override
  public Flux<ItinerarioDto> findByNome(String nome) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Mono<ItinerarioDto> findById(Long id) {
    return repository.findById(id).map(converter::toDto);
  }
  
  @Override
  public Mono<Void> delete(Long id) {
    return repository.deleteById(id);
  }

  @Override
  public Mono<ItinerarioDto> create(ItinerarioDto dto) {
    return repository.findByIdLinhaAndLatAndLng(dto.getIdLinha(), dto.getLat(), dto.getLng())
        .switchIfEmpty(Mono.defer(() -> {
          Itinerario entity = converter.toEntity(dto);
          entity.setAsNew();
          return repository.save(entity);
        }))
        .map(converter::toDto);
  }

  @Override
  public Mono<ItinerarioDto> update(Long id, ItinerarioDto dto) {
    return repository.findById(id)
        .switchIfEmpty(Mono.defer(() -> Mono.error(
            new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(ITINERARIO_NAO_LOCALIZADO, id))
        )))
        .doOnNext(entity -> {
          entity.setLng(dto.getLng());
          entity.setLat(dto.getLat());
          entity.setIdLinha(dto.getIdLinha());
        })
        .flatMap(repository::save)
        .map(converter::toDto);
  }


}
