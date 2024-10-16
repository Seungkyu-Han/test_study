package seungkyu.mockito.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.test.StepVerifier;
import seungkyu.mockito.MockitoApplication;
import seungkyu.mockito.test.app.repository.chat.ChatDocument;
import seungkyu.mockito.test.app.repository.chat.ChatMongoRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("mongo")
@ContextConfiguration(
        classes = MockitoApplication.class
)
@DataMongoTest
public class MongoExampleTest {

    @Autowired
    ChatMongoRepository chatMongoRepository;

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @AfterEach
    void tearDown(){
        reactiveMongoTemplate.dropCollection(
                ChatDocument.class
        ).block();
    }

    @Test
    void test1(){
        var chat = new ChatDocument(
                "seungkyu"," seungkyu", "hello"
        );

        var result = chatMongoRepository.insert(chat);

        StepVerifier.create(result)
                .assertNext(chatDocument -> {
                    Assertions.assertNotNull(chatDocument.getId());
                    assertEquals(
                            chat.getFrom(),
                            chatDocument.getFrom()
                    );
                })
                .verifyComplete();
    }

    @Test
    void test2(){
        var chats = List.of(
                new ChatDocument("seungkyu", "seungkyu1", "hello1"),
                new ChatDocument("seungkyu", "seungkyu2", "hello2"),
                new ChatDocument("seungkyu", "seungkyu3", "hello3")
        );

        reactiveMongoTemplate.insertAll(chats)
                .collectList().block();

        var result = chatMongoRepository.findAllByFrom("seungkyu");

        StepVerifier.create(result)
                .expectNextCount(3)
                .verifyComplete();
    }
}
