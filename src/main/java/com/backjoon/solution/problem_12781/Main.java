package com.backjoon.solution.problem_12781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Point[] points = new Point[4];

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < 4; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Point(x, y);
        }

        boolean res = isIntersected(points[0], points[1], points[2], points[3]);
        System.out.println(res ? 1 : 0);
    }

     public static int ccw(Point p1, Point p2, Point p3) {

        // res > 0: counter clock, res < 0: clock, res==0: collinear
        int cal = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);

        return (cal > 0) ? 1 : (cal < 0) ? -1 : 0;
    }

    public static boolean isIntersected(Point A, Point B, Point C, Point D) {
        int res1 = ccw(A, B, C) * ccw(A, B, D);
        int res2 = ccw(C, D, A) * ccw(C, D, B);

        return res1 < 0 && res2 < 0;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
