package org.trafikverket.poller.divyaTrafikverketPoller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "org.trafikverket")
public class DivyaTrafikverketPollerApplication {

  public static void main(String[] args) {
    SpringApplication.run(DivyaTrafikverketPollerApplication.class, args);
  }


  @Bean
  public ObjectMapper objectMapper() {
    SimpleFilterProvider filters = new SimpleFilterProvider();
    filters.setFailOnUnknownId(false);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setFilterProvider(filters);
    objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return objectMapper;
  }
}
