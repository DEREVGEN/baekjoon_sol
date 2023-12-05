package com.backjoon.solution.problem_7420;

import java.util.*;
import java.io.*;

public class Main {

    /*
        1. 컨벡스 헐 수행
        2. 포인트간 모든 거리 합계 구함
        3. cc각도면, 벡터 내적을 통한 각도의 acos로 호의 길이 더하고, c각도면, -2d 해줌.
     */

    static int N, D;
    static final double PI = 3.14159265;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            points.add(new Point(y, x));
        }

        List<Point> hPoints = convexHull(points);

        double res = measureWall(hPoints);
        System.out.println(Math.round(res));
    }

    public static List<Point> convexHull(List<Point> points) {
        int minIdx = 0;

        for (int i = 1; i < points.size(); i++) {
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

        return new ArrayList<>(hStack);
    }

    public static long dist(Point p1, Point p2) {
        return (long)(p1.x - p2.x)*(p1.x - p2.x) + (long)(p1.y - p2. y)*(p1.y - p2.y);
    }

    public static int ccw(Point p1, Point p2, Point p3) {

        // res > 0: counter clock, res < 0: clock, res==0: collinear
        return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
    }

    public static double measureWall(List<Point> points) {
        // L의 길이 만큼 떨어져 있는 벽의 최소 거리를 잰다..
        int l = points.size();

        double ld = 0;

        for (int i = 0; i < points.size(); i++) {
            ld += Math.sqrt(dist(points.get(i), points.get((i+1) % l)));
        }

        if (l == 2) {
            // 한 선분 위에 있다면..
            return ld + 2 * D * PI;
        }

        for (int i = 0; i < points.size(); i++) {
            // p1 을 기준으로 시작
            Point p0 = points.get(i);
            Point p1 = points.get((i+1) % l);
            Point p2 = points.get((i+2) % l);

            int angle = ccw(p0, p1, p2);

            if (angle > 0) {
                // counter clock
                ld += arcLength(p0, p1, p2) * D;
            } else {
                // clock
                ld -= 2 * D;
            }
        }

        // temp
        return ld;
    }

    static Point v1 = new Point(0, 0);
    static Point v2 = new Point(0, 0);
    public static double arcLength(Point p0, Point p1, Point p2) {
        // v1 : p0 <- p1, v2 : p1 <- p2
        v1.y = p0.y - p1.y;
        v1.x = p0.x - p1.x;

        v2.y = p1.y - p2.y;
        v2.x = p1.x - p2.x;

        return calculateAngle(v1, v2);
    }

    private static double calculateAngle(Point vectorA, Point vectorB) {
        // 내적 계산
        double dotProduct = dotProduct(vectorA, vectorB);

        // 벡터 크기 계산
        double magnitudeA = magnitude(vectorA);
        double magnitudeB = magnitude(vectorB);

        // 아크코사인 계산
        return Math.acos(dotProduct / (magnitudeA * magnitudeB));
    }

    public static double dotProduct(Point vectorA, Point vectorB) {
        return vectorA.y * vectorB.y + vectorA.x * vectorB.x;
    }

    public static double magnitude(Point vector) {
        return Math.sqrt(Math.pow(vector.y, 2) + Math.pow(vector.x, 2));
    }

}

class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}