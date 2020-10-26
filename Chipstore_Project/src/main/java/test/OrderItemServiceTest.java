package test;

import org.junit.jupiter.api.Test;
import service.OrderItemService;
import service.impl.OrderItemServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemServiceTest {

    @Test
    void checkOrderItemByOrderId() {
        OrderItemService orderItemService = new OrderItemServiceImpl();
        orderItemService.checkOrderItemByOrderId("16036634771981").forEach(System.out::println);
    }

}