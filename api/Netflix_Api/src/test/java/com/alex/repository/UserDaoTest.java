package com.alex.repository;

import com.alex.model.Role;
import com.alex.model.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Slf4j
class UserDaoTest {

    @Autowired
    UserDao userDao;


    @Test
    public void UserDao_FindAll_ReturnNotNullAndSizeEqualsTwo() {
        User user0 = User.builder()
                .username("Song")
                .nickname("Song Jiang")
                .email("song@gmail.com")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE)
                .password("password").build();
        User user1 = User.builder()
                .username("Kim")
                .nickname("Jim Kim")
                .email("kim@gmail.com")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE)
                .password("password").build();

        userDao.save(user0);
        userDao.save(user1);

        List<User> all = userDao.findAll();

        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }

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
    public void UserDao_FindAll_ReturnsMoreThanOneUser() {
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

    @Test
    public void UserDao_Delete_ReturnsNullUser() {
        User user0 = User.builder()
                .username("Song")
                .nickname("Song Jiang")
                .email("song@gmail.com")
                .password("password")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE).build();

        User savedUser = userDao.save(user0);

        userDao.delete(savedUser);

        Optional<User> user = userDao.findById(savedUser.getId());
        Assertions.assertThat(user).isEmpty();

    }

    @Test
    public void UserDao_Count_ReturnsTwo() {
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

        long total = userDao.count();
        Assertions.assertThat((int) total).isEqualTo(2);
    }

    @Test
    public void UserDao_GetByUsername_ReturnsNotNull() {
        User user0 = User.builder()
                .username("Song")
                .nickname("Song Jiang")
                .email("song@gmail.com")
                .password("password")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE).build();

        User savedUser = userDao.save(user0);

        User user = userDao.getByUsername(savedUser.getUsername()).get();
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo("Song");
    }

    @Test
    public void UserDao_GetByEmail_ReturnsNotNull() {
        User user0 = User.builder()
                .username("Song")
                .nickname("Song Jiang")
                .email("song@gmail.com")
                .password("password")
                .locked(Boolean.FALSE)
                .enabled(Boolean.FALSE).build();

        User savedUser = userDao.save(user0);

        User user = userDao.getByEmail(savedUser.getEmail()).get();
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getEmail()).isEqualTo("song@gmail.com");
    }

    @Test
    public void UserDao_Pageable_FindAll_ReturnsNotNull() {
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

        int page = 1;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size);

        Page<User> page1 = userDao.findAll(pageable);

        Assertions.assertThat(page1.getSize()).isEqualTo(1);
        Assertions.assertThat(page1.getTotalElements()).isEqualTo(2);
    }
}