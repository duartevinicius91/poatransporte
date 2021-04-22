package br.com.poatransporte.unittests.entity;

import br.com.poatransporte.entity.Linha;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinhaTest {

  @Test
  public void shouldReturnTrueWhenSetAsNew() {
    Linha linha = new Linha();
    linha.setAsNew();
    assertTrue(linha.isNew());
  }

  @Test
  public void defaultValueOfIsNewShouldBeTrue() {
    assertTrue(new Linha().isNew());
  }

  @Test
  public void shouldReturnFalseWhenIsNewIsFalseAndIdIsNotNull() {
    Linha linha = new Linha();
    linha.setId(1L);
    assertFalse(linha.isNew());
  }


}