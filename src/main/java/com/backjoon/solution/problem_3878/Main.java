package com.backjoon.solution.problem_3878;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(input.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Point> blackPoints = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(input.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                blackPoints.add(new Point(x, y));
            }
            List<Point> blackHPoints = convexHull(blackPoints);

            List<Point> whitePoints = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(input.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                whitePoints.add(new Point(x, y));
            }
            List<Point> whiteHPoints = convexHull(whitePoints);

            if (isPolygonInPoint(whiteHPoints, blackPoints)
                    || isPolygonInPoint(blackHPoints, whitePoints)
                    || checkIntersecting(whiteHPoints, blackHPoints)) {
                sb.append("NO\n");
            } else
                sb.append("YES\n");
        }
        System.out.println(sb);
    }


    public static boolean isPolygonInPoint(List<Point> polygonP, List<Point> compareP) {
        for (Point cp : compareP) {
            if (isPolygonInPoint(polygonP, cp))
                return true;
        }

        return false;
    }

    public static boolean isPolygonInPoint(List<Point> polygonP, Point testP) {

        boolean isOddNum = false;

        for (int i = 0, j = polygonP.size() - 1; i < polygonP.size(); i++) {
            Point p1 = polygonP.get(i);
            Point p2 = polygonP.get(j);

            if(isInside(p1, p2, testP) && compareSlope(p1, p2, testP))
                isOddNum = !isOddNum;
            j = i;
        }

        return isOddNum;
    }

    public static boolean compareSlope(Point p1, Point p2, Point tp) {
        // 기울기를 통해 계산
        return (tp.x <= (double)(p2.x-p1.x) * (tp.y-p1.y) / (p2.y-p1.y) + p1.x);
    }

    public static boolean isInside(Point p1, Point p2, Point tp) {
        // y축을 기준으로 내부 있는지 확인
        return (p1.y >= tp.y) != (p2.y >= tp.y);
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

        if(cal > 0) return 1;
        else if (cal == 0) return 0;
        else return -1;
    }

    public static long dist(Point p1, Point p2) {
        return (long)(p1.x - p2.x)*(p1.x - p2.x) + (long)(p1.y - p2. y)*(p1.y - p2.y);
    }

    public static boolean checkIntersecting(List<Point> wPoints, List<Point> bPoints) {
        for (int bi = 0, bj = bPoints.size() - 1; bi < bPoints.size(); bj = bi, bi++) {
            for (int wi = 0, wj = wPoints.size() - 1; wi < wPoints.size(); wj = wi, wi++) {
                if (isIntersected(bPoints.get(bj), bPoints.get(bi), wPoints.get(wj), wPoints.get(wi))) {
                    return true;
                }
            }
        }
        return false;
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