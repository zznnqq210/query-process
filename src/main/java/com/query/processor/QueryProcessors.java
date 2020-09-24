package com.query.processor;

import com.query.model.GroupBy;
import com.query.model.Limit;
import com.query.model.OrderBy;
import com.query.model.Where;
import com.query.utils.CommonUtils;
import com.query.utils.OrderEntry;
import io.vavr.collection.List;
import io.vavr.collection.Seq;

// 查询处理
public abstract class QueryProcessors {

    private QueryProcessors() {}

    public static <T> java.util.List<T> query(java.util.List<T> data, Limit limit) {
        return query(data, null, null, null, limit);
    }

    public static <T> java.util.List<T> query(java.util.List<T> data, OrderBy orderBy) {
        return query(data, null, null, orderBy);
    }

    public static <T> java.util.List<T> query(java.util.List<T> data, GroupBy groupBy) {
        return query(data, null, groupBy);
    }

    public static <T> java.util.List<T> query(java.util.List<T> data, Where<T> where) {
        return query(data, where, null);
    }

    public static <T> java.util.List<T> query(java.util.List<T> data, Where<T> where, GroupBy groupBy) {
        return query(data, where, groupBy, null);
    }

    public static <T> java.util.List<T> query(java.util.List<T> data, Where<T> where, GroupBy groupBy, OrderBy orderBy) {
        return query(data, where, groupBy, orderBy,null);
    }

    public static <T> java.util.List<T> query(java.util.List<T> data, Where<T> where, GroupBy groupBy,
                                    OrderBy orderBy, Limit limit) {
        if (data == null || data.isEmpty())
            return data;

        Seq<T> result = List.ofAll(data);
        if (where != null)
            result = result.filter(where::toPredicate);

        // 处理groupBy和orderBy
        List<String> groupList = groupBy == null ? List.empty() : List.ofAll(groupBy.getFields());
        List<OrderEntry> orders = orderBy == null ? List.empty() : List.ofAll(orderBy.getOrders());
        result = CommonUtils.groupAndSorted(result, groupList, orders);

        return limitResult(result, limit).toJavaList();
    }

    private static <T> Seq<T> limitResult(Seq<T> result, Limit limit) {
        if (result != null && !result.isEmpty() && limit != null) {
            int from = limit.getStart();
            int end = limit.getEnd();
            if (from > result.size())
                from = result.size();
            if (end > result.size())
                end = result.size();
            if (end < from)
                end = from;
            return result.subSequence(from, end);
        }

        return result;
    }
}
