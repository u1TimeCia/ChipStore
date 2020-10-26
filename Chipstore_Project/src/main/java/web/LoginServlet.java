package web;


import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//outdated class
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、get request parameter
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // call userService.login() to deal with service
        User loginUser = userService.login(new User(null, username, password, null));
        // if equals null, login failed
        if (loginUser == null) {
            //store errorMsg and form information into request
            req.setAttribute("msg", "username or password is wrong");
            req.setAttribute("username", username);

            //   go to login page
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // login success
            //go to login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}

