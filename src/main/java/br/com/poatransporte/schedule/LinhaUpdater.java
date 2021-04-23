package br.com.poatransporte.schedule;

import br.com.poatransporte.converter.LinhaConverter;
import br.com.poatransporte.entity.Linha;
import br.com.poatransporte.repository.LinhaRepository;
import br.com.poatransporte.webclient.LinhaWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class LinhaUpdater {

  public static final String LINHA_IMPORTADA_COM_SUCESSO = "Linha imported successful. {}";
  public static final String IMPORT_LINES_TASK_FINISHED = "Import linhas task finished.";
  private final LinhaWebClient linhaWebClient;
  private final LinhaRepository linhaRepository;
  private final LinhaConverter linhaConverter;

  @Autowired
  public LinhaUpdater(LinhaWebClient linhaWebClient, LinhaRepository linhaRepository, LinhaConverter linhaConverter) {
    this.linhaWebClient = linhaWebClient;
    this.linhaRepository = linhaRepository;
    this.linhaConverter = linhaConverter;
  }

  @PostConstruct
  @Scheduled(cron = "* * 1 * * *")
  public void importaLinhasPoaTransporte() {
    linhaWebClient.getLinhas()
        .flatMap(linhaConverter::toEntity)
        .doOnNext(Linha::setAsNew)
        .doOnNext(linha ->
            linhaRepository
              .findById(linha.getId())
              .switchIfEmpty(linhaRepository.save(linha))
              .subscribe(newLinha -> log.info(LINHA_IMPORTADA_COM_SUCESSO, newLinha))
        )
        .doOnTerminate(() -> log.info(IMPORT_LINES_TASK_FINISHED))
        .subscribe()

    ;
  }
}
