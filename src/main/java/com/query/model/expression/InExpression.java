package com.query.model.expression;

import java.util.Collection;

import static com.query.utils.CommonUtils.eq;
import static com.query.utils.CommonUtils.withReadMethod;

public class InExpression<T> implements Expression<T> {

    private final String fieldName;

    private final Collection<?> values;

    InExpression(String fieldName, Collection<?> values) {
        this.fieldName = fieldName;
        this.values = values;
    }

    @Override
    public boolean test(T o) {
        return withReadMethod(o, fieldName, v -> {
            if (values == null || values.isEmpty())
                return false;
            return values.stream().anyMatch(obj -> eq(v, obj));
        });
    }
}
