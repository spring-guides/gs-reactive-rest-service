package com.example.reactivewebservice

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class GreetingHandler {

  fun hello(request: ServerRequest): Mono<ServerResponse> =
    ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue(Greeting("Hello, Spring!"))
}
