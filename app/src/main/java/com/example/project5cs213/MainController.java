package com.example.project5cs213;

import com.example.project5cs213.model.*;

import java.util.ArrayList;

public class MainController {

    private static ArrayList<Order> orders = new ArrayList<Order>();
    private static ArrayList<MenuItem> currentOrder = new ArrayList<MenuItem>();
    private static int id = 1;

    public static ArrayList<Order> getOrders() {return orders;}

    public static void addOrder(Order order) {
        orders.add(order);
        order.setOrderNumber(id);
        id++;
    }

    public static void removeOrder(Order order) {orders.remove(order);}

    public static ArrayList<MenuItem> getCurrentOrder() {return currentOrder;}

    public static void addMenuItem(MenuItem item) {currentOrder.add(item);}

    public static void removeMenuItem(MenuItem item) {currentOrder.remove(item);}

    public static void clearCurrentOrder(){currentOrder.clear();}

}
