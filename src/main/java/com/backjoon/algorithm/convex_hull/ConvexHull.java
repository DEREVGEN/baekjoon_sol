package com.backjoon.algorithm.convex_hull;

/*
    edited, 2023-06-17,
   convex_hell 알고리즘
   Minimum enclosing circle 알고리즘 공부하던 중, convex hull에 대해서 알게 되었다.
   이 알고리즘은 2차원 평면 상에서, 포인트가 있을때, 가장 바깥을 감싸는 포인트 들을 구하는 알고리즘이다.

 */

import java.util.ArrayList;
import java.util.List;

public class ConvexHull {
    public static void main(String[] args) {

        Point points[] = new Point[7]; // Point 집합 points 로 정의.
        points[0] = new Point(1, 3);
        points[1] = new Point(3, 2);
        points[2] = new Point(4, 2);
        points[3] = new Point(5, 2);
        points[4] = new Point(7, 3);
        points[5] = new Point(2, 1);
        points[6] = new Point(6, 1);


        convexHull(points, points.length);
    }

    static void convexHull(Point[] points, int N) {
        if (N < 3)
            return; // convex hull 알고리즘은 p1,p2,p3 적어도 3개가 존재해야한다.

        int min = 0; // leftmost point를 구하기 위함.
        for (int i = 1; i < N; i++) {
            if (points[i].x < points[min].x)
                min = i;
        }

        int p1 = min;

        List<Integer> shellPoints = new ArrayList<>();

        do {
            shellPoints.add(p1);
            int p2 = (p1 + 1) % N; // p2 임의로 지정, p2라는 인덱스 값과 p2의 포인트는 아무런 관계성이 없다.

            for (int p3 = 0; p3 < N; p3++) {
                if (counterClock(points, p1, p2, p3)) // 참이라면 counter Clock 관계
                    p2 = p3; // p2 <- p3
            }

            p1 = p2; // p2가 껍데기 포인트라는 것이 확정이 되었음 -> p1로 지정
        } while (p1 != min);


        for (int idx : shellPoints) {
            System.out.println("idx: " + idx + " ( " + points[idx].y + " , " + points[idx].x + " )");
        }
    }

    static boolean counterClock(Point[] points, int p1, int p2, int p3) { // 밑에 조건 식이 참이라면, 3개의 포인트간 counter clock wise 관계, 0보다 작다면 clock, 같다면 colinear
        return (points[p2].x - points[p1].x) * (points[p3].y - points[p2].y) - (points[p3].x - points[p2].x) * (points[p2].y - points[p1].y) > 0;
    }
}

class Point {
    int x;
    int y;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
