package br.com.poatransporte.controller;

import br.com.poatransporte.dto.ItinerarioDto;
import br.com.poatransporte.service.BaseService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/itinerario")
public class ItinerarioController implements BaseController<ItinerarioDto> {

  private final BaseService<ItinerarioDto> itinerarioService;

  public ItinerarioController(BaseService<ItinerarioDto> itinerarioService) {
    this.itinerarioService = itinerarioService;
  }

  @GetMapping
  public Flux<ItinerarioDto> findAll(String nome) {
    return itinerarioService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<ItinerarioDto> findById(@PathVariable("id") Long id) {
    return itinerarioService.findById(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable("id") Long id) {
    return itinerarioService.delete(id);
  }

  @PostMapping
  public Mono<ItinerarioDto> create(@RequestBody ItinerarioDto dto) {
    return itinerarioService.create(dto);
  }

  @PutMapping("/{id}")
  public Mono<ItinerarioDto> update(@PathVariable("id") Long id, @RequestBody ItinerarioDto dto) {
    return itinerarioService.update(id, dto);
  }
}
