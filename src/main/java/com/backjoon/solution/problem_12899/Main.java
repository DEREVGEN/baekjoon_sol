package com.backjoon.solution.problem_12899;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int M = Integer.parseInt(input.readLine());

        N = 2_000_001;

        int b = 1;
        for (int e = 1; e < N; b++)
            e = e << 1;

        int l = (int) (Math.pow(2, b) - 1);

        tree = new int[l + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (t == 1) {
                // add
                update(1, 1, N, x);
            } else {
                // get and remove
                int r = getAndRemove(1, 1, N, x);
                sb.append(r).append('\n');
            }
        }

        System.out.println(sb);
    }

    public static void update(int idx, int l, int r, int nIdx) {
        if (l == r) {
            tree[idx]++;
            return;
        }

        int m = (l + r) / 2;

        if (l <= nIdx && nIdx <= m) {
            update(2 * idx, l, m, nIdx);
        } else {
            update(2 * idx + 1, m + 1, r, nIdx);
        }

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
    }

    public static int getAndRemove(int idx, int l, int r, int fIdx) {
        if (l == r) {
            // terminal node
            tree[idx]--;
            return l;
        }

        int m = (l + r) / 2;
        int lc = tree[2 * idx]; // left child

        int res = 0;

        if (fIdx <= lc) {
            // go to left
            res = getAndRemove(2 * idx, l, m, fIdx);
        } else {
            // go to right
            res = getAndRemove(2 * idx + 1, m + 1, r, fIdx - lc);
        }

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];

        return res;
    }
}