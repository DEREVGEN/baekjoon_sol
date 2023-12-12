package com.backjoon.solution.problem_2699;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // input
            List<Point> points = new ArrayList<>();
            int N = Integer.parseInt(input.readLine());

            int l = N / 5;
            if (N % 5 != 0)
                l++;
            for (int i = 1; i <= l; i++) {
                st = new StringTokenizer(input.readLine());
                while (st.hasMoreTokens()) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());

                    points.add(new Point(x, y));
                }
            }

            // solve
            List<Point> hPoints = convexHull(points);
            printPoints(hPoints);
        }
    }

    public static List<Point> convexHull(List<Point> points) {
        int n = points.size();

        Point minP = points.get(0);
        for (int i = 1; i < n; i++) {
            Point p = points.get(i);
            if (p.y > minP.y) {
                minP = p;
            } else if (p.y == minP.y && p.x < minP.x) {
                minP = p;
            }
        }

        Point mp = minP;
        points.sort((o1, o2) -> {
            int a = ccw(mp, o1, o2);

            if (a == 0) {
                return Long.compare(dist(mp, o1), dist(mp, o2));
            }
            return -a;
        });

        Stack<Point> hStack = new Stack<>();
        for (Point p : points) {
            while (hStack.size() > 1 && ccw(hStack.get(hStack.size()-2), hStack.get(hStack.size()-1), p) <= 0) {
                hStack.pop();
            }
            hStack.push(p);
        }

        return new ArrayList<>(hStack);
    }

    public static long dist(Point p1, Point p2) {
        return (long) (p1.x - p2.x) * (p1.x - p2.x) + (long) (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static int ccw(Point p1, Point p2, Point p3) {
        // res > 0: counter clock, res < 0: clock, res==0: collinear
        int cal = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);

        return (cal > 0) ? 1 : (cal < 0) ? -1 : 0;
    }


    public static void printPoints(List<Point> hPoints) {
        // 첫좌표를 기준으로 역으로 출력해야함.
        StringBuilder sb = new StringBuilder();

        sb.append(hPoints.size() + "\n");
        sb.append(hPoints.get(0).toString());

        for (int i = hPoints.size() - 1; i > 0; i--) {
            Point p = hPoints.get(i);
            sb.append(p.toString());
        }

        System.out.print(sb);
    }

}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y + "\n";
    }
}