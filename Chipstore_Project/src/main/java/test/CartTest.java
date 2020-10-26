package test;

import org.junit.jupiter.api.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"attackDamage+2",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"attackDamage+2",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"attackDamage+2",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"attackDamage+1",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"attackDamage+2",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        cart.updateCount(2,10);
        System.out.println(cart);
    }
}