package com.nirvana.habits.journal.webclient;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;

@Service
public class MovieDataBaseClient {
    public String getMovies() {
        WebClient client = WebClient.builder().build();
        return client
                .get()
                .uri("http://localhost:8090/api/movies")
                .httpRequest(request-> {
                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofMillis(1));
                })
                .retrieve().bodyToMono(String.class)
                .onErrorResume(e-> Mono.empty())
                .block();
    }
}
