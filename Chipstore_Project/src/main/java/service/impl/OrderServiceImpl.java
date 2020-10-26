package service.impl;

import dao.ChipDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.ChipDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private ChipDao chipDao = new ChipDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    /**
     * create order list by cart and userId, orderId is generated uniquely by time+userId
     * @param cart
	 * @param userId
     * @return
     * @author Haoran Qi
     * @date
     */
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //save order
        orderDao.saveOrder(order);
        //go th/rough each item in cart and covert to order item to store in database
        for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //save orderItem to database
            orderItemDao.saveOrderItem(orderItem);
            Chip chip = chipDao.queryChipById(cartItem.getId());
            chip.setSales(chip.getSales()+cartItem.getCount());
            chip.setStock(chip.getStock()-cartItem.getCount());
            chipDao.updateChip(chip);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> checkMyOrder(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public List<Order> managerCheckAllOrders() {
        return orderDao.queryForAllOrders();
    }

    @Override
    public Integer changeStatus(Order order) {
        return orderDao.updateOrderStatus(order.getStatus()+1,order.getOrderId());
    }
}
