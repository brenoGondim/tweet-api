package com.challenge.livesponsor.tweetapi.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private IUserRepository underTest;

    @AfterEach
    void tearDown() { underTest.deleteAll(); }

    @Test
    void itShouldFindOneByEmail() {
        //given
        String email = "laura@mail.com";
        User user = new User(
                1L,
                email,
                "12345",
                Role.USER,
                "Laura",
                0,
                null
        );
        underTest.save(user);

        //when
        Optional<User> expected = underTest.findOneByEmail(user.getEmail());

        //then
        assertThat(expected.isPresent()).isTrue();
        assertThat(expected.get()).isEqualTo(user);
    }

    @Test
    void itShouldNotFindOneByEmail() {
        //given
        String email = "mary@mail.com";

        //when
        Optional<User> expected = underTest.findOneByEmail(email);

        //then
        assertThat(expected.isPresent()).isFalse();
    }
}