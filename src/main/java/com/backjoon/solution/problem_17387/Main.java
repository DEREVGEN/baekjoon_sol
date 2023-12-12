package com.backjoon.solution.problem_17387;

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
        points[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        points[1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(input.readLine());
        points[2] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        points[3] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        boolean res = isIntersected(points[0], points[1], points[2], points[3]);

        System.out.println(res ? 1 : 0);
    }


    public static int ccw(Point p1, Point p2, Point p3) {

        // res > 0: counter clock, res < 0: clock, res==0: collinear
        long cal = ((long)p2.x - p1.x) * (p3.y - p1.y) - ((long)p3.x - p1.x) * (p2.y - p1.y);

        if(cal > 0) return 1;
        else if (cal == 0) return 0;
        else return -1;
    }

    public static boolean isIntersected(Point A, Point B, Point C, Point D) {
        int res1 = ccw(A, B, C) * ccw(A, B, D);
        int res2 = ccw(C, D, A) * ccw(C, D, B);

        if (res1 == 0 && res2 == 0) {
            // 같은 선상에 있을 경우
            return !liesOnLine(A.x, B.x, C.x, D.x) && !liesOnLine(A.y, B.y, C.y, D.y);
        }

        return res1 <= 0 && res2 <= 0;
    }

    public static boolean liesOnLine(int a, int b, int c, int d) {
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