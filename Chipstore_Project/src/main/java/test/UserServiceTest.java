package test;

import org.junit.jupiter.api.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void registerUser() {
        UserService userService = new UserServiceImpl();
        userService.registerUser(new User(null,"qhr","qhr","qhr74426726@icloud.com"));
    }

    @Test
    void login() {
        UserService userService = new UserServiceImpl();
        System.out.println(userService.login(new User(null,"qhr","qhr","qhr74426726@icloud.com")));
    }

    @Test
    void existUser() {
        UserService userService = new UserServiceImpl();
        System.out.println(userService.existUser("1"));
    }
}