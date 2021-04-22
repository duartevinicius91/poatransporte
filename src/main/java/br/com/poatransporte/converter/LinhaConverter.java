package br.com.poatransporte.converter;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import org.springframework.stereotype.Component;

@Component
public class LinhaConverter {

  public Linha toEntity(LinhaDto linhaDto) {
    Linha entity = new Linha();
    entity.setId(linhaDto.getId());
    entity.setNome(linhaDto.getNome());
    entity.setCodigo(linhaDto.getCodigo());
    return entity;
  }
}
