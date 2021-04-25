package br.com.poatransporte.unittests.converter;

import br.com.poatransporte.converter.ItinerarioConverter;
import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.entity.Itinerario;
import org.junit.jupiter.api.Test;

import static br.com.poatransporte.helper.ItinerarioHelper.buildDto;
import static br.com.poatransporte.helper.ItinerarioHelper.buildEntity;
import static org.junit.jupiter.api.Assertions.*;

class ItinerarioConverterTest {

  private ItinerarioConverter converter = new ItinerarioConverter();
  @Test
  void convertToEntity() {
    Itinerario entity = converter.toEntity(buildDto());

    assertNotNull(entity);
    assertEquals(buildEntity(), entity);
  }

  @Test
  void convertToDto() {
    ItinerarioDto dto = converter.toDto(buildEntity());

    assertNotNull(dto);
    assertEquals(buildDto(), dto);
  }

  @Test
  void shouldReturnNullWhenParamNull() {
    assertNull(converter.toDto(null));
    assertNull(converter.toEntity(null));
  }
}