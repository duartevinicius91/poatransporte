package br.com.poatransporte.webclient;

import br.com.poatransporte.dto.LinhaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.client.WebClient.Builder;

@Component
public class LinhaWebClient {

  private final WebClient webClient;

  public LinhaWebClient(@Value("${poa-transporte.linha-endpoint.url}") String linhaEndPoint,
                        Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl(linhaEndPoint).build();
  }

  public Flux<LinhaDto> getLinhas() {
    return webClient.get()
        .retrieve()
        .bodyToFlux(LinhaDto.class)
        ;

  }
}
