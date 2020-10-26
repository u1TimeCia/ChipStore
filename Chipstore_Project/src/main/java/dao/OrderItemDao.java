package dao;

import pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    int saveOrderItem(OrderItem orderItem);
    List<OrderItem> queryOrderItemByOrderId(String orderId);

}
