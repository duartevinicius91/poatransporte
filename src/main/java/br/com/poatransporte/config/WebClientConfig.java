package br.com.poatransporte.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebClientConfig {

  @Value("${poa-transporte.linha-endpoint.url}")
  private String linhaEndPoint;

  @Autowired
  private ObjectMapper mapper;

  @Bean
  public WebClient linhaWebClient() {
    ExchangeStrategies strategies = strategies();

    return
      WebClient.builder()
        .exchangeStrategies(strategies)
        .baseUrl(linhaEndPoint)
        .build();
  }

  private ExchangeStrategies strategies() {
    return ExchangeStrategies
        .builder()
        .codecs(codecConfigurer -> {
          codecConfigurer.defaultCodecs()
              .jackson2JsonDecoder(
                  new Jackson2JsonDecoder(mapper, new MimeType(MediaType.TEXT_HTML, StandardCharsets.UTF_8))
              );
        }).build();
  }

}
