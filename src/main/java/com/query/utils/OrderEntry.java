package com.query.utils;

import java.io.Serializable;
import java.util.Objects;

public class OrderEntry implements Serializable {

    private static final long serialVersionUID = -5143003207202364342L;

    private final String field; // 排序属性名

    private final Order order; // 排序方式

    public OrderEntry(String field) {
        this(field, Order.ASC);
    }

    public OrderEntry(String field, Order order) {
        assert field != null && !field.isEmpty() && order != null: "参数不能为空";
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderEntry{" +
                "field='" + field + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntry)) return false;
        OrderEntry that = (OrderEntry) o;
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(field);
    }
}
