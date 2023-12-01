package com.backjoon.algorithm.ccw;

/*
    ccw 알고리즘(벡터, 기하 알고리즘)

    3개의 포인트 간에, 시계, 반시계, collinear 판별 하는 알고리즘
 */

public class Main {

    public static void main(String[] args) {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(2, -1);

        int res = ccw(p1, p2, p3);

        if (res > 0)
            System.out.println("counter clock wise");
        else if (res < 0)
            System.out.println("clock wise");
        else
            System.out.println("collinear");
    }

    public static int ccw(Point p1, Point p2, Point p3) {

        // res > 0: couter clock, res < 0: clock, res==0:collinear
        return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
    }
}

class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}