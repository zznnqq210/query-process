package com.query.model;

import com.query.utils.Order;
import com.query.utils.OrderEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderBy implements Serializable {

    private static final long serialVersionUID = 590066252820361260L;

    private final List<OrderEntry> orders;

    public OrderBy() {
        this(new ArrayList<>());
    }

    public OrderBy(List<OrderEntry> orders) {
        this.orders = new ArrayList<>(orders);
    }

    public void addOrder(String field) {
        addOrder(new OrderEntry(field));
    }

    public void addOrder(String field, Order order) {
        addOrder(new OrderEntry(field, order));
    }

    public void addOrder(OrderEntry orderEntry) {
        addOrders(orderEntry);
    }

    public void addOrders(OrderEntry... orders) {
        addOrders(Arrays.asList(orders));
    }

    public void addOrders(List<OrderEntry> orders) {
        this.orders.addAll(orders);
    }

    public List<OrderEntry> getOrders() {
        return this.orders.stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }
}