package web;

import pojo.Chip;
import pojo.Page;
import service.ChipService;
import service.impl.ChipServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChipServlet extends BaseServlet {

    private ChipService chipService = new ChipServiceImpl();

    /**
     * tackle paging function
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        Page<Chip> page = chipService.page(pageNo,pageSize);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/chip_manager.jsp").forward(req, resp);
    }




    /**
     * add new chip to back-stage management
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo += 1;
        Chip chip = WebUtils.copyParamToBean(req.getParameterMap(), new Chip());
        chipService.addChip(chip);
        //finish doing operation add, go to the item which has been updated
        resp.sendRedirect(req.getContextPath()+"/manager/chipServlet?action=page&pageNo="+pageNo);
    }

    /**
     * delete chip by id
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = Integer.parseInt(req.getParameter("pageNo"));
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        chipService.deleteChipById(i);
        //finish doing operation delete, go to the item which has been updated
        resp.sendRedirect(req.getContextPath()+"/manager/chipServlet?action=page&pageNo="+pageNo);
    }

    /**
     * update chip by id
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNo = Integer.parseInt(req.getParameter("pageNo"));
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        Chip chip = WebUtils.copyParamToBean(req.getParameterMap(), new Chip());
        chip.setId(i);
        chipService.updateChip(chip);
        //finish doing operation updated, go to the item which has been updated
        resp.sendRedirect(req.getContextPath()+"/manager/chipServlet?action=page&pageNo="+pageNo);
    }

    /**
     * it will store all the chip data and send request to chip_manager to display all chips
     * @param req
	 * @param resp
     * @return
     * @author Haoran Qi
     * @date
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Chip> chips = chipService.queryChips();
        req.setAttribute("chipList", chips);
        req.getRequestDispatcher("/pages/manager/chip_manager.jsp").forward(req, resp);
    }


    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        req.setAttribute("pageNo", pageNo);
        String id = req.getParameter("id");
        int i = Integer.parseInt(id);
        Chip chip = chipService.queryChipById(i);
        req.setAttribute("chip", chip);
        req.getRequestDispatcher("/pages/manager/chip_edit.jsp").forward(req, resp);
    }
}
