package br.com.poatransporte.schedule;

import org.apache.camel.Consume;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TaxiUpdater extends EndpointRouteBuilder  {


  @Override
  public void configure() throws Exception {
      from(
      file("./")
          .fileName("ponto-taxi.txt")
          .noop(false)
          .repeatCount(1)
    )
    .id("importFile")

    .log("${body}")

    ;
  }

  @Consume("file:///home/viniciusgoncalves/?fileName=ponto-taxi.txt&repeatCount=1&noop=false")
  public void test(String msg) throws Exception {
    System.out.println(msg);
  }
}
