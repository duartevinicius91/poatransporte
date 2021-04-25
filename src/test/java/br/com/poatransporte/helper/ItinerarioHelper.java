package br.com.poatransporte.helper;

import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.entity.Itinerario;

import java.math.BigDecimal;

public class ItinerarioHelper {

  public static final BigDecimal LAT = BigDecimal.ZERO;
  public static final BigDecimal LNG = BigDecimal.ONE;
  public static final Long ID_LINHA = 1L;
  public static final Long ID = 1L;

  public static ItinerarioDto buildDto() {
    return buildDto(ID, ID_LINHA, LAT, LNG);
  }

  public static ItinerarioDto buildDto(BigDecimal lat, BigDecimal lng)  {
    return buildDto(ID, ID_LINHA, lat, lng);
  }

  public static ItinerarioDto buildDto(Long id, BigDecimal lat, BigDecimal lng) {
    return buildDto(id, ID_LINHA, lat, lng);
  }

  public static ItinerarioDto buildDto(Long id, Long idLinha, BigDecimal lat, BigDecimal lng) {
    ItinerarioDto dto = new ItinerarioDto();
    dto.setId(id);
    dto.setLat(lat);
    dto.setLng(lng);
    dto.setIdLinha(idLinha);
    return dto;
  }

  public static Itinerario buildEntity() {
    return buildEntity(ID, ID_LINHA, LAT, LNG);
  }

  public static Itinerario buildEntity(BigDecimal lat, BigDecimal lng) {
    return buildEntity(ID, ID_LINHA, lat, lng);
  }

  public static Itinerario buildEntity(Long id, BigDecimal lat, BigDecimal lng) {
    return buildEntity(id, ID_LINHA, lat, lng);
  }

  public static Itinerario buildEntity(Long id, Long idLinha, BigDecimal lat, BigDecimal lng) {
    Itinerario entity = new Itinerario();
    entity.setId(id);
    entity.setIdLinha(idLinha);
    entity.setLat(lat);
    entity.setLng(lng);
    return entity;
  }

}
