package test;


import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import org.junit.jupiter.api.Test;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    @Test
    void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        Order order1 = new Order("16033376428243",new Date(),new BigDecimal(162.50),0,1);
        Order order = new Order("160333764282453",order1.getCreateTime(),new BigDecimal(162.50),0,1);
        orderDao.saveOrder(order);
    }

    @Test
    void queryOrderByUserId() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderByUserId(1));
    }

    @Test
    void queryForAllOrders() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.queryForAllOrders().forEach(System.out::println);
    }

    @Test
    void queryOrderByOrderId() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.queryOrderByOrderId("16036634771981"));
    }


    @Test
    void updateOrderStatus() {
        OrderDao orderDao = new OrderDaoImpl();
        System.out.println(orderDao.updateOrderStatus(2,"16036634771981"));
    }
}