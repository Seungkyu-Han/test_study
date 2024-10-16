package seungkyu.unittest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.MockUtil;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import seungkyu.unittest.common.repository.UserEntity;
import seungkyu.unittest.repository.UserR2dbcRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    WebClient mockWebClient;

    @Mock
    UserR2dbcRepository mockUserR2dbcRepository;

    @Mock
    R2dbcEntityTemplate mockR2dbcEntityTemplate;

    @InjectMocks
    UserService userService;

    @Mock
    WebClient.RequestHeadersUriSpec mockRequestHeadersUriSpec;

    @Mock
    WebClient.RequestHeadersSpec mockRequestHeadersSpec;

    @Mock
    WebClient.ResponseSpec mockResponseSpec;

    @Mock
    ResponseEntity mockResponseEntity;

    @Nested
    class FindById {
        Long userId;

        @BeforeEach
        void setup() {
            userId = 1L;

            lenient().when(mockWebClient.get())
                    .thenReturn(mockRequestHeadersUriSpec);

            lenient().when(mockRequestHeadersUriSpec.uri(anyString(), anyMap()))
                    .thenReturn(mockRequestHeadersSpec);

            lenient().when(mockRequestHeadersSpec.retrieve())
                    .thenReturn(mockResponseSpec);
        }

        @Test
        void when_user_repository_returns_empty_then_returns_empty_mono() {
            // given
            when(mockUserR2dbcRepository.findById(eq(userId)))
                    .thenReturn(Mono.empty());

            // when
            var result = userService.findById(String.valueOf(userId));

            // then
            StepVerifier.create(result)
                    .verifyComplete();
        }


    }
}