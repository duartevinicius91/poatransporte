package br.com.poatransporte.webclient;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.helper.WebClientTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static br.com.poatransporte.helper.LinhaDtoHelper.build;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LinhaWebClientTest extends WebClientTestHelper {

  private LinhaWebClient linhaWebClient;

  @BeforeEach
  void setUp() {
    linhaWebClient = new LinhaWebClient(anyString(), getBuilder());
  }

  @Test
  void shouldReturnExaclyWhatWebClientReturn() {
    mockWebClientReturn(Flux.just(build()));

    Flux<LinhaDto> actual = linhaWebClient.getLinhas();

    assertNotNull(actual);

  }

}