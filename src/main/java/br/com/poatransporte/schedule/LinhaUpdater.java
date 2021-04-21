package br.com.poatransporte.schedule;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.entity.Linha;
import br.com.poatransporte.repository.LinhaRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoOperator;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class LinhaUpdater {

  public static final String LINHA_IMPORTADA_COM_SUCESSO = "Linha importada com sucesso. {}";
  private final WebClient linhaWebClient;
  private final LinhaRepository linhaRepository;

  @Autowired
  public LinhaUpdater(WebClient linhaWebClient, LinhaRepository linhaRepository) {
    this.linhaWebClient = linhaWebClient;
    this.linhaRepository = linhaRepository;
  }

  @PostConstruct
  public void importaLinhasPoaTransporte() {
    linhaWebClient
        .get()
        .retrieve()
        .bodyToFlux(Linha.class)
        .flatMap(linha ->
            linhaRepository
              .findById(linha.getId())
              .switchIfEmpty(linhaRepository.save(linha.setAsNew()))
        )
        .subscribe(linha -> log.info(LINHA_IMPORTADA_COM_SUCESSO, linha))
    ;
  }
}
