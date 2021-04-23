package br.com.poatransporte.unittests.service;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.repository.LinhaRepository;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.service.LinhaService;
import br.com.poatransporte.webclient.LinhaWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static br.com.poatransporte.helper.LinhaDtoHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LinhaServiceTest {

  private LinhaWebClient linhaWebClient;
  private LinhaConverter linhaConverter = new LinhaConverter();
  private LinhaRepository linhaRepository;
  private BaseService linhaService;

  @BeforeEach
  void setUp() {
    linhaRepository = mock(LinhaRepository.class);
    linhaService = new LinhaService(linhaRepository, linhaConverter);
  }

  @Test
  void shouldReturnExaclyWhatLinhaWebClientReturn() {
    when(linhaWebClient.getLinhas()).thenReturn(Flux.just(build()));
    Flux<LinhaDto> linhas = linhaService.findAll();
    LinhaDto actual = linhas.blockFirst();

    assertNotNull(actual);
    assertEquals(ID, actual.getId());
    assertEquals(CODIGO, actual.getCodigo());
    assertEquals(NOME, actual.getNome());
  }
}