package service;

import pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> checkOrderItemByOrderId(String orderId);

}
