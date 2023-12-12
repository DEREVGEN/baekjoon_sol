package com.backjoon.algorithm.convexhull;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.y = y;
        this.x = x;
    }
}
