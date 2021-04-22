package br.com.poatransporte.service;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import br.com.poatransporte.webclient.LinhaWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class LinhaService implements BaseService<LinhaDto> {

  private final LinhaWebClient linhaWebClient;

  @Autowired
  public LinhaService(LinhaWebClient linhaWebClient) {
    this.linhaWebClient = linhaWebClient;
  }

  public Flux<LinhaDto> findAll() {
    return linhaWebClient.getLinhas();
  }
}
