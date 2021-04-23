package br.com.poatransporte.service;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.repository.LinhaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LinhaService implements BaseService<LinhaDto> {

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
}
