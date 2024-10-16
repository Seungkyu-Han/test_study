package seungkyu.slicetest.controller;

import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import seungkyu.slicetest.controller.dto.ProfileImageResponse;
import seungkyu.slicetest.controller.dto.SignupUserRequest;
import seungkyu.slicetest.service.AuthService;
import seungkyu.slicetest.service.UserService;
import seungkyu.slicetest.testBuilder.TestDataBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = UserController.class)
class UserControllerSliceTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    UserService userService;

    @MockBean
    AuthService authService;

    @Test
    void webTestClient_should_not_be_null(){
        assertNotNull(webTestClient);
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class UserRes{
        private String id;
        private String name;
        private int age;
        private Long followCount;
        private Optional<ProfileImageResponse> image;
    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class ProfileImageRes{
        private String id;
        private String name;
        private String url;
    }


    @Nested
    class GetUserById{
        @Test
        void when_iam_token_is_not_given_then_returns_unauthorized_status(){
            //when,then
            webTestClient.get()
                    .uri("/api/users/1")
                    .exchange()
                    .expectStatus()
                    .isUnauthorized();
        }

        @Test
        void when_auth_service_returns_empty_then_returns_unauthorized_status(){
            //given
            var token = "invalid";

            when(authService.getNameByToken(eq(token)))
                    .thenReturn(Mono.empty());

            webTestClient.get()
                    .uri("/api/users/1")
                    .header("x-i-am", token)
                    .exchange()
                    .expectStatus()
                    .isUnauthorized();
        }

        @Test
        void when_authentication_name_is_not_matched_then_returns_unauthorized_status(){
            var token = "token";

            var invalidName = "invalidName";

            when(authService.getNameByToken(eq(token)))
                    .thenReturn(Mono.just(invalidName));

            webTestClient.get()
                    .uri("/api/users/1")
                    .header("x-i-am", token)
                    .exchange()
                    .expectStatus()
                    .isUnauthorized();
        }

        @Test
        void when_user_service_returns_empty_then_returns_not_found_status(){
            //given
            var token = "token";
            var userId = "1234";

            when(authService.getNameByToken(eq(token)))
                    .thenReturn(Mono.just(userId));

            when(userService.findById(eq(userId)))
                    .thenReturn(Mono.empty());

            webTestClient.get()
                    .uri("/api/users/"+userId)
                    .header("X-I-AM", token)
                    .exchange()
                    .expectStatus()
                    .isNotFound();
        }

        @Test
        void when_all_conditions_are_perfect_then_returns_user_response(){
            //given
            var token = "token";
            var userId = "1234";
            var foundUser = TestDataBuilder.createUser(userId);

            when(authService.getNameByToken(eq(token)))
                    .thenReturn(Mono.just(userId));

            when(userService.findById(eq(userId)))
                    .thenReturn(Mono.just(foundUser));

            webTestClient.get()
                    .uri("/api/users/"+userId)
                    .header("X-I-AM", token)
                    .exchange()
                    .expectStatus()
                    .isOk()
                    .expectHeader()
                    .contentType(MediaType.APPLICATION_JSON)
                    .expectBody(UserRes.class)
                    .value(
                            userRes -> {
                                assertEquals(userId, userRes.id);
                                assertEquals(foundUser.getName(), userRes.name);
                                assertEquals(foundUser.getAge(), userRes.age);
                            }
                    );
        }
    }

    @Nested
    class SignupUser{
        @Test
        void when_signup_request_is_given_then_should_save_user(){

            var userId = "1204";
            var name = "seungkyu";
            var age = 20;
            var password = "1204";
            var profileImageId = "hello";
            //given
            var signupRequest = new SignupUserRequest(
                    name, age, password, profileImageId
            );


            var createdUser = TestDataBuilder.createUser(
                    userId, name, age, password, profileImageId
            );

            when(userService.createUser(
                    anyString(), anyInt(), anyString(), anyString()
            )).thenReturn(Mono.just(createdUser));

            webTestClient.post()
                    .uri("/api/users/signup")
                    .bodyValue(signupRequest)
                    .exchange()
                    .expectStatus()
                    .isCreated();
        }

        @Test
        void when_create_user_returns_empty_mono_then(){
            var userId = "1204";
            var name = "seungkyu";
            var age = 20;
            var password = "1204";
            var profileImageId = "hello";
            //given
            var signupRequest = new SignupUserRequest(
                    name, age, password, profileImageId
            );


            when(userService.createUser(
                    anyString(), anyInt(), anyString(), anyString()
            )).thenReturn(Mono.empty());

            webTestClient.post()
                    .uri("/api/users/signup")
                    .bodyValue(signupRequest)
                    .exchange()
                    .expectStatus()
                    .isBadRequest();
        }
    }

}