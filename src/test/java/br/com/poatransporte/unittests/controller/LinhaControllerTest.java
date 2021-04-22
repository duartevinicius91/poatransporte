package br.com.poatransporte.unittests.controller;

import br.com.poatransporte.controller.BaseController;
import br.com.poatransporte.controller.LinhaController;
import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.service.LinhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.com.poatransporte.helper.LinhaDtoHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LinhaControllerTest {

  private BaseController<LinhaDto> linhaController;
  private BaseService<LinhaDto> linhaService;

  @BeforeEach
  void setUp() {
    linhaService = mock(LinhaService.class);
    linhaController = new LinhaController(linhaService);
  }

  @Test
  void shoudReturnExaclyWhatServiceReturns() {
    when(linhaService.findAll()).thenReturn(Flux.just(build()));
    Flux<LinhaDto> findAll = linhaController.findAll();
    LinhaDto actual = findAll.blockFirst();

    assertNotNull(actual);
    assertEquals(NOME, actual.getNome());
    assertEquals(CODIGO, actual.getCodigo());
    assertEquals(ID, actual.getId());
  }

  @Test
  void killNullReturnMutationTest() {
    Mono<LinhaDto> byId = linhaController.findById(1L);

    assertNotNull(byId);
  }
}