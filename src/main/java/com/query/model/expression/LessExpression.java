package com.query.model.expression;

import static com.query.utils.CommonUtils.compare;
import static com.query.utils.CommonUtils.withReadMethod;

public class LessExpression<T> implements Expression<T> {

    private final String fieldName;

    private final Object value;

    private final boolean includeEquals;

    LessExpression(String fieldName, Object value, boolean includeEquals) {
        this.fieldName = fieldName;
        this.value = value;
        this.includeEquals = includeEquals;
    }

    @Override
    public boolean test(T o) {
        return withReadMethod(o, fieldName,
                v -> includeEquals ? compare(v, value) <= 0 : compare(v, value) < 0);
    }
}
