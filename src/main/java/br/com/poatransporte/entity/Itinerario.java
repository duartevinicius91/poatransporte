package br.com.poatransporte.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Data
public class Itinerario implements Persistable<Long> {
  @Id
  private Long id;
  private Long idLinha;
  private BigDecimal lat;
  private BigDecimal lng;

  @Transient
  private boolean isNew;

  @Override
  @Transient
  public boolean isNew() {
    return isNew || isNull(id);
  }

  public void setAsNew() {
    this.isNew = true;
  }
}
