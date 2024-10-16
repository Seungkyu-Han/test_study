package seungkyu.slicetest.repository;


import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import seungkyu.slicetest.common.repository.UserEntity;

public interface UserR2dbcRepository
        extends R2dbcRepository<UserEntity, Long> {
    Flux<UserEntity> findAllByNameStartsWith(String prefix);
}