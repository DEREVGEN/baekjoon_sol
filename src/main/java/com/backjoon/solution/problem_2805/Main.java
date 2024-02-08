package com.backjoon.solution.problem_2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(input.readLine());
        tree = new int[N];

        int hm = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            hm = Math.max(tree[i], hm);
        }
        //findHeight(0, hm);

        int hs = 0, he = hm;
        int max = 0;

        while (hs <= he) {
            int mid = (hs + he) / 2;

            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (tree[i] >= mid) {
                    sum += tree[i] - mid;
                }
            }

            if (sum >= M) {
                max = mid;
                hs = mid + 1;
            } else {
                he = mid -1;
            }

        }
        System.out.println(max);
    }
}