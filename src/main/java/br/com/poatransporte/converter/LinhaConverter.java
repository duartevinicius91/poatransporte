package br.com.poatransporte.converter;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static java.util.Objects.isNull;

@Component
public class LinhaConverter {

  public Mono<Linha> toEntity(LinhaDto linhaDto) {
    if (isNull(linhaDto)) {
      return null;
    }

    Linha entity = new Linha();
    entity.setId(linhaDto.getId());
    entity.setNome(linhaDto.getNome());
    entity.setCodigo(linhaDto.getCodigo());
    return Mono.just(entity);
  }

  public Mono<LinhaDto> toDto(Linha linha) {
    if (isNull(linha)) {
      return null;
    }

    LinhaDto linhaDto = new LinhaDto();
    linhaDto.setNome(linha.getNome());
    linhaDto.setId(linha.getId());
    linhaDto.setCodigo(linha.getCodigo());
    return Mono.just(linhaDto);
  }
}
