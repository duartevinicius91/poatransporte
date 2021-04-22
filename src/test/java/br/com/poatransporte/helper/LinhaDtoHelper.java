package br.com.poatransporte.helper;

import br.com.poatransporte.dto.LinhaDto;

public class LinhaDtoHelper {

  public static final String NOME = "NOME";
  public static final long ID = 1L;
  public static final String CODIGO = "CODIGO";

  public static LinhaDto build() {
    LinhaDto linhaDto = new LinhaDto();
    linhaDto.setCodigo(CODIGO);
    linhaDto.setId(ID);
    linhaDto.setNome(NOME);
    return linhaDto;
  }
}
