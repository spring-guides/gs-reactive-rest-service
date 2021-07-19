package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {

	private final String result;

	public GreetingWebClient() {

		WebClient client = WebClient.create("http://localhost:8080");
		WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec = client.get();
		requestHeadersUriSpec.uri("/hello").accept(MediaType.TEXT_PLAIN);
		Mono<String> response = requestHeadersUriSpec.exchangeToMono(res -> {
			if (res.statusCode().equals(HttpStatus.OK))
				return res.bodyToMono(String.class);
			if (res.statusCode().is4xxClientError())
				return Mono.just("Error response: ".concat(res.statusCode().getReasonPhrase()));
			return res.createException().flatMap(Mono::error);
		});

		result = response.block();

	}

	public String getResult() {
		return result;
	}
}
