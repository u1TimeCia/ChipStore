package web;

import com.google.gson.Gson;
import pojo.Cart;
import pojo.CartItem;
import pojo.Chip;
import service.ChipService;
import service.impl.ChipServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private ChipService chipService = new ChipServiceImpl();
    /**
     * add item to cart
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get item id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Chip chip = chipService.queryChipById(id);
        //convert chip info to CartItem
        CartItem cartItem = new CartItem(chip.getId(),chip.getName(),1,chip.getPrice(),chip.getPrice());
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("chipName", cartItem.getName());
        //redirect to home page
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * delete item
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get item id
        Integer id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            //delete item by ID
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * clear cart
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * update quantity of user's choice
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = WebUtils.parseInt(req.getParameter("chipId"), 0);
        Integer count = WebUtils.parseInt(req.getParameter("count"), 1);
        Cart cart  = (Cart)req.getSession().getAttribute("cart");
        if(cart!= null){
            //modify quantity
            cart.updateCount(id, count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
