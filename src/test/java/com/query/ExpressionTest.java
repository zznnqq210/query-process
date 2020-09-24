package com.query;

import static com.query.model.expression.Expressions.*;

import com.query.model.expression.Expression;
import org.junit.Test;

public class ExpressionTest {

    @Test
    public void testExpression() {
        User user = new User("lisi", 29, "yibin");

        // name='lisi' and (age > 29 or location like '%ib%')
        Expression expression = eq("name", "lisi")
                .and(greaterThan("age", 29)
                        .or(like("location", "%ib%")));
        System.out.println(expression.test(user));
    }
}
