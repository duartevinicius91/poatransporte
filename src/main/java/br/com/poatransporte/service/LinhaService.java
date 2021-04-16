package br.com.poatransporte.service;

import br.com.poatransporte.dto.LinhaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class LinhaService {

  private final WebClient linhaWebClient;

  @Autowired
  public LinhaService(WebClient linhaWebClient) {
    this.linhaWebClient = linhaWebClient;
  }

  public Flux<LinhaDto> getLinhas() {
    return linhaWebClient.get().retrieve().bodyToFlux(LinhaDto.class);
  }
}
