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

import static br.com.poatransporte.helper.LinhaHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
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
    when(linhaService.findAll()).thenReturn(Flux.just(buildDto()));
    Flux<LinhaDto> findAll = linhaController.findAll();
    LinhaDto actual = findAll.blockFirst();

    assertNotNull(actual);
    assertEquals(NOME, actual.getNome());
    assertEquals(CODIGO, actual.getCodigo());
    assertEquals(ID, actual.getId());
  }

  @Test
  void shouldReturnLinhaWhenFindById() {
    when(linhaService.findById(anyLong())).thenReturn(Mono.just(buildDto()));

    LinhaDto actual = linhaController.findById(1L).block();
    assertNotNull(actual);
    assertEquals(NOME, actual.getNome());
    assertEquals(CODIGO, actual.getCodigo());
    assertEquals(ID, actual.getId());
  }

  @Test
  void shouldReturnExaclyWhatLinhaWebClientReturn() {
    when(linhaService.findAll()).thenReturn(Flux.just(buildDto()));

    LinhaDto actual = linhaService.findAll().blockFirst();

    assertNotNull(actual);
    assertEquals(NOME, actual.getNome());
    assertEquals(CODIGO, actual.getCodigo());
    assertEquals(ID, actual.getId());
  }

  @Test
  void shouldReturnVoidMonoWhenDeleteLinha() {
    when(linhaService.delete(anyLong())).thenReturn(Mono.empty());

    Void actual = linhaController.delete(1L).block();

    assertNull(actual);
  }

}