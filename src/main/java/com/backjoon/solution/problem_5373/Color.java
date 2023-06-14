package com.backjoon.solution.problem_5373;

public enum Color {
    white(0), yellow(1), red(2), orange(3), green(4), blue(5);

    private final int value;
    Color(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
