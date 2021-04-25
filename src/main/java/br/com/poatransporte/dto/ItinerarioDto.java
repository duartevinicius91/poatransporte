package br.com.poatransporte.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItinerarioDto {
  private Long id;
  private Long idLinha;
  private BigDecimal lat;
  private BigDecimal lng;
}
