package br.com.poatransporte.converter;

import br.com.poatransporte.dto.ItinerarioDto;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;

@Component
public class ItinerarioWebClientConverter implements Converter<Map<String, Object>, Flux<ItinerarioDto>> {

  public static final String LAT = "lat";
  public static final String LNG = "lng";
  private final JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();

  @Override
  public Flux<ItinerarioDto> convert(Map<String, Object> source) {
    return Flux.fromStream(source.keySet().stream()
        .filter(NumberUtils::isCreatable)
        .map(s -> (Map<String, String>) source.get(s))
        .map(jsonObject -> {
          ItinerarioDto itinerarioDto = new ItinerarioDto();
          itinerarioDto.setLat(NumberUtils.createBigDecimal(jsonObject.get(LAT)));
          itinerarioDto.setLng(NumberUtils.createBigDecimal(jsonObject.get(LNG)));
          return itinerarioDto;
        })
    );
  }
}
