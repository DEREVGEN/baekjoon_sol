package com.backjoon.solution.problem_1708;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Point(y, x);
        }

        convexHull(points, points.length);
    }

    static void convexHull(Point[] points, int N) {

        int min = 0;

        for (int i = 1; i < N; i++) { // 가장 작은 y 값 중, 가장 작은 x 값을 가진 포인트
            if (points[min].y > points[i].y)
                min = i;
            else if (points[min].y == points[i].y) {
                if (points[min].x > points[i].x)
                    min = i;
            }
        }

        Point sp = points[min]; // sp = starting point

        // points는 정렬을 하는데, sp 에 대해서 반시계로 정렬, 같은 collinear 선상에 위치하면 거리기반으로 가까운 순 우선..

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) { // 오름차 순, a < b return -1 값. 그래서, sp, a, b 간의 counter clock이라면, b가 우선

                int r = ccw(sp, a, b);

                if (r == 1) // sp-a-b 관계에서, 반시계적 위치에 존재하면, 우선 배열에 넣어야 한다.
                    return -1;
                else if (r == -1)
                    return 1;
                else { // r == 0
                    if (dist(sp, a) < dist(sp, b))
                        return -1;
                }
                return 1;
            }
        });

        Stack<Point> shellP = new Stack<>();
        shellP.push(sp);

        for (int i = 1; i < N; i++) {
            while(shellP.size() > 1 && ccw(shellP.get(shellP.size()-2), shellP.get(shellP.size()-1), points[i]) <= 0) {
                shellP.pop();
            }
            shellP.push(points[i]);
        }

        System.out.println(shellP.size());
    }

    static int ccw(Point p1, Point p2, Point p3) {
        long r = (p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y);

        // r > 0 counter clock, r < 0 clock, r == 0 collinear
        if (r > 0)
            return 1;
        else if (r < 0)
            return -1;
        else
            return 0;
    }

    static long dist(Point a, Point b) { // 거리계산 but not sqrt 연산
        return (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y);
    }
}

class Point {
    long x;
    long y;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "( " + y + " , " + x + " )";
    }
}