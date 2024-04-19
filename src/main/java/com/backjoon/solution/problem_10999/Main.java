package com.backjoon.solution.problem_10999;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static long[] nums;
    static long[] st;
    static long[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(input.readLine());
        }

        init(nums);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M + K; i++){
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                // update
                long d = Long.parseLong(st.nextToken());
                update(1, 0, nums.length-1, b-1, c-1, d);

            } else {
                // query
                long res = query(1, 0, nums.length-1, b-1, c-1);
                sb.append(res).append('\n');
            }
        }

        System.out.println(sb);
    }

    public static void init(long[] nums) {
        int i = 1;
        for (int e = 1; e < nums.length; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        st = new long[l + 1];
        lazy = new long[l + 1];

        build(1, 0, nums.length-1, nums);
    }

    public static void build(int idx, int l, int r, long[] nums) {
        if (l == r) {
            st[idx] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        build(2 * idx, l, mid, nums);
        build(2 * idx + 1, mid+1, r, nums);

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }

    public static void propagate(int idx, int l, int r) {
        if (lazy[idx] != 0) {
            st[idx] += (r - l + 1) * lazy[idx];
            if (l != r) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }

    public static long query(int idx, int l, int r, int tl, int tr) {
        propagate(idx, l, r);

        // 범위 벗어날 시,
        if (tr < l || r < tl)
            return 0;

        // 범위에 속한다면,
        if (tl <= l && r <= tr)
            return st[idx];

        int m = (l + r) / 2;

        return query(2 * idx, l, m, tl, tr) + query(2 * idx + 1, m + 1, r , tl, tr);
    }

    public static void update(int idx, int l, int r, int tl, int tr, long val) {

        propagate(idx, l, r);

        if (tr < l || r < tl)
            return;

        if (tl <= l && r <= tr) {
            st[idx] += (r - l + 1) * val;
            if (l != r) {
                lazy[idx * 2] += val;
                lazy[idx * 2 + 1] += val;
            }
            return;
        }

        int m = (l + r) / 2;
        update(2 * idx, l, m, tl, tr, val);
        update(2 * idx + 1, m+1, r, tl, tr, val);

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }

}