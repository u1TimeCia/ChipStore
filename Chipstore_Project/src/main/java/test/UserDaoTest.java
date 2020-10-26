package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.jupiter.api.Test;
import pojo.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    void saveUser() {
        UserDao userDao = new UserDaoImpl();
        userDao.saveUser(new User(2,"qhr","qhr","qhr74426726@icloud.com"));
    }

    @Test
    void queryByUsernameAndPwd() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryByUsernameAndPwd("admin","admin"));
    }
}