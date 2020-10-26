package test;

import org.junit.jupiter.api.Test;
import pojo.Cart;
import pojo.CartItem;
import pojo.Order;
import service.OrderService;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void createOrder() {
        OrderService orderService = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"attackDamage+2",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(orderService.createOrder(cart,1));
    }

    @Test
    void checkMyOrder() {
        OrderService orderService = new OrderServiceImpl();
        System.out.println(orderService.checkMyOrder(1));
    }

    @Test
    void managerCheckAllOrders() {
        OrderService orderService = new OrderServiceImpl();
        orderService.managerCheckAllOrders().forEach(System.out::println);
    }

    @Test
    void changeStatus() {
        OrderService orderService = new OrderServiceImpl();
        System.out.println(orderService.changeStatus(new Order("16036634771981",null,null,0,null)));
    }
}