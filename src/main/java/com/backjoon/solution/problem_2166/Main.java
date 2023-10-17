package com.backjoon.solution.problem_2166;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<long[]> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());

            points.add(new long[]{x, y});
        }

        long area = 0;
        for (int i = 1; i < N-1; i++) {
            area += outerProduct(points.get(0), points.get(i), points.get(i+1));
        }
        area = Math.abs(area);

        System.out.printf("%.1f", (double) area/2);

    }

    static final int x = 0;
    static final int y = 1;

    static long outerProduct(long[] a, long[] b, long[] c) {
        return( a[x] * b[y] + b[x] * c[y] + c[x] * a[y]) - ( a[x] * c[y] + b[x] * a[y] + c[x] * b[y]);
    }
}