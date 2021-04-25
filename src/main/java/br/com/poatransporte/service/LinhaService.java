package br.com.poatransporte.service;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import br.com.poatransporte.repository.LinhaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.apache.commons.lang3.StringUtils.upperCase;

@Service
public class LinhaService implements BaseService<LinhaDto> {

  public static final String LINHA_NAO_LOCALIZADA = "Linha com id %s não localizada";
  private final LinhaRepository repository;
  private final LinhaConverter converter;

  public LinhaService(LinhaRepository repository, LinhaConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  @Override
  public Flux<LinhaDto> findAll() {
    return repository.findAll().map(converter::toDto);
  }

  @Override
  public Flux<LinhaDto> findByNome(String nome) {
    return repository.findByNomeContaining(upperCase(nome)).map(converter::toDto);
  }

  @Override
  public Mono<LinhaDto> findById(Long id) {
    return repository.findById(id).map(converter::toDto);
  }

  @Override
  public Mono<Void> delete(Long id) {
    return repository.deleteById(id);
  }

  @Override
  public Mono<LinhaDto> create(LinhaDto dto) {
    return repository.findByCodigo(dto.getCodigo())
        .doOnNext(linha -> {
          linha.setNome(dto.getNome());
          linha.setCodigo(dto.getCodigo());
        })
        .switchIfEmpty(Mono.defer(() ->  {
          Linha entity = converter.toEntity(dto);
          entity.setAsNew();
          return Mono.just(entity);
        }))
        .flatMap(repository::save)
        .map(converter::toDto)
        ;
  }

  @Override
  public Mono<LinhaDto> update(Long id, LinhaDto dto) {
    return repository.findById(id)
        .switchIfEmpty(Mono.defer(() -> Mono.error(
            new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(LINHA_NAO_LOCALIZADA, id))
        )))
        .doOnNext(linha -> {
          linha.setNome(dto.getNome());
          linha.setCodigo(dto.getCodigo());
        })
        .flatMap(linha -> repository.save(linha))
        .map(linha -> converter.toDto(linha));
  }

}
