package br.com.poatransporte.webclient;

import br.com.poatransporte.converter.ItinerarioWebClientConverter;
import br.com.poatransporte.dto.ItinerarioDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class ItinerarioWebClient {
  private final WebClient.Builder webClientBuilder;
  private final String itinerarioEndpoint;
  private final Map<Long, WebClient> webclients;
  private final ItinerarioWebClientConverter itinerarioWebClientConverter;


    public ItinerarioWebClient(@Value("${poa-transporte.itinerario-endpoint.url}") String itinerarioEndpoint,
                               WebClient.Builder webClientBuilder,
                               ItinerarioWebClientConverter itinerarioWebClientConverter) {
      this.webClientBuilder = webClientBuilder;
      this.itinerarioEndpoint = itinerarioEndpoint;
      this.itinerarioWebClientConverter = itinerarioWebClientConverter;
      webclients = new HashMap<>();
  }

  public Flux<ItinerarioDto> getItinerarios(Long idLinha) {
    Objects.requireNonNull(idLinha);

    return getWebClientFor(idLinha)
            .map(webClient -> webClient.get()
                .retrieve()
                .bodyToFlux(Map.class)
                .flatMap(itinerarioWebClientConverter::convert)
                .doOnNext(itinerarioDto -> ((ItinerarioDto) itinerarioDto).setIdLinha(idLinha))
            )
            .orElseThrow();
  }

  private Optional<WebClient> getWebClientFor(Long idLinha) {

    return Optional.ofNullable(webclients.get(idLinha))
            .or(() -> buildWebClient(idLinha));

  }

  private Optional<WebClient> buildWebClient(Long idLinha) {
    WebClient webClient = webClientBuilder.baseUrl(String.format(itinerarioEndpoint, idLinha)).build();
    webclients.put(idLinha, webClient);
    return Optional.of(webClient);
  }
}
