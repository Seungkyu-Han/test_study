package seungkyu.unittest.repository;


import org.springframework.data.r2dbc.repository.R2dbcRepository;
import seungkyu.unittest.common.repository.UserEntity;

public interface UserR2dbcRepository
        extends R2dbcRepository<UserEntity, Long> {
}