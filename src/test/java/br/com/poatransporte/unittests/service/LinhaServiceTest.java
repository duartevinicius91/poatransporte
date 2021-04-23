package br.com.poatransporte.unittests.service;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import br.com.poatransporte.repository.LinhaRepository;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.service.LinhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.com.poatransporte.helper.LinhaHelper.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class LinhaServiceTest {

  public static final String NEW_NOME = "NEW_NOME";
  public static final String NEW_CODIGO = "NEW_CODIGO";
  public static final Long NEW_ID = 99L;

  private LinhaConverter linhaConverter = new LinhaConverter();
  private LinhaRepository linhaRepository;
  private BaseService<LinhaDto> linhaService;

  @BeforeEach
  void setUp() {
    linhaRepository = mock(LinhaRepository.class);
    linhaService = new LinhaService(linhaRepository, linhaConverter);
  }

  @Test
  void shouldReturnExaclyWhatLinhaWebClientReturn() {
    when(linhaRepository.findAll()).thenReturn(Flux.just(buildEntity()));
    Flux<LinhaDto> linhas = linhaService.findAll();
    LinhaDto actual = linhas.blockFirst();

    assertNotNull(actual);
    assertEquals(ID, actual.getId());
    assertEquals(CODIGO, actual.getCodigo());
    assertEquals(NOME, actual.getNome());
  }

  @Test
  void shouldReturnLinhaWhenFindById() {
    when(linhaRepository.findById(anyLong())).thenReturn(Mono.just(buildEntity()));

    LinhaDto actual = linhaService.findById(1L).block();
    assertEquals(buildDto(), actual);
  }

  @Test
  void shouldReturnVoidMonoWhenDeleteLinha() {
    when(linhaRepository.deleteById(anyLong())).thenReturn(Mono.empty());

    Void block = linhaService.delete(1L).block();
    assertNull(block);
 }

  @Test
  void whenReceiveLinhaShouldPersist() {
    when(linhaRepository.findById(anyLong())).thenReturn(Mono.just(buildEntity()));
    when(linhaRepository.save(any(Linha.class))).thenReturn(Mono.just(buildEntity(NEW_ID, NEW_CODIGO, NEW_NOME)));
    ArgumentCaptor<Linha> argumentCaptor = ArgumentCaptor.forClass(Linha.class);

    LinhaDto actual = linhaService.create(buildDto(NEW_ID, NEW_CODIGO, NEW_NOME)).block();

    verify(linhaRepository, times(1)).findById(anyLong());
    verify(linhaRepository, times(1)).save(argumentCaptor.capture());
    Linha value = argumentCaptor.getValue();

    assertEquals(buildEntity(NEW_ID, NEW_CODIGO, NEW_NOME), value);
    assertFalse(value.isNew());
    assertEquals(buildDto(NEW_ID, NEW_CODIGO, NEW_NOME), actual);

  }

  @Test
  void whenReceiveNewLinhaShouldPersist() {
    when(linhaRepository.findById(anyLong())).thenReturn(Mono.empty());
    when(linhaRepository.save(any(Linha.class))).thenReturn(Mono.just(buildEntity(NEW_ID, NEW_CODIGO, NEW_NOME)));
    ArgumentCaptor<Linha> argumentCaptor = ArgumentCaptor.forClass(Linha.class);

    LinhaDto actual = linhaService.create(buildDto(NEW_ID, NEW_CODIGO, NEW_NOME)).block();

    verify(linhaRepository, times(1)).findById(anyLong());
    verify(linhaRepository, times(1)).save(argumentCaptor.capture());
    Linha value = argumentCaptor.getValue();


    assertTrue(value.isNew());
    assertEquals(NEW_ID, value.getId());
    assertEquals(NEW_CODIGO, value.getCodigo());
    assertEquals(NEW_NOME, value.getNome());
    assertEquals(buildDto(NEW_ID, NEW_CODIGO, NEW_NOME), actual);

  }

  @Test
  void whenReceiveLinhaShouldUpdate() {

    Mono<Linha> storedLinha = Mono.just(buildEntity());
    when(linhaRepository.findById(anyLong())).thenReturn(storedLinha);

    Mono<Linha> persistedLinha = Mono.just(buildEntity(NEW_CODIGO, NEW_NOME));
    when(linhaRepository.save(any(Linha.class))).thenReturn(persistedLinha);

    ArgumentCaptor<Linha> argumentCaptor = ArgumentCaptor.forClass(Linha.class);

    LinhaDto updateDto = buildDto(NEW_CODIGO, NEW_NOME);
    LinhaDto actual = linhaService.update(1L, updateDto).block();

    verify(linhaRepository, times(1)).findById(anyLong());
    verify(linhaRepository, times(1)).save(argumentCaptor.capture());
    Linha value = argumentCaptor.getValue();

    assertFalse(value.isNew());
    assertEquals(ID, value.getId());
    assertEquals(NEW_CODIGO, value.getCodigo());
    assertEquals(NEW_NOME, value.getNome());
    assertEquals(NEW_CODIGO, actual.getCodigo());
    assertEquals(NEW_NOME, actual.getNome());

  }

  @Test()
  void whenNotExistsLinhaShouldError() {

    when(linhaRepository.findById(anyLong())).thenReturn(Mono.empty());
    LinhaDto updateDto = buildDto(NEW_CODIGO, NEW_NOME);

    assertThrows(HttpClientErrorException.class, () -> linhaService.update(1L, updateDto).block());
    verify(linhaRepository, times(1)).findById(anyLong());
    verifyNoMoreInteractions(linhaRepository);
  }

}