package br.com.poatransporte.unittests.service;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.repository.LinhaRepository;
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

class LinhaServiceTest {

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


}