package com.query.model;

import java.io.Serializable;

public class Limit implements Serializable {

    private static final long serialVersionUID = -5213247906857488886L;

    private final int start;

    private final int end;

    public Limit(int end) {
        this(0, end);
    }

    public Limit(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return Math.max(start, 0);
    }

    public int getEnd() {
        return Math.max(end, 0);
    }
}
