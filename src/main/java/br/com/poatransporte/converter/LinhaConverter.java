package br.com.poatransporte.converter;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class LinhaConverter {

  public Linha toEntity(LinhaDto linhaDto) {
    if (isNull(linhaDto)) {
      return null;
    }

    Linha entity = new Linha();
    entity.setId(linhaDto.getId());
    entity.setNome(linhaDto.getNome());
    entity.setCodigo(linhaDto.getCodigo());
    return entity;
  }

  public LinhaDto toDto(Linha linha) {
    if (isNull(linha)) {
      return null;
    }

    LinhaDto linhaDto = new LinhaDto();
    linhaDto.setNome(linha.getNome());
    linhaDto.setId(linha.getId());
    linhaDto.setCodigo(linha.getCodigo());
    return linhaDto;
  }
}
