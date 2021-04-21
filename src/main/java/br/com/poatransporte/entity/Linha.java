package br.com.poatransporte.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import static java.util.Objects.isNull;

@Data
public class Linha implements Persistable<Long> {
  @Id
  private Long id;
  private String codigo;
  private String nome;

  @Transient
  private boolean isNew;

  @Override
  @Transient
  public boolean isNew() {
    return isNew || isNull(id);
  }

  public Linha setAsNew() {
    this.isNew = true;
    return this;
  }
}
