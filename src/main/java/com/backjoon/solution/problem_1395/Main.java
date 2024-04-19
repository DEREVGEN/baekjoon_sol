package com.backjoon.solution.problem_1395;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] st;
    static boolean[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init(N);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 0) {
                update(1, 0, N-1, b-1, c-1);
            } else {
                int res = query(1, 0, N-1, b-1, c-1);
                sb.append(res).append('\n');
            }
        }

        System.out.println(sb);

    }

    public static void init(int size) {
        int i = 1;
        for (int e = 1; e < size; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        st = new int[l + 1];
        lazy = new boolean[l + 1];
    }

    public static void propagate(int idx, int l, int r) {
        if (lazy[idx]) {
            st[idx] = (r - l + 1) - st[idx];
            if (l != r) {
                lazy[idx * 2] = !lazy[idx * 2];
                lazy[idx * 2 + 1] = !lazy[idx * 2 + 1];
            }
            lazy[idx] = false;
        }
    }

    public static int query(int idx, int l, int r, int tl, int tr) {
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

    public static void update(int idx, int l, int r, int tl, int tr) {

        propagate(idx, l, r);

        if (tr < l || r < tl)
            return;

        if (tl <= l && r <= tr) {
            st[idx] = (r - l + 1) - st[idx];
            if (l != r) {
                lazy[idx * 2] = !lazy[idx * 2];
                lazy[idx * 2 + 1] = !lazy[idx * 2 + 1];
            }
            return;
        }

        int m = (l + r) / 2;
        update(2 * idx, l, m, tl, tr);
        update(2 * idx + 1, m+1, r, tl, tr);

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }


}