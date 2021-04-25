package br.com.poatransporte.converter;

import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.entity.Itinerario;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ItinerarioConverter {

  public Itinerario toEntity(ItinerarioDto dto) {
    if (isNull(dto)) {
      return null;
    }

    Itinerario entity = new Itinerario();
    entity.setId(dto.getId());
    entity.setLat(dto.getLat());
    entity.setLng(dto.getLng());
    entity.setIdLinha(dto.getIdLinha());
    return entity;
  }

  public ItinerarioDto toDto(Itinerario entity) {
    if (isNull(entity)) {
      return null;
    }

    ItinerarioDto dto = new ItinerarioDto();
    dto.setId(entity.getId());
    dto.setLat(entity.getLat());
    dto.setLng(entity.getLng());
    dto.setIdLinha(entity.getIdLinha());
    return dto;
  }
}
