package com.backjoon.solution.problem_11997;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int N;
    static List<Point> points;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        List<Integer> yAscend = new ArrayList<>();
        points = new ArrayList<>();

        boolean isLine = true;
        int prevX = 0;

        TreeSet<Integer> yIndexes = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.add(new Point(x, y));
            yIndexes.add(y);

            if (i != 0) {
                if (prevX != x)
                    isLine = false;
            } else {
                prevX = x;
            }
        }

        int n = points.size();

        if (isLine) {
            int res = n / 2;
            if (res % 2 == 1)
                res++;
            System.out.println(res);
            return;
        }

        Collections.sort(yAscend);
        Collections.sort(points, (o1, o2) -> {
            return o1.x - o2.x;
        });

        int min = n;

        for (int y : yIndexes) {
            for (int m = 0; m < n-1; m++) {
                int lCnt = 0;
                int ly;
                for (ly = 0; ly <= m; ly++) {
                    if (points.get(ly).y <= y)
                        lCnt++;
                }

                int rCnt = 0;
                int ry;
                for (ry = m+1; ry < n; ry++) {
                    if (points.get(ry).y <= y)
                        rCnt++;
                }

                int cur = Math.max(lCnt, rCnt);
                cur = Math.max(cur, m+1-lCnt);
                cur = Math.max(cur, n-1-m-rCnt);

                min = Math.min(min, cur);

            }
        }
        System.out.println(min);

    }

    public static int min(int a1, int a2, int a3, int a4) {
        return Math.max(a1, Math.max(a2, Math.max(a3, a4)));
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}