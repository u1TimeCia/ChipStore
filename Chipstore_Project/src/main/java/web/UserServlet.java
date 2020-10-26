package web;

import com.google.gson.Gson;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

//merge loginServlet and registerServlet
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     *  implement user logout function by destroying sessionScope
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //destroy session information
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }


    /**
     * implement loginServlet, decide if login information is reight and return info relatively
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        // call userService.login() to deal with service
        User loginUser = userService.login(user);
        // if equals null, login failed
        if (loginUser == null) {
            //store errorMsg and form information into request
            req.setAttribute("msg", "username or password is wrong");
            req.setAttribute("username", user.getUsername());

            //   go to login page
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // login success
            //store user information to session to display username if different login_success page
            req.getSession().setAttribute("user", loginUser);
            //go to login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * implement registerServlet, pass user registration information and return the information to make decision
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get verification code from sessionScope
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        User user = new User();
        WebUtils.copyParamToBean(req.getParameterMap(), user);
        if(token != null && token.equalsIgnoreCase(code)){
            if(userService.existUser(user.getUsername())){
                req.setAttribute("msg", "username exists");
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
                System.out.println("username exists");
            }
            else{
                userService.registerUser(user);
                //store user information to session to display username if different login_success page
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
                System.out.println("resister successfully");
            }
        }
        else{
            req.setAttribute("msg", "verification code is wrong");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            System.out.println("code"+code+"wrong");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }

    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get username parameter
        String username = req.getParameter("username");
        boolean existUsername = userService.existUser(username);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername", existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    /**
     * decide user login or register by reflection in baseServlet
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
}
