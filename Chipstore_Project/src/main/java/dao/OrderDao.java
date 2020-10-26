package dao;

import pojo.Order;

import java.util.List;

public interface OrderDao {

    int saveOrder(Order order);
    List<Order> queryOrderByUserId(Integer userId);
    Order queryOrderByOrderId(String orderId);
    List<Order> queryForAllOrders();
    Integer updateOrderStatus(Integer status,String orderId);

}
