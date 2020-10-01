package org.trafikverket.poller.divyaTrafikverketPoller;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/*

your personal number should be replalced with actual personal number
 */
@Component
@Slf4j
public class Poller {

  private final EmailSender emailSender;

  public Poller(EmailSender emailSender) {
    this.emailSender = emailSender;
  }

  @Scheduled(fixedRate = 50000)
  public void reportCurrentTimeSollentuna() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000134,\"vehicleTypeId\":4,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response sollentuna");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done sollentuna");
  }

  @Scheduled(fixedRate = 50000)
  public void reportCurrentTimeSollentunaManual() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000134,\"vehicleTypeId\":2,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket Manual %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response sollentuna Manual");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done sollentuna Manual");
  }

  @Scheduled(fixedRate = 135000)
  public void reportCurrentTimeSala() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000041,\"vehicleTypeId\":4,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response Sala");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done Sala");
  }

  @Scheduled(fixedRate = 135000)
  public void reportCurrentTimeEskilstuna() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000005,\"vehicleTypeId\":4,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response Eskilstuna");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done Eskilstuna");
  }


  @Scheduled(fixedRate = 135000)
  public void reportCurrentTimeEskilstunaManual() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000005,\"vehicleTypeId\":2,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response Eskilstuna Manual");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done Eskilstuna Manual");
  }

  private String checkMatches(Occasions occasions) {
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date dateForSlot = simpleDateFormat.parse(occasions.getDate());
      LocalDate localDate = dateForSlot.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
      if (localDate.isBefore(LocalDate.now().plusDays(10L))) {
        log.error(":::::::::::::::: **** found it ::::::::::::::::-----------");
        log.error("localDate {}", occasions.getDate());
        log.error("get Location Name {}", occasions.getLocationName());
        log.info("localDate available is {}, {}, {}", occasions.getDate(), occasions.getTime(),
            occasions.getLocationName());

        log.error(":::::::::::::::: **** found it end slot info new follows::::::::::::::::-----------");
        if ("sollentuna".equalsIgnoreCase(occasions.getLocationName()) ||
            "Enköping"
                .equalsIgnoreCase(occasions.getLocationName())) {
          log.info("latest local Date available is {}, {}, {}", occasions.getDate(), occasions.getTime(),
              occasions.getLocationName());
          try {
            emailSender.sendEmail(occasions.getDate() + "::" + occasions.getTime() + "::"
                + occasions.getLocationName());
            emailSender.sendSms(occasions.getDate() + "::" + occasions.getTime() + "::"
                + occasions.getLocationName());
            log.info("mailSent");
          } catch (MailjetSocketTimeoutException e) {
            e.printStackTrace();
          } catch (MailjetException e) {
            e.printStackTrace();
          }
        }
      }

    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "Not Found anything loop done";
  }

  @Scheduled(fixedRate = 120000)
  public void reportCurrentTimeSträngnäs() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000008,\"vehicleTypeId\":4,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response for Strängnäs");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done Strängnäs");
  }

  @Scheduled(fixedRate = 120000)
  public void reportCurrentTimeSträngnäsManual() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000008,\"vehicleTypeId\":2,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response for Strängnäs Manual");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done Strängnäs Manual");
  }

  @Scheduled(fixedRate = 120000)
  public void reportCurrentTimeEnkoping() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000072,\"vehicleTypeId\":4,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response for Enkoping");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done Enkoping");
  }

  @Scheduled(fixedRate = 120000)
  public void reportCurrentTimeEnkopingManual() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    String body = "{\"bookingSession\":{\"socialSecurityNumber\":\"your personal number\",\"licenceId\":5,\"bookingModeId\":0,\"ignoreDebt\":false,\"ignoreBookingHindrance\":false,\"excludeExaminationCategories\":[],\"rescheduleTypeId\":0,\"paymentIsActive\":false,\"paymentReference\":null,\"paymentUrl\":null},\"occasionBundleQuery\":{\"startDate\":\"2020-09-30T22:00:00.000Z\",\"locationId\":1000072,\"vehicleTypeId\":2,\"tachographTypeId\":1,\"occasionChoiceId\":1,\"examinationTypeId\":12}}";
    ResponseTrafikverket block = WebClient.create().post()
        .uri("https://fp.trafikverket.se/Boka/occasion-bundles")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from trafikverket %s Manual", body))))
        .bodyToMono(ResponseTrafikverket.class).block();
    log.info("Got Response for Enkoping Manual");
    assert block != null;
    block.getData().stream().filter(Objects::nonNull).forEach(dataList ->
    {
      dataList.getOccasions().stream().filter(Objects::nonNull).forEach(this::checkMatches);
    });
    log.info("Not Found anything loop done Enkoping Manual");
  }
}
