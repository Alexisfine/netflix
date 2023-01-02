package com.alex.repository;

import com.alex.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    void getByUsername() {
        User user = new User();
        user.setUsername("Client");
        user.setEmail("client@gmail.com");
        user.setPassword("1234");
        userDao.save(user);
        log.info(userDao.getByUsername(user.getUsername()).toString());
    }

    @Test
    void getByEmail() {
        User user = new User();
        user.setUsername("Client");
        user.setEmail("client@gmail.com");
        user.setPassword("1234");
        userDao.save(user);
        System.out.println(userDao.getByEmail(user.getEmail()));
    }
}