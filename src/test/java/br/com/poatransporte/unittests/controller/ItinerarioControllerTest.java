package br.com.poatransporte.unittests.controller;

import br.com.poatransporte.controller.BaseController;
import br.com.poatransporte.controller.ItinerarioController;
import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.service.ItinerarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.com.poatransporte.helper.ItinerarioHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItinerarioControllerTest {
  private BaseController<ItinerarioDto> itinerarioController;
  private BaseService<ItinerarioDto> itinerarioService;

  @BeforeEach
  void setUp() {
    itinerarioService = mock(ItinerarioService.class);
    itinerarioController = new ItinerarioController(itinerarioService);
  }

  @Test
  void shoudReturnExaclyWhatServiceReturns() {
    when(itinerarioService.findAll()).thenReturn(Flux.just(buildDto()));
    Flux<ItinerarioDto> findAll = itinerarioController.findAll("anyString()");
    ItinerarioDto actual = findAll.blockFirst();

    assertNotNull(actual);
    assertEquals(LNG, actual.getLng());
    assertEquals(LAT, actual.getLat());
    assertEquals(ID, actual.getIdLinha());
  }

  @Test
  void shouldReturnLinhaWhenFindById() {
    when(itinerarioService.findById(anyLong())).thenReturn(Mono.just(buildDto()));

    ItinerarioDto actual = itinerarioController.findById(1L).block();
    assertNotNull(actual);
    assertEquals(LNG, actual.getLng());
    assertEquals(LAT, actual.getLat());
    assertEquals(ID, actual.getIdLinha());
  }

  @Test
  void shouldReturnExaclyWhatLinhaWebClientReturn() {
    when(itinerarioService.findAll()).thenReturn(Flux.just(buildDto()));

    ItinerarioDto actual = itinerarioService.findAll().blockFirst();

    assertNotNull(actual);
    assertEquals(LNG, actual.getLng());
    assertEquals(LAT, actual.getLat());
    assertEquals(ID, actual.getIdLinha());
  }

  @Test
  void shouldReturnVoidMonoWhenDeleteLinha() {
    when(itinerarioService.delete(anyLong())).thenReturn(Mono.empty());

    Void actual = itinerarioController.delete(1L).block();

    assertNull(actual);
  }

  @Test
  void whenCreateShouldReturnServiceCreateReturnMethod() {
    ItinerarioDto dto = buildDto();
    when(itinerarioService.create(any(ItinerarioDto.class))).thenReturn(Mono.just(dto));

    ItinerarioDto actual = itinerarioController.create(dto).block();

    assertNotNull(actual);
    assertEquals(ID, actual.getIdLinha());
    assertEquals(LAT, actual.getLat());
    assertEquals(LNG, actual.getLng());
  }

  @Test
  void whenUpdateShouldReturnServiceUpdateReturnMethod() {
    when(itinerarioService.update(anyLong(), any(ItinerarioDto.class))).thenReturn(Mono.just(buildDto()));

    ItinerarioDto actual = itinerarioController.update(1L, buildDto()).block();

    assertNotNull(actual);
    assertEquals(ID, actual.getIdLinha());
    assertEquals(LAT, actual.getLat());
    assertEquals(LNG, actual.getLng());
  }


}