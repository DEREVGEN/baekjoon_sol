package com.backjoon.solution.problem_11505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] nums;
    static int[] tree;
    final static int MOD = 1_000_000_007;

    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int i = 1;
        for (int e = 1; e < N; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        nums = new int[N];
        tree = new int[l + 1];

        for (int j = 0; j < N; j++) {
            nums[j] = Integer.parseInt(input.readLine());
        }

        build(1, 0, N-1);


        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < M + K; j++) {
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                // update
                update(1, 0, N-1, b-1, c);
            } else {
                // print
                long q = query(1, 0, N-1, b-1, c-1);
                sb.append(q).append('\n');
            }
        }

        System.out.println(sb);
    }

    public static void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = nums[l];
            return;
        }

        int m = (l + r) / 2;

        build(2 * idx, l, m);
        build(2 * idx + 1, m + 1, r);

        tree[idx] = (int) ((long)tree[2 * idx] * tree[2 * idx + 1] % MOD);
    }

    public static int query(int idx, int l, int r, int tl, int tr) {
        if (tr < l || r < tl)
            return 1;

        if (tl <= l && r <= tr)
            return tree[idx];

        int tm = (l + r) / 2;

        return (int) ((long)query(2 * idx, l, tm, tl, tr) * query(2 * idx + 1, tm + 1, r , tl, tr) % MOD);
    }

    public static void update(int idx, int l, int r, int nIdx, int val) {
        if (l == r) {
            nums[l] = val;
            tree[idx] = val;

            return;
        }

        int m = (l + r) / 2;

        if (l <= nIdx && nIdx <= m) {
            update(2 * idx, l, m, nIdx, val);
        } else {
            update(2 * idx + 1, m + 1, r, nIdx, val);
        }

        tree[idx] = (int) ((long)tree[2 * idx] * tree[2 * idx + 1] % MOD);
    }

}