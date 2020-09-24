package com.query.utils;

import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;
import io.vavr.control.Try;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

public abstract class CommonUtils {

    private CommonUtils() {}

    public static <T> Object getFieldValue(T domain, String fieldName) {
        return withReadMethod(domain, fieldName, v -> v);
    }

    public static <T, R> R withReadMethod(T domain, String fieldName, Function<Object, R> function) {
        Objects.requireNonNull(domain);
        Objects.requireNonNull(function);

        return Try.of(() -> getReadMethod(domain.getClass(), fieldName))
                .mapTry(readMethod -> readMethod.invoke(domain))
                .map(function)
                .get();
    }

    // 既有分组又有排序，先分组，对每个分组进行排序
    public static <T> Seq<T> groupAndSorted(Seq<T> data, List<String> groups, List<OrderEntry> orders) {
        Comparator<T> comparator = buildComparator(orders);
        if (data.isEmpty() || groups == null || groups.isEmpty()) {
            return comparator == null ? data : data.sorted(comparator);
        }

        String head = groups.head();
        // 去掉分组字段
        List<OrderEntry> tailOrder = orders == null ? null : orders.removeFirst(o -> head.equals(o.getField()));

        Map<Object, ? extends Seq<T>> groupMap = data.groupBy(t -> CommonUtils.getFieldValue(t, groups.head()));
        List<String> tail = groups.tail();

        // 递归继续分组
        return groupMap.values().map(l -> groupAndSorted(l, tail, tailOrder)).flatMap(l -> l);
    }

    // 排序
    public static <T> List<T> sortList(List<T> data, List<OrderEntry> orderEntries) {
        Comparator<Object> comparator = buildComparator(orderEntries);
        return data.sorted(comparator);
    }

    // 获取对象的comparator
    public static <T> Comparator<T> buildComparator(List<OrderEntry> orders) {
        if (orders == null)
            return null;

        return (o1, o2) -> {
            for (OrderEntry orderEntry : orders) {
                Comparator<T> comparator = getSortedComparator(orderEntry);
                int compare = comparator.compare(o1, o2);
                if (compare != 0)
                    return compare;
            }
            return 0;
        };
    }

    @SuppressWarnings("unchecked")
    public static int compare(Object o1, Object o2) {
        if (o1 == null && o2 == null)
            return 0;
        else if (o1 == null)
            return -1;
        else if (o2 == null)
            return 1;
        else {
            Class<?> o1Class = o1.getClass();
            Class<?> o2Class = o2.getClass();
            assert o1Class == o2Class && Comparable.class.isAssignableFrom(o1Class): "类型不匹配";

            return ((Comparable<Object>) o1).compareTo(o2);
        }
    }

    public static boolean eq(Object o1, Object o2) {
        return Objects.equals(o1, o2);
    }

    // 排序Comparator
    private static <T> Comparator<T> getSortedComparator(OrderEntry orderEntry) {
        if (orderEntry == null)
            return null;

        return (o1, o2) -> {
            String field = orderEntry.getField();
            Order order = orderEntry.getOrder();
            Object v1 = getFieldValue(o1, field);
            Object v2 = getFieldValue(o2, field);
            return order == Order.DESC ? compare(v2, v1) : compare(v1, v2);
        };
    }

    private static Method getReadMethod(Class<?> domainClass, String fieldName) throws Exception {
        checkDomainType(domainClass);
        PropertyDescriptor[] pds = Introspector.getBeanInfo(domainClass)
                .getPropertyDescriptors();
        if (pds == null || pds.length == 0)
            throw new RuntimeException("clazz没有属性或没有相应get/set方法");

        return List.of(pds)
                .find(pd -> pd != null && pd.getName().equals(fieldName))
                .map(PropertyDescriptor::getReadMethod)
                .getOrElseThrow(() -> new NoSuchFieldException("未知属性" + fieldName));
    }

    private static void checkDomainType(Class<?> clazz) {
        if (clazz.isArray() || clazz.isPrimitive() || clazz.isEnum() ||
                (clazz.getPackage() != null && clazz.getPackage().getName().startsWith("java.lang")))
            throw new IllegalArgumentException("clazz: " + clazz + " 类型不合法");
    }
}
