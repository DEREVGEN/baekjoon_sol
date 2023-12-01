package com.backjoon.solution.problem_10254;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {

            int N = Integer.parseInt(input.readLine());

            List<Point> inputPoints = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());

                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                inputPoints.add(new Point(y, x));
            }

            convexHull(inputPoints, N, sb);
        }

        System.out.println(sb);

    }

    public static void convexHull(List<Point> inputPoints, int N, StringBuilder sb) {

        // y를 기준으로 가장 작은 포인트,(같다면, 가장 작은 x를 기준으로,)
        int minIdx = 0;
        for (int i = 1; i < N; i++) {
            if (inputPoints.get(minIdx).y > inputPoints.get(i).y) {
                minIdx = i;
            } else if (inputPoints.get(minIdx).y == inputPoints.get(i).y
                    && inputPoints.get(minIdx).x > inputPoints.get(i).x) {
                minIdx = i;
            }
        }

        Point sp = inputPoints.get(minIdx);

        // 태두리에 대한 점을 ccw로 정렬
        inputPoints.sort((o1, o2) -> {
            int r = ccw(sp, o1, o2);

            if (r == 0) {
                if (dist(sp, o1) < dist(sp, o2))
                    return -1;
                return 1;
            }
            return r;
        });


        // 태두리의 point만 얻는다.
        Stack<Point> shellP = new Stack<>();

        for (Point p : inputPoints) {
            while (shellP.size() > 1 && ccw(shellP.get(shellP.size()-2), shellP.get(shellP.size()-1), p) >= 0) {
                shellP.pop();
            }
            shellP.push(p);
        }

        rotatingCalipers(shellP, sb);
    }

    public static void rotatingCalipers(List<Point> hPoints, StringBuilder sb) {
        // get converx hull points next, rotating points

        int n = hPoints.size();

        long max = Long.MIN_VALUE;
        Point fP1 = null, fP2 = null;

        int j = 1;
        // starting point is 0, so based on (n-1, 0), compare k point.
        for (int i = 0; i < n; i++) {

            long d = dist(hPoints.get(i), hPoints.get(j % n));
            if (max < d) {
                max = d;
                fP1 = hPoints.get(i);
                fP2 = hPoints.get(j % n);
            }
            // i 부터 시작
            while ((j + 1) % n != i && cccw(hPoints.get(i), hPoints.get((i+1) % n), hPoints.get(j % n), hPoints.get((j+1) % n)) <= 0) {
                d = dist(hPoints.get(i), hPoints.get(j % n));
                if (max < d) {
                    max = d;
                    fP1 = hPoints.get(i);
                    fP2 = hPoints.get(j % n);
                }
                j++;
            }
        }

        sb.append(fP1.y + " " + fP1.x + " " + fP2.y + " " + fP2.x + "\n");

    }

    public static int ccw(Point p1, Point p2, Point p3) {
        long r = area(p1, p2, p3);

        // r: result, r < 0 counter clock, r > 0 clock, r == 0 collinear
        return (r < 0) ? -1 : (r > 0) ? 1 : 0;
    }

    static Point tempPoint = new Point(0, 0);

    public static int cccw(Point p1, Point p2, Point p3, Point p4) {
        // p3 -> p4 벡터를 p2에 옮김.
        tempPoint.y = p4.y - p3.y + p2.y;
        tempPoint.x = p4.x - p3.x + p2.x;

        // r: result, r < 0 counter clock, r > 0 clock, r == 0 collinear
        return ccw(p1, p2, tempPoint);
    }

    public static long area(Point p1, Point p2, Point p3) {
        // area of tri
        return (long)(p3.x - p2.x) * (p2.y - p1.y) - (long)(p2.x - p1.x) * (p3.y - p2.y);
    }

    public static long absArea(Point p1, Point p2, Point p3) {
        return Math.abs(area(p1, p2, p3));
    }

    public static long dist(Point p1, Point p2) {
        return (long)(p1.x - p2.x)*(p1.x - p2.x) + (long)(p1.y - p2.y)*(p1.y - p2.y);
    }
}

class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}