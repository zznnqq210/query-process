package com.query.model.expression;

import static com.query.utils.CommonUtils.*;

// 包含首尾边界
public class BetweenExpression<T> implements Expression<T> {

    private final String fieldName;

    private final Object start;

    private final Object end;

    BetweenExpression(String fieldName, Object start, Object end) {
        assert start != null && end != null: "start or end is null";

        this.fieldName = fieldName;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean test(T o) {
        return withReadMethod(o, fieldName, v -> compare(v, start) >= 0 && compare(v, end) <= 0);
    }
}
