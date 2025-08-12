package com.example.reactivewebservice

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

// We create a @SpringBootTest, starting an actual server on a RANDOM_PORT
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingRouterTest @Autowired constructor(
    private val webTestClient: WebTestClient
) {

    // Spring Boot will create a WebTestClient for you,
    // already configured and ready to issue requests against "localhost:RANDOM_PORT"
    @Test
    fun testHello() {
        webTestClient
            // Create a GET request to test an endpoint
            .get().uri("/hello")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            // and use the dedicated DSL to test assertions against the response
            .expectStatus().isOk
            .expectBody<Greeting>()
            .isEqualTo(Greeting("Hello, Spring!"))
    }
}
