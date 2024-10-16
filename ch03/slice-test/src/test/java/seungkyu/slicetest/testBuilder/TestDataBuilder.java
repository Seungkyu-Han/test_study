package seungkyu.slicetest.testBuilder;


import seungkyu.slicetest.common.Image;
import seungkyu.slicetest.common.User;
import seungkyu.slicetest.common.repository.UserEntity;

import java.util.Collections;
import java.util.Optional;

public class TestDataBuilder {
    public static User createUser(String id) {
        var profileImage = new Image(
                "1",
                "profile",
                "https://seungkyu.han/images/1"
        );

        return new User(
                id,
                "seungkyu",
                20,
                Optional.of(profileImage),
                Collections.emptyList(),
                100L
        );
    }

    public static User createUser(
            String id,
            String name,
            Integer age,
            String password,
            String profileImageId
    ) {
        var profileImage = new Image(
                profileImageId,
                "profile",
                "https://seungkyu.han/images/1"
        );

        return new User(
                id,
                name,
                age,
                Optional.of(profileImage),
                Collections.emptyList(),
                100L
        );
    }

    public static UserEntity createUnsavedUserEntity(
            String name
    ) {
        return new UserEntity(
                name,
                20,
                "1",
                "password"
        );
    }

}