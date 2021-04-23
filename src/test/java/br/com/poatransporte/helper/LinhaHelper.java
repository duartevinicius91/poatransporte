package br.com.poatransporte.helper;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;

public class LinhaHelper {

  public static final String NOME = "NOME";
  public static final long ID = 1L;
  public static final String CODIGO = "CODIGO";

  public static LinhaDto buildDto() {
    return buildDto(ID, CODIGO, NOME);
  }

  public static LinhaDto buildDto(String codigo, String nome)  {
    return buildDto(ID, codigo, nome);
  }

  public static LinhaDto buildDto(Long id, String codigo, String nome) {
    LinhaDto linha = new LinhaDto();
    linha.setId(id);
    linha.setCodigo(codigo);
    linha.setNome(nome);
    return linha;
  }

  public static Linha buildEntity() {
    return buildEntity(ID, CODIGO, NOME);
  }

  public static Linha buildEntity(String codigo, String nome) {
    return buildEntity(ID, codigo, nome);
  }

  public static Linha buildEntity(Long id, String codigo, String nome) {
    Linha linha = new Linha();
    linha.setId(id);
    linha.setCodigo(codigo);
    linha.setNome(nome);
    return linha;
  }
}
