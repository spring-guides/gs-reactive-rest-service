package com.example.reactivewebservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveWebServiceApplication

fun main(args: Array<String>) {
  val context = runApplication<ReactiveWebServiceApplication>(*args)
  val greetingClient = context.getBean(GreetingClient::class.java)
  // Block here to wait for the response, otherwise the JVM might exit before the message is logged
  println(">> message = ${greetingClient.getMessage().block()}")
}
