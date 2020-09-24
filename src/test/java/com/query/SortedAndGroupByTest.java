package com.query;

import com.query.utils.CommonUtils;
import com.query.utils.Order;
import com.query.utils.OrderEntry;
import io.vavr.collection.Seq;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedAndGroupByTest {

    @Test
    public void testSort() {
        List<OrderEntry> orders = new ArrayList<>();
        orders.add(new OrderEntry("name"));
        orders.add(new OrderEntry("age", Order.DESC));
        orders.add(new OrderEntry("location"));
        Comparator<Object> comparator = CommonUtils.buildComparator(io.vavr.collection.List.ofAll(orders));

        List<User> users = new ArrayList<>();
        users.add(new User("lisi", 29, "yibin"));
        users.add(new User("lisi", 36, "yibin"));
        users.add(new User("lisi", 42));
        users.add(new User("lisi", 42, "sichang"));
        users.add(new User("lili", 25, "shanghai"));
        users.add(new User("ewertk", 15, "guangdong"));
        users.add(new User("wetry", 35));
        users.add(new User("adgrhy", 50));
        users.add(new User("gerl", 28));
        users.add(new User("gersl", 25, "shanghai"));

        users.forEach(System.out::println);
        users.sort(comparator);
        System.out.println("---------------------------------");
        users.forEach(System.out::println);
    }

    @Test
    public void testGroupBy() {
        List<User> users = new ArrayList<>();
        users.add(new User("gersl", 25, "shanghai"));
        users.add(new User("lisi", 42, "yibin"));
        users.add(new User("adgrhy", 50));
        users.add(new User("lisi", 42, "sichang"));
        users.add(new User("lili", 25, "shanghai"));
        users.add(new User("ewertk", 15, "guangdong"));
        users.add(new User("wetry", 35));
        users.add(new User("lisi", 36, "yibin"));
        users.add(new User("gerl", 28));
        users.add(new User("lisi", 42, "yibin"));

        io.vavr.collection.List<OrderEntry> entries = io.vavr.collection.List.of(new OrderEntry("location"));
        Seq<User> seq = CommonUtils.groupAndSorted(io.vavr.collection.List.ofAll(users),
                io.vavr.collection.List.of("name", "age"), entries);
        seq.forEach(System.out::println);
    }
}
