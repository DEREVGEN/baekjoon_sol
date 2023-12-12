package com.backjoon.algorithm.convexhull;

/*
    edited, 2023-06-17,
   convex_hell 알고리즘
   Minimum enclosing circle 알고리즘 공부하던 중, convex hull에 대해서 알게 되었다.
   이 알고리즘은 2차원 평면 상에서, 포인트가 있을때, 가장 바깥을 감싸는 포인트 들을 구하는 알고리즘이다.

 */

import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ConvexHull {
    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();

        points.add(new Point(0, 0));
        points.add(new Point(0, 8));
        points.add(new Point(6, 8));
        points.add(new Point(6, 6));
        points.add(new Point(3, 6));
        points.add(new Point(3, 2));
        points.add(new Point(6, 2));
        points.add(new Point(6, 0));

        List<Point> hullPoints = convexHull(points);
        System.out.println(hullPoints);
    }

    public static List<Point> convexHull(List<Point> points) {

        int minIdx = 0;

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x < points.get(minIdx).x) {
                minIdx = i;
            } else if (points.get(i).x == points.get(minIdx).x &&
                    points.get(i).y < points.get(minIdx).y) {
                minIdx = i;
            }
        }

        Point mPoint = points.get(minIdx);

        points.sort((o1, o2) ->  {
            int res = ccw(mPoint, o1, o2);

            if (res == 0) {
                return Long.compare(dist(mPoint, o1), dist(mPoint, o2));
            }

            return -res;
        });

        Stack<Point> hStack = new Stack<>();

        for (Point p : points) {
            while (hStack.size() > 1 &&
                    ccw(hStack.get(hStack.size()-2), hStack.get(hStack.size()-1), p) <= 0) {
                hStack.pop();
            }
            hStack.push(p);
        }

        // hull points return
        return new ArrayList<>(hStack);
    }

    public static int ccw(Point p1, Point p2, Point p3) {
        // res > 0: counter clock, res < 0: clock, res==0: collinear
        int cal = (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);

        return (cal > 0) ? 1 : (cal < 0) ? -1 : 0;
    }

    public static long dist(Point p1, Point p2) {
        return (long)(p1.x - p2.x)*(p1.x - p2.x) + (long)(p1.y - p2. y)*(p1.y - p2.y);
    }
}