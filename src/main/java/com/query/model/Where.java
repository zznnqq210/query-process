package com.query.model;

import com.query.model.expression.Expression;

public class Where<T> {

    private final Expression<T> expression;

    public Where(Expression<T> expression) {
        this.expression = expression;
    }

    public Expression<T> getExpression() {
        return expression;
    }

    public boolean toPredicate(T o) {
        return expression == null || expression.test(o);
    }
}
