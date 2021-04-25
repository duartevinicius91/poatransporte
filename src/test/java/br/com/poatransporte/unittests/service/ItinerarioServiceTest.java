package br.com.poatransporte.unittests.service;

import br.com.poatransporte.converter.ItinerarioConverter;
import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.entity.Itinerario;
import br.com.poatransporte.repository.ItinerarioRepository;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.service.ItinerarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static br.com.poatransporte.helper.ItinerarioHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class ItinerarioServiceTest {
  public static final BigDecimal NEW_LAT = BigDecimal.valueOf(99l);
  public static final BigDecimal NEW_LNG = BigDecimal.valueOf(98l);
  public static final Long NEW_ID = 99L;
  public static final Long NEW_ID_LINHA = 97L;

  private ItinerarioConverter converter = new ItinerarioConverter();
  private ItinerarioRepository repository;
  private BaseService<ItinerarioDto> service;

  @BeforeEach
  void setUp() {
    repository = mock(ItinerarioRepository.class);
    service = new ItinerarioService(repository, converter);
  }

  @Test
  void shouldReturnExaclyWhatItinerarioWebClientReturn() {
    when(repository.findAll()).thenReturn(Flux.just(buildEntity()));
    Flux<ItinerarioDto> itinerarios = service.findAll();
    ItinerarioDto actual = itinerarios.blockFirst();

    assertNotNull(actual);
    assertEquals(ID, actual.getId());
    assertEquals(LAT, actual.getLat());
    assertEquals(LNG, actual.getLng());
  }

  @Test
  void shouldReturnItinerarioWhenFindById() {
    when(repository.findById(anyLong())).thenReturn(Mono.just(buildEntity()));

    ItinerarioDto actual = service.findById(1L).block();
    assertEquals(buildDto(), actual);
  }

  @Test
  void shouldReturnVoidMonoWhenDeleteItinerario() {
    when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

    Void block = service.delete(1L).block();
    assertNull(block);
  }

  @Test
  void whenReceiveItinerarioShouldPersist() {
    when(repository.findByIdLinhaAndLatAndLng(anyLong(), any(), any()))
        .thenReturn(Mono.just(buildEntity(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG)));

    ItinerarioDto actual = service.create(buildDto(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG)).block();

    verify(repository, times(1))
        .findByIdLinhaAndLatAndLng(anyLong(), any(), any());
    verify(repository, never())
        .save(any(Itinerario.class));
    assertEquals(buildDto(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG), actual);

  }

  @Test
  void whenReceiveNewItinerarioShouldPersist() {
    when(repository.findByIdLinhaAndLatAndLng(anyLong(), any(), any())).thenReturn(Mono.empty());
    when(repository.save(any(Itinerario.class))).thenReturn(Mono.just(buildEntity(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG)));
    ArgumentCaptor<Itinerario> argumentCaptor = ArgumentCaptor.forClass(Itinerario.class);

    ItinerarioDto actual = service.create(buildDto(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG)).block();

    verify(repository, times(1))
        .findByIdLinhaAndLatAndLng(anyLong(), any(), any());
    verify(repository, times(1))
        .save(argumentCaptor.capture());
    Itinerario value = argumentCaptor.getValue();
    assertEquals(NEW_ID, value.getId());
    assertEquals(NEW_ID_LINHA, value.getIdLinha());
    assertEquals(NEW_LNG, value.getLng());
    assertEquals(NEW_LAT, value.getLat());
    assertEquals(buildDto(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG), actual);

  }

  @Test
  void whenReceiveItinerarioShouldUpdate() {

    Mono<Itinerario> storedItinerario = Mono.just(buildEntity());
    when(repository.findById(anyLong())).thenReturn(storedItinerario);

    Mono<Itinerario> persistedItinerario = Mono.just(buildEntity(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG));
    when(repository.save(any(Itinerario.class))).thenReturn(persistedItinerario);

    ArgumentCaptor<Itinerario> argumentCaptor = ArgumentCaptor.forClass(Itinerario.class);

    ItinerarioDto updateDto = buildDto(NEW_ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG);
    ItinerarioDto actual = service.update(1L, updateDto).block();

    verify(repository, times(1)).findById(anyLong());
    verify(repository, times(1)).save(argumentCaptor.capture());
    Itinerario almostStoredItinerario = argumentCaptor.getValue();

    assertEquals(updateDto, actual);
    assertEquals(buildEntity(ID, NEW_ID_LINHA, NEW_LAT, NEW_LNG), almostStoredItinerario);

  }

  @Test()
  void whenNotExistsItinerarioShouldError() {

    when(repository.findById(anyLong())).thenReturn(Mono.empty());
    ItinerarioDto updateDto = buildDto(NEW_LNG, NEW_LAT);

    assertThrows(HttpClientErrorException.class, () -> service.update(1L, updateDto).block());
    verify(repository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(repository);
  }

}