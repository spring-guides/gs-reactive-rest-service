package com.example.reactivewebservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration(proxyBeanMethods = false)
class GreetingRouter {

    @Bean
    fun route(greetingHandler: GreetingHandler): RouterFunction<ServerResponse> = router {
        (accept(MediaType.APPLICATION_JSON) and GET("/hello")).invoke(greetingHandler::hello)
    }
}
