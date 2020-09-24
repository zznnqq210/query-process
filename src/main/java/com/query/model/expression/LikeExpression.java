package com.query.model.expression;

import static com.query.utils.CommonUtils.eq;
import static com.query.utils.CommonUtils.withReadMethod;

public class LikeExpression<T> implements Expression<T> {

    private static final String FLAG_CHAR = "%";

    private final String fieldName;

    private final String value;

    LikeExpression(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public boolean test(T o) {
        return withReadMethod(o, fieldName, v -> {
            assert v instanceof String: "like仅支持String类型";
            String fieldValue = (String) v;
            return like(fieldValue, value);
        });
    }

    private static boolean like(String fieldValue, String value) {
        String s = value;
        // 空值返回false
        if (s == null || s.isEmpty())
            return false;

        // '%' '%%'返回true, 其他字符串去掉收尾%后比较
        if (s.startsWith(FLAG_CHAR) && s.endsWith(FLAG_CHAR)) {
            s = s.substring(1, s.length() - 1);
            return fieldValue.contains(s);
        } else if (s.startsWith(FLAG_CHAR)) {
            s = s.substring(1);
            return fieldValue.endsWith(s);
        } else if (s.endsWith(FLAG_CHAR)) {
            s = s.substring(0, s.length() - 1);
            return fieldValue.startsWith(s);
        } else {
            // 默认情况，判断相等
            return eq(fieldValue, s);
        }
    }
}
