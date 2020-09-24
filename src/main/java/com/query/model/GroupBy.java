package com.query.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroupBy {

    // 分组字段
    private final List<String> fields;

    public GroupBy() {
        this(new ArrayList<>());
    }

    public GroupBy(List<String> fields) {
        this.fields = new ArrayList<>(fields);
    }

    public void addField(String field) {
        addFields(field);
    }

    public void addFields(String...fields) {
        addFields(Arrays.asList(fields));
    }

    public void addFields(List<String> fields) {
        this.fields.addAll(fields);
    }

    public List<String> getFields() {
        return this.fields.stream()
                .filter(s -> s != null && !s.isEmpty())
                .distinct()
                .collect(Collectors.toList());
    }
}
