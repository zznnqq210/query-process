package com.query.model.expression;

import java.util.function.Predicate;

public interface Expression<T> extends Predicate<T> {

    default Expression<T> and(Expression<T> expression) {
        return new CompoundExpression<>(this, expression, BooleanPredicate.AND);
    }

    default Expression<T> or(Expression<T> expression) {
        return new CompoundExpression<>(this, expression, BooleanPredicate.OR);
    }

    default Expression<T> negate() {
        return t -> !test(t);
    }

    enum BooleanPredicate {
        AND, OR
    }
}
