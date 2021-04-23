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

@Service
public class LinhaService implements BaseService<LinhaDto> {

  public static final String LINHA_NAO_LOCALIZADA = "Linha com id %s não localizada";
  private final LinhaRepository linhaRepository;
  private final LinhaConverter linhaConverter;

  public LinhaService(LinhaRepository linhaRepository, LinhaConverter linhaConverter) {
    this.linhaRepository = linhaRepository;
    this.linhaConverter = linhaConverter;
  }


  public Flux<LinhaDto> findAll() {
    return linhaRepository.findAll().flatMap(linhaConverter::toDto);
  }

  @Override
  public Mono<LinhaDto> findById(Long id) {
    return linhaRepository.findById(id).flatMap(linhaConverter::toDto);
  }

  @Override
  public Mono<Void> delete(Long id) {
    return linhaRepository.deleteById(id);
  }

  @Override
  public Mono<LinhaDto> create(LinhaDto dto) {
    return linhaRepository.findById(dto.getId())
        .doOnNext(linha -> {
          linha.setNome(dto.getNome());
          linha.setCodigo(dto.getCodigo());
        })
        .switchIfEmpty(
            linhaConverter.toEntity(dto).doOnNext(Linha::setAsNew)
        )
        .flatMap(linhaRepository::save)
        .flatMap(linhaConverter::toDto);
  }

  @Override
  public Mono<LinhaDto> update(Long id, LinhaDto dto) {
    return linhaRepository.findById(id)
        .switchIfEmpty(Mono.error(
            new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(LINHA_NAO_LOCALIZADA, id))
        ))
        .doOnNext(linha -> {
          linha.setNome(dto.getNome());
          linha.setCodigo(dto.getCodigo());
        })
        .flatMap(linhaRepository::save)
        .flatMap(linhaConverter::toDto);
  }
}
