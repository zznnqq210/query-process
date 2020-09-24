package com.query.model.expression;

public class CompoundExpression<T> implements Expression<T> {

    private final Expression<T> left;

    private final Expression<T> right;

    private final BooleanPredicate predicate;

    CompoundExpression(Expression<T> left, Expression<T> right, BooleanPredicate predicate) {
        this.left = left;
        this.right = right;
        this.predicate = predicate;
    }

    @Override
    public boolean test(T o) {
        if (predicate == BooleanPredicate.OR)
            return left.test(o) || right.test(o);

        return left.test(o) && right.test(o);
    }
}
