package com.query.model.expression;

import java.util.Arrays;
import java.util.Collection;

public abstract class Expressions {

    private Expressions() {}

    public static <T> Expression<T> and(Expression<T> left, Expression<T> right) {
        return left.and(right);
    }

    public static <T> Expression<T> or(Expression<T> left, Expression<T> right) {
        return left.or(right);
    }

    public static <T> Expression<T> negate(Expression<T> expression) {
        return expression.negate();
    }

    public static <T> Expression<T> eq(String fieldName, Object value) {
        return new EqualsExpression<>(fieldName, value);
    }

    public static <T> Expression<T> greaterThan(String fieldName, Object value) {
        return new GreaterExpression<>(fieldName, value, false);
    }

    public static <T> Expression<T> greaterEqualsThan(String fieldName, Object value) {
        return new GreaterExpression<>(fieldName, value, true);
    }

    public static <T> Expression<T> lessThan(String fieldName, Object value) {
        return new LessExpression<>(fieldName, value, false);
    }

    public static <T> Expression<T> lessEqualsThan(String fieldName, Object value) {
        return new LessExpression<>(fieldName, value, true);
    }

    public static <T> Expression<T> in(String fieldName, Object... values) {
        return in(fieldName, Arrays.asList(values));
    }

    public static <T> Expression<T> in(String fieldName, Collection<?> values) {
        return new InExpression<>(fieldName, values);
    }

    public static <T> Expression<T> like(String fieldName, String value) {
        return new LikeExpression<>(fieldName, value);
    }

    public static <T> Expression<T> between(String fieldName, Object start, Object end) {
        return new BetweenExpression<>(fieldName, start, end);
    }
}
