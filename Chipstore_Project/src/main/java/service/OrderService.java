package service;

import pojo.Cart;
import pojo.Order;

import java.util.List;

public interface OrderService {

    String createOrder(Cart cart,Integer userId);

    List<Order> checkMyOrder(Integer userId);

    List<Order> managerCheckAllOrders();

    Integer changeStatus(Order order);
}
