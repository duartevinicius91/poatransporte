package br.com.poatransporte.unittests.schedule;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.entity.Linha;
import br.com.poatransporte.repository.LinhaRepository;
import br.com.poatransporte.schedule.LinhaUpdater;
import br.com.poatransporte.webclient.LinhaWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static br.com.poatransporte.helper.LinhaHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class LinhaUpdaterTest {
  private LinhaWebClient linhaWebClient = mock(LinhaWebClient.class);
  private LinhaRepository linhaRepository = mock(LinhaRepository.class);
  private LinhaConverter linhaConverter = new LinhaConverter();

  private LinhaUpdater linhaUpdater;

  @BeforeEach
  void setUp() {
    linhaUpdater = new LinhaUpdater(linhaWebClient, linhaRepository, linhaConverter);
  }

  @Test
  void shouldInsertNewLinha() {
    when(linhaWebClient.getLinhas()).thenReturn(Flux.just(buildDto()));
    when(linhaRepository.findById(anyLong())).thenReturn(Mono.empty());
    when(linhaRepository.save(any(Linha.class))).thenReturn(Mono.just(new Linha()));
    ArgumentCaptor<Linha> argumentCaptor = ArgumentCaptor.forClass(Linha.class);

    linhaUpdater.importaLinhasPoaTransporte();

    verify(linhaRepository, times(1)).save(argumentCaptor.capture());
    Linha actual = argumentCaptor.getValue();
    assertNotNull(actual);
    assertEquals(CODIGO, actual.getCodigo());
    assertEquals(ID, actual.getId());
    assertEquals(NOME, actual.getNome());
  }

}