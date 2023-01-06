package com.alex.repository;

import com.alex.model.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Slf4j
class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void UserDao_Save_ReturnSavedUser() {
        User user = User.builder()
                .username("Song")
                .nickname("Song Jiang")
                .email("song@gmail.com")
                .password("password")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE).build();

        User savedUser = userDao.save(user);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    public void UserDao_FindAll_ReturnMoreThanOneUser() {
        User user0 = User.builder()
                .username("Song")
                .nickname("Song Jiang")
                .email("song@gmail.com")
                .password("password")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE).build();

        User user1 = User.builder()
                .username("Jim")
                .email("jim@gmail.com")
                .password("password")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE).build();

        userDao.save(user0);
        userDao.save(user1);

        List<User> userList = userDao.findAll();

        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }


}