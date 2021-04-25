package br.com.poatransporte.schedule;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.webclient.LinhaWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class LinhaUpdater {

  public static final String LINHA_IMPORTADA_COM_SUCESSO = "Linha imported successful. {}";
  public static final String IMPORT_LINES_TASK_FINISHED = "Import linhas task finished.";
  private final LinhaWebClient linhaWebClient;
  private final BaseService<LinhaDto> linhaService;

  @Autowired
  public LinhaUpdater(LinhaWebClient linhaWebClient, BaseService<LinhaDto> linhaService) {
    this.linhaWebClient = linhaWebClient;
    this.linhaService = linhaService;
  }

  @PostConstruct
  @Scheduled(cron = "0 0 1 * * *")
  public void importaLinhasPoaTransporte() {
    linhaWebClient.getLinhas()
        .flatMap(linhaService::create)
        .doOnTerminate(() -> log.info(IMPORT_LINES_TASK_FINISHED))
        .subscribe();
  }


}
