package org.trafikverket.poller.divyaTrafikverketPoller;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EmailSender {

  //@Todo: your auth
  //implemented using mailjet so left it for reference
  void sendEmail(String emailData) throws MailjetSocketTimeoutException, MailjetException {
    MailjetClient client;
    MailjetRequest request;
    MailjetResponse response;
    client = new MailjetClient("your api key", "your secret",
        new ClientOptions("v3.1"));
    request = new MailjetRequest(Emailv31.resource)
        .property(Emailv31.MESSAGES, new JSONArray()
            .put(new JSONObject()
                .put(Emailv31.Message.FROM, new JSONObject()
                    .put("Email", "gaddechaitu2323@gmail.com")
                    .put("Name", "Divya"))
                .put(Emailv31.Message.TO, new JSONArray()
                    .put(new JSONObject()
                        .put("Email", "gaddechaitu2323@gmail.com")
                        .put("Name", "divya")))
                .put(Emailv31.Message.SUBJECT, emailData)
                .put(Emailv31.Message.TEXTPART, emailData)
                .put(Emailv31.Message.CUSTOMID, emailData)));
    response = client.post(request);
    System.out.println(response.getData());
    System.out.println(response.getStatus());
  }

  void sendSms(String sms) {

    String body = "{\n    \"Text\": \"" + sms + "\",\n    \"To\": \"+46734865655\",\n    \"From\": \"divya\"\n}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    //@Todo: your auth
    headers.setBearerAuth("your auth");

    String block = WebClient.create().post()
        .uri("https://api.mailjet.com/v4/sms-send")
        .headers(httpHeaders -> httpHeaders.addAll(headers))
        .body(BodyInserters.fromValue(body))
        .retrieve()
        .onStatus(HttpStatus::isError,
            clientResponse -> Mono.error(new NoSuchElementException(
                String.format("Error in response from sms %s", body))))
        .bodyToMono(String.class).block();
    System.out.println(block);
  }

}
