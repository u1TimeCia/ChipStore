package web;

import pojo.Chip;
import pojo.HomePage;
import pojo.Page;
import service.ChipService;
import service.impl.ChipServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientChipServlet extends BaseServlet {

    private ChipService chipService = new ChipServiceImpl();
    /**
     * tackle paging function for index.jsp this is different from the page func in chipServlet
     * @param req
     * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"),HomePage.PAGE_SIZE);
        Page<Chip> page = chipService.page(pageNo,pageSize);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"),HomePage.PAGE_SIZE);
        Integer min = WebUtils.parseInt(req.getParameter("min"), 0);
        Integer max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        Page<Chip> page = chipService.pageByPrice(pageNo,pageSize,min,max);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
