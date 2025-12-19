package com.example.reactivewebservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Component
class GreetingClient(builder: WebClient.Builder) {

  // Spring Boot auto-configures a WebClient.Builder instance with nice defaults and customizations.
  // We can use it to create a dedicated WebClient for our component.
  private val client: WebClient = builder.baseUrl("http://localhost:8080").build()

  fun getMessage(): Mono<String> = client
    .get()
    .uri("/hello")
    .accept(MediaType.APPLICATION_JSON)
    .retrieve()
    .bodyToMono<Greeting>()
    .map { it.message }
}
