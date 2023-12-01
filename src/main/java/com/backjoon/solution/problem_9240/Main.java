package com.backjoon.solution.problem_9240;

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

        int N = Integer.parseInt(input.readLine());

        List<Point> inputPoints = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            inputPoints.add(new Point(y, x));
        }

        List<Point> hPoints = convexHull(inputPoints);
        double res = maxDist(hPoints);
        System.out.println(res);
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

        return new ArrayList<>(hStack);
    }

    public static double maxDist(List<Point> points) {
        // 회전하는 캘리퍼스 적용
        int j = 1;
        int N = points.size();
        long maxDist = Long.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            long d = dist(points.get(i), points.get(j % N));
            maxDist = Math.max(d , maxDist);

            while ((j + 1) % N != i &&
                    ccw(points.get(i), points.get((i+1) % N), points.get(j % N), points.get((j+1) % N)) >= 0 ) {
                j++;
                maxDist = Math.max(d , maxDist);
            }
        }

        return Math.sqrt(maxDist);
    }

    public static long dist(Point p1, Point p2) {
        return (long)(p1.x - p2.x)*(p1.x - p2.x) + (long)(p1.y - p2. y)*(p1.y - p2.y);
    }

    public static int ccw(Point p1, Point p2, Point p3) {

        // res > 0: counter clock, res < 0: clock, res==0: collinear
        return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
    }

    static Point tempPoint = new Point(0, 0);

    public static int ccw(Point p1, Point p2, Point p3, Point p4) {
        // p1 -> p2 벡터에, p3 -> p4 벡터 연결 이후 ccw 판별
        tempPoint.y = p4.y - p3.y + p2.y;
        tempPoint.x = p4.x - p3.x + p2.x;

        // res > 0: counter clock, res < 0: clock, res==0: collinear
        return ccw(p1, p2, tempPoint);
    }

}

class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}