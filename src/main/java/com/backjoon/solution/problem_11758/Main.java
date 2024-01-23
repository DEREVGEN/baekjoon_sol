package com.backjoon.solution.problem_11758;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        int y1 = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(input.readLine());
        int y2 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(input.readLine());
        int y3 = Integer.parseInt(st.nextToken());
        int x3 = Integer.parseInt(st.nextToken());

        Point p1 = new Point(y1, x1);
        Point p2 = new Point(y2, x2);
        Point p3 = new Point(y3, x3);

        int res = ccw(p1, p2, p3);

        if (res > 0)
            System.out.println("-1");
        else if (res < 0)
            System.out.println("1");
        else
            System.out.println("0");
    }

    public static int ccw(Point p1, Point p2, Point p3) {

        // res < 0: couter clock, res > 0: clock, res==0:collinear
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