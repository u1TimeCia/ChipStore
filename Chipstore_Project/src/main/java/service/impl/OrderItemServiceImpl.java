package service.impl;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import pojo.OrderItem;
import service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public List<OrderItem> checkOrderItemByOrderId(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }
}
