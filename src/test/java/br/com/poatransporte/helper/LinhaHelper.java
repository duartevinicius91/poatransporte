package br.com.poatransporte.helper;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;

public class LinhaHelper {

  public static final String NOME = "NOME";
  public static final long ID = 1L;
  public static final String CODIGO = "CODIGO";

  public static LinhaDto buildDto() {
    LinhaDto linhaDto = new LinhaDto();
    linhaDto.setCodigo(CODIGO);
    linhaDto.setId(ID);
    linhaDto.setNome(NOME);
    return linhaDto;
  }

  public static Linha buildEntity() {
    Linha linhaDto = new Linha();
    linhaDto.setCodigo(CODIGO);
    linhaDto.setId(ID);
    linhaDto.setNome(NOME);
    return linhaDto;
  }
}
