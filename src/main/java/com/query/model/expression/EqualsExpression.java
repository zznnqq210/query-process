package com.query.model.expression;

import static com.query.utils.CommonUtils.*;

public class EqualsExpression<T> implements Expression<T> {

    private final String fieldName;

    private final Object value;

    EqualsExpression(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public boolean test(T o) {
        return withReadMethod(o, fieldName, v -> eq(v, value));
    }
}
