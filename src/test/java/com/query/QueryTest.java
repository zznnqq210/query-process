package com.query;

import com.query.model.*;
import com.query.model.expression.Expression;
import com.query.model.expression.Expressions;
import com.query.utils.Order;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.query.processor.QueryProcessors.query;

public class QueryTest {

    @Test
    public void testLimit() {
        Limit limit = new Limit(2, 5);
        List<DataObject> list = query(getData(), limit);
        list.forEach(System.out::println);
    }

    @Test
    public void testWhere() {
        Expression<DataObject> type = Expressions.eq("type", 2);
        Expression<DataObject> name = Expressions.like("name", "%ang");
        Expression<DataObject> expression = type.and(name);
        Where<DataObject> where = new Where<>(expression);
        List<DataObject> list = query(getData(), where);
        list.forEach(System.out::println);
    }

    @Test
    public void testGroupBy() {
        GroupBy groupBy = new GroupBy();
        groupBy.addFields("name", "createTime");
        List<DataObject> list = query(getData(), groupBy);
        list.forEach(System.out::println);
    }

    @Test
    public void testOrderBy() {
        OrderBy orderBy = new OrderBy();
        orderBy.addOrder("name", Order.DESC);
        orderBy.addOrder("type");
        orderBy.addOrder("createTime", Order.DESC);
        List<DataObject> list = query(getData(), orderBy);
        list.forEach(System.out::println);
    }

    private List<DataObject> getData() {
        LocalDateTime now = LocalDateTime.now();
        return Arrays.asList(
                new DataObject(1L, now, "zhang", 1, false),
                new DataObject(2L, now.minusDays(1), "guo", 2, false),
                new DataObject(3L, now, "zhang", 1, true),
                new DataObject(4L, now, "wang", 2, false),
                new DataObject(5L, now.plusDays(1), "zhang", 2, true),
                new DataObject(6L, now, "zhang", 1, true),
                new DataObject(7L, now.minusDays(1), "zeng", 2, true),
                new DataObject(8L, now, "wang", 2, false),
                new DataObject(9L, now.minusDays(1), "zhang", 0, false),
                new DataObject(10L, now, "zhang", 0, true),
                new DataObject(11L, now, "zeng", 2, false),
                new DataObject(12L, now.plusDays(1), "zhang", 2, false),
                new DataObject(13L, now, "zeng", 0, true),
                new DataObject(14L, now, "zhang", 1, false)
        );
    }
}
