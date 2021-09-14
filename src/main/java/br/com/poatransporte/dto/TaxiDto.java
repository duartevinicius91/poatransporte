package br.com.poatransporte.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TaxiDto {
  private String nomeDoPonto;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private LocalDateTime dataHoraCadastro;
}
