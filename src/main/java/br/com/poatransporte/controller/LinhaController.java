package br.com.poatransporte.controller;

import br.com.poatransporte.dto.LinhaDto;
import br.com.poatransporte.service.BaseService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("/linha")
public class LinhaController implements BaseController<LinhaDto> {

  private final BaseService<LinhaDto> linhaService;

  public LinhaController(BaseService<LinhaDto> linhaService) {
    this.linhaService = linhaService;
  }

  @GetMapping
  public Flux<LinhaDto> findAll(@RequestParam("nome") String nome) {
    if (isBlank(nome)) {
      return linhaService.findAll();
    }
    return linhaService.findByNome(nome);
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

  @PutMapping("/{id}")
  public Mono<LinhaDto> update(@PathVariable("id") Long id, @RequestBody LinhaDto dto) {
    return linhaService.update(id, dto);
  }
}
