package web;

import pojo.Cart;
import pojo.Order;
import pojo.OrderItem;
import pojo.User;
import service.OrderItemService;
import service.OrderService;
import service.impl.OrderItemServiceImpl;
import service.impl.OrderServiceImpl;
import utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{

    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService= new OrderItemServiceImpl();

    /**
     * generate order
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void showMyOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderItem> orderItems = orderItemService.checkOrderItemByOrderId(req.getParameter("orderId"));
        req.setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("pages/order/orderItems.jsp").forward(req, resp);
    }

    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User)req.getSession().getAttribute("user");
        List<Order> orderList = orderService.checkMyOrder(loginUser.getId());
        req.setAttribute("orderList", orderList);
        req.getRequestDispatcher("pages/order/order.jsp").forward(req, resp);
    }

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User)req.getSession().getAttribute("user");
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);
        JdbcUtils.commitAndClose();

        //req.setAttribute("orderId", orderId);
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        //avoid from submitting repeatedly
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }


}
