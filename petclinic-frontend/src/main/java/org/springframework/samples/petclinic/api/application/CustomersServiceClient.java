package org.springframework.samples.petclinic.api.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.api.dto.OwnerDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class CustomersServiceClient {

  @Value("${customers-service-id://customers-service}")
  private String hostname;

  private final WebClient.Builder webClientBuilder;

  public CustomersServiceClient(WebClient.Builder webClientBuilder) {
    this.webClientBuilder = webClientBuilder;
  }

  public Mono<OwnerDetails> getOwner(final int ownerId) {
    return webClientBuilder.build().get()
        .uri(hostname + "/owners/{ownerId}", ownerId)
        .retrieve()
        .bodyToMono(OwnerDetails.class);
  }
}
