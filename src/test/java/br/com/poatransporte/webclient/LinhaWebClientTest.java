package br.com.poatransporte.webclient;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.helper.WebClientTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static br.com.poatransporte.helper.LinhaHelper.buildDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class LinhaWebClientTest extends WebClientTestHelper {

  private LinhaWebClient linhaWebClient;

  @BeforeEach
  void setUp() {
    linhaWebClient = new LinhaWebClient(anyString(), getBuilder());
  }

  @Test
  void shouldReturnExaclyWhatWebClientReturn() {
    mockWebClientReturn(Flux.just(buildDto()));

    Flux<LinhaDto> actual = linhaWebClient.getLinhas();

    assertNotNull(actual);

  }

}