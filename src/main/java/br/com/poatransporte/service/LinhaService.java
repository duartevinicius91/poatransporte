package br.com.poatransporte.service;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.webclient.LinhaWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class LinhaService {

  private final LinhaWebClient linhaWebClient;

  @Autowired
  public LinhaService(LinhaWebClient linhaWebClient) {
    this.linhaWebClient = linhaWebClient;
  }

  public Flux<LinhaDto> getLinhas() {
    return linhaWebClient.getLinhas();
  }
}
