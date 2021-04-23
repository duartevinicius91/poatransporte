package br.com.poatransporte.controller;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.service.BaseService;
import org.springframework.web.bind.annotation.*;
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
    return linhaService.findById(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") Long id) {
    return linhaService.delete(id);
  }

  @PostMapping
  public Mono<LinhaDto> create(@RequestBody LinhaDto dto) {
    return linhaService.create(dto);
  }

}
