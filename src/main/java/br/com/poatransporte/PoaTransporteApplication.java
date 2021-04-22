package br.com.poatransporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PoaTransporteApplication {

  public static void main(String[] args) {
    SpringApplication.run(PoaTransporteApplication.class, args);
  }

}
