package br.com.poatransporte.unittests.converter;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import org.junit.jupiter.api.Test;

import static br.com.poatransporte.helper.LinhaHelper.*;
import static org.junit.jupiter.api.Assertions.*;

class LinhaConverterTest {

  private LinhaConverter linhaConverter = new LinhaConverter();

  @Test
  void convertToEntity() {
    Linha linha = linhaConverter.toEntity(buildDto());

    assertNotNull(linha);
    assertEquals(buildEntity(), linha);
  }

  @Test
  void convertToDto() {
    LinhaDto linha = linhaConverter.toDto(buildEntity());

    assertNotNull(linha);
    assertEquals(buildDto(), linha);
  }

  @Test
  void shouldReturnNullWhenParamNull() {
    assertNull(linhaConverter.toDto(null));
    assertNull(linhaConverter.toEntity(null));
  }

}