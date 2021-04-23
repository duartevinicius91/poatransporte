package br.com.poatransporte.helper;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class WebClientTestHelper {

  static WebClient webClient;
  static WebClient.Builder builder;

  @BeforeAll
  static void beforeAll() {
    builder = mock(WebClient.Builder.class);
    webClient = mock(WebClient.class);

    when(builder.baseUrl(anyString()))
        .thenReturn(builder);
    when(builder.build())
        .thenReturn(webClient);
  }

  public WebClient getWebClient() {
    return webClient;
  }

  public WebClient.Builder getBuilder() {
    return builder;
  }

  public void mockWebClientReturn(Flux just) {
    var requestHeadersUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
    when(webClient.get()).thenReturn(requestHeadersUriSpec);

    var responseSpec = mock(WebClient.ResponseSpec.class);
    when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);

    when(responseSpec.bodyToFlux(any(Class.class))).thenReturn(just);
  }

}
