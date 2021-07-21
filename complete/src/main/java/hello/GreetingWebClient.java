package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {

	private final WebClient client = WebClient.create("http://localhost:8080");

	private final Mono<String> response = client.get()
			.uri("/hello")
			.accept(MediaType.TEXT_PLAIN)
			.exchangeToMono(res -> {
				if (res.statusCode().equals(HttpStatus.OK)) {
					return res.bodyToMono(String.class);
				} else if (res.statusCode().is4xxClientError()) {
					return Mono.just(res.statusCode().getReasonPhrase());
				} else {
					return res.createException().flatMap(Mono::error);
				}
			});

	public String getResult() {
		return ">> result = " + response.block();
	}

}
