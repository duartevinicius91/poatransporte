package br.com.poatransporte.unittests.converter;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.entity.Linha;
import org.junit.jupiter.api.Test;

import static br.com.poatransporte.helper.LinhaDtoHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LinhaConverterTest {

  private LinhaConverter linhaConverter = new LinhaConverter();

  @Test
  public void convertToEntity() {
    Linha linha = linhaConverter.toEntity(build());

    assertNotNull(linha);
    assertEquals(CODIGO, linha.getCodigo());
    assertEquals(ID, linha.getId());
    assertEquals(NOME, linha.getNome());

  }
}