package br.com.poatransporte.schedule;

import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.repository.LinhaRepository;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.webclient.ItinerarioWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class ItinerarioUpdater {
  public static final String IMPORT_ITINERARIO_TASK_FINISHED = "Import itinerarios task finished.";

  private final ItinerarioWebClient itinerarioWebClient;
  private final BaseService<ItinerarioDto> itinerarioService;
  private final LinhaRepository linhaRepository;

  public ItinerarioUpdater(ItinerarioWebClient itinerarioWebClient, BaseService<ItinerarioDto> itinerarioService, LinhaRepository linhaRepository) {
    this.itinerarioWebClient = itinerarioWebClient;
    this.itinerarioService = itinerarioService;
    this.linhaRepository = linhaRepository;
  }

//  @PostConstruct
//  @Scheduled(cron = "30 * * * * *")
  public void importaItinerariosPoaTransporte() {
    linhaRepository
        .findAll()
        .flatMap(linha -> itinerarioWebClient.getItinerarios(linha.getId()))
        .flatMap(itinerarioDto -> itinerarioService.create(itinerarioDto))
        .doOnTerminate(() -> log.info(IMPORT_ITINERARIO_TASK_FINISHED))
        .subscribe();
  }
}
