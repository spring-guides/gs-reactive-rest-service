package hello;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {

	private final WebClient client = WebClient.create("http://localhost:8080");

	private final Mono<String> response = client.get()
			.uri("/hello")
			.accept(MediaType.TEXT_PLAIN)
			.exchangeToMono(res -> res.bodyToMono(String.class));

	public String getResult() {
		return ">> result = " + response.block();
	}

}
