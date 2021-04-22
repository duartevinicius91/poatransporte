package br.com.poatransporte.converter;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinhaConverterTest {

  public static final String NOME = "NOME";
  public static final long ID = 1L;
  public static final String CODIGO = "CODIGO";

  private LinhaConverter linhaConverter = new LinhaConverter();

  @Test
  public void convertToEntity() {
    Linha linha = linhaConverter.toEntity(build());

    assertNotNull(linha);
    assertEquals(CODIGO, linha.getCodigo());
    assertEquals(ID, linha.getId());
    assertEquals(NOME, linha.getNome());

  }

  private LinhaDto build() {
    LinhaDto linhaDto = new LinhaDto();
    linhaDto.setCodigo(CODIGO);
    linhaDto.setId(ID);
    linhaDto.setNome(NOME);
    return linhaDto;
  }
}