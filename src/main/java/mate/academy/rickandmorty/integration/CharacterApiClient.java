package mate.academy.rickandmorty.integration;

import static org.apache.commons.lang3.StringUtils.truncate;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDataDto;
import mate.academy.rickandmorty.exception.ExternalApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterApiClient {
    private final ObjectMapper objectMapper;

    @Bean
    private HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

    public CharacterDataDto getDtoResponse(String url) {
        CharacterDataDto characterDataDto;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient().send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            int status = response.statusCode();
            if (status < 200 || status >= 300) {
                throw new ExternalApiException(
                        "Rick&Morty API returned status " + status + " for " + url
                                + " body=" + truncate(response.body(), 512));
            }
            characterDataDto = objectMapper.readValue(response.body(), CharacterDataDto.class);
            return characterDataDto;
        } catch (IOException | InterruptedException e) {
            throw new ExternalApiException("Problem to get response from external API url: " + url);
        }
    }
}
