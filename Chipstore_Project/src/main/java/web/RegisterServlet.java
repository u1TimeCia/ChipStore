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
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if("abcde".equalsIgnoreCase(code)){
            if(userService.existUser(username)){
                req.setAttribute("msg", "username exists");
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
                System.out.println("username exists");
            }
            else{
                userService.registerUser(new User(null,username,pwd,email));
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
                System.out.println("resister successfully");
            }
        }
        else{
            req.setAttribute("msg", "verification code is wrong");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("code"+code+"wrong");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }

    }
}
