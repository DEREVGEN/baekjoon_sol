package com.backjoon.solution.problem_1007;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> xc;
    static List<Integer> yc;
    static int xSum, ySum;



    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(input.readLine());

            xSum = 0;
            ySum = 0;
            xc = new ArrayList<>();
            yc = new ArrayList<>();

            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(input.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                xc.add(x);
                yc.add(y);

                xSum += x;
                ySum += y;
            }

            min = Double.MAX_VALUE;
            comb(0, N/2, 0, 0);
            sb.append(min).append('\n');
        }

        System.out.println(sb);
    }

    static double min = Double.MAX_VALUE;

    public static void comb(int nextIdx, int depth, int xs, int ys) {
        if (depth == 0) {
            min = Math.min(min, Math.sqrt(Math.pow(2 * xs - xSum, 2) + Math.pow(2 * ys - ySum, 2)));
            return;
        }

        for (int i = nextIdx; i < N; i++) {
            comb(i+1, depth-1, xs + xc.get(i), ys + yc.get(i));
        }
    }
}