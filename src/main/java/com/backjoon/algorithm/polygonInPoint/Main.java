package com.backjoon.algorithm.polygonInPoint;

/*
    컨벡스 헐로 구해진 다각형 안에서의 내부의 점 판별

    1. 비교 할 포인트 기준으로 오른쪽 x축을 생성
    2. 간선과 그어진 선과의 접하는 개수 파악 (이때, 기울기를 이용하여 판별한다.)
    2-1. 만약, 홀수 라면 내부의 점
    2-2. 짝수 라면 외부의 점
 */

import com.backjoon.algorithm.convexhull.ConvexHull;
import com.backjoon.algorithm.convexhull.Point;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Point> points = new ArrayList<>();

        points.add(new Point(100, 700));
        points.add(new Point(600, 600));
        points.add(new Point(500, 300));
        points.add(new Point(500, 100));

        List<Point> hullP = ConvexHull.convexHull(points);

        Point internalP = new Point(-1, 5);
        System.out.println(hullP);


        boolean res = isPolygonInPoint(hullP, internalP);

        System.out.println(res);
    }

    public static boolean isPolygonInPoint(List<Point> polygonP, Point testP) {

        int n = polygonP.size();

        boolean isOddNum = false;

        for (int i = 0, j = n - 1; i < n; i++) {

            Point p1 = polygonP.get(i);
            Point p2 = polygonP.get(j);

            if(isInside(p1, p2, testP) && compareSlope(p1, p2, testP))
                isOddNum = !isOddNum;
            j = i;
        }

        return isOddNum;
    }

    public static boolean isInside(Point p1, Point p2, Point tp) {
        // y축을 기준으로 내부 있는지 확인
        return (p1.y >= tp.y) != (p2.y >= tp.y);
    }

    public static boolean compareSlope(Point p1, Point p2, Point tp) {
        // 기울기를 통해 계산
        double r = (double) ((p2.x - p1.x) * (tp.y - p1.y)) / (p2.y-p1.y) + p1.x;
        return tp.x <= r;
    }
}