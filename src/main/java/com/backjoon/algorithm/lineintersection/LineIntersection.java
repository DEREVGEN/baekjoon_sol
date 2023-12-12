package com.backjoon.algorithm.lineintersection;

/*
    좌표를 통해, 벡터로 교차하는지 검증 하는 알고리즘이다.

    두 선분간의 ccw를 활용하여, 검증을 한다.

    A-B, C-D의 관계에서, ccw 판정을 이용하여, count * counter clock = 음수 를 이용한다.

    이때, collinear 한 관계에서 따로 검사를 해야한다.
 */

public class LineIntersection {

    public static void main(String[] args) {
        Point p1 = new Point(0 ,0);
        Point p2 = new Point(5 ,0);
        Point p3 = new Point(3 ,0);
        Point p4 = new Point(7 ,0);

        boolean res = isIntersected(p1, p2, p3, p4);
        System.out.println(res);
    }

    public static int ccw(Point p1, Point p2, Point p3) {

        // res > 0: counter clock, res < 0: clock, res==0: collinear
        int cal = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);

        return (cal > 0) ? 1 : (cal < 0) ? -1 : 0;
    }

    public static boolean isIntersected(Point A, Point B, Point C, Point D) {
        int res1 = ccw(A, B, C) * ccw(A, B, D);
        int res2 = ccw(C, D, A) * ccw(C, D, B);

        if (res1 == 0 && res2 == 0) {
            // 같은 선상에 있을 경우
            return !pointAboveLine(A.x, B.x, C.x, D.x) && !pointAboveLine(A.y, B.y, C.y, D.y);
        }

        return res1 <= 0 && res2 <= 0;
    }

    public static boolean pointAboveLine(int a, int b, int c, int d) {
        int temp;
        if (a > b) {
            temp = a;
            a = b;
            b = temp;
        }
        if (c > d) {
            temp = c;
            c = d;
            d = temp;
        }
        return b < c || d < a;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
