package seungkyu.unittest.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import seungkyu.unittest.service.ImageResponse;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class ImageHttpClient {
    private final WebClient imageWebClient;

    public Mono<ImageResponse> getImageResponseByImageId(String imageId) {
        Map<String, String> uriVariableMap = Map.of("imageId", imageId);

        return imageWebClient.get()
                .uri("/api/images/{imageId}", uriVariableMap)
                .retrieve()
                .toEntity(ImageResponse.class)
                .map(resp -> resp.getBody());
    }
}