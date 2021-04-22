package br.com.poatransporte.controller;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.service.BaseService;
import br.com.poatransporte.service.LinhaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/linha")
public class LinhaController implements BaseController<LinhaDto> {

  private final BaseService<LinhaDto> linhaService;

  public LinhaController(BaseService<LinhaDto> linhaService) {
    this.linhaService = linhaService;
  }

  @GetMapping
  public Flux<LinhaDto> findAll() {
    return linhaService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<LinhaDto> findById(@PathVariable("id") Long id) {
    return Mono.empty();
  }

}
