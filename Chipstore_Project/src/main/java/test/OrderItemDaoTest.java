package test;

import dao.OrderItemDao;
import dao.impl.OrderItemDaoImpl;
import org.junit.jupiter.api.Test;
import pojo.OrderItem;

import java.math.BigDecimal;

class OrderItemDaoTest {

    @Test
    void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"critical damage",1,new BigDecimal(100),new BigDecimal(100),"123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"critical damage+2",2,new BigDecimal(100),new BigDecimal(200),"123456789"));
        orderItemDao.saveOrderItem(new OrderItem(null,"critical damage+3",1,new BigDecimal(100),new BigDecimal(100),"123456789"));
    }

    @Test
    void queryOrderItemByOrderId() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.queryOrderItemByOrderId("16036634771981").forEach(System.out::println);
    }
}