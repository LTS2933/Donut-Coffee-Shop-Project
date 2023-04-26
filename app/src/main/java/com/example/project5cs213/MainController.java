package com.example.project5cs213;

import com.example.project5cs213.model.*;

import java.util.ArrayList;

/**
 * This class is responsible for holding data and creating methods for
 * all activities to use related to orders and menu items
 * @author Christian Osma, Liam Smith
 */
public class MainController {

    private static ArrayList<Order> orders = new ArrayList<Order>();
    private static ArrayList<MenuItem> currentOrder = new ArrayList<MenuItem>();
    private static int id = 1;

    /**
     * Getter method for getting all the orders
     * @return ArrayList containing all the orders
     */
    public static ArrayList<Order> getOrders() {return orders;}

    /**
     * Adds the inputted order to the order list
     * @param order Order to be added
     */
    public static void addOrder(Order order) {
        orders.add(order);
        order.setOrderNumber(id);
        id++;
    }

    /**
     * Removes the specified order in the order list
     * @param order Order to be removed
     */
    public static void removeOrder(Order order) {orders.remove(order);}

    /**
     * Getter method for getting the menu items in the current order
     * @return ArrayList containing all items in the current order
     */
    public static ArrayList<MenuItem> getCurrentOrder() {return currentOrder;}

    /**
     * Adds the inputted menu item to the current order
     * @param item MenuItem to be added
     */
    public static void addMenuItem(MenuItem item) {currentOrder.add(item);}

    /**
     * Removes the menu item from the current order
     * @param item MenuItem to be removed
     */
    public static void removeMenuItem(MenuItem item) {currentOrder.remove(item);}

    /**
     * Changes the current order list to an empty list
     */
    public static void clearCurrentOrder(){currentOrder.clear();}

}