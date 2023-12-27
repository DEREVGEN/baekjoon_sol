package com.backjoon.solution.problem_1168;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int b = 1;
        for (int e = 1; e <= N; b++)
            e = e << 1;

        int l = (int) (Math.pow(2, b) - 1);

        tree = new int[l + 1];
        build(1, 1, N);

        StringBuilder sb = new StringBuilder();

        int startIdx = getAndRemove(1, 1, N, K);
        if (N == 1) {
            System.out.println("<" + startIdx + ">");
            return;
        }

        sb.append("<").append(startIdx).append(", ");

        for (int i = 1; i < N; i++) {
            int right = query(1, 1, N, startIdx, N);
            int total = N - i;
            int left = total - right;

            int m = K % (N - i);
            if (m == 0)
                m = N - i;

            if (right < m) {
                // 왼쪽
                startIdx = getAndRemove(1, 1, N, m - right);
            } else {
                // 오른쪽
                startIdx = getAndRemove(1, 1, N, m + left);
            }

            sb.append(startIdx);
            if (i < N-1)
                sb.append(", ");
        }
        sb.append(">");

        System.out.println(sb);
    }

    public static void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = 1;
            return;
        }

        int m = (l + r) / 2;

        build(idx * 2, l, m);
        build(idx * 2 + 1, m + 1, r);

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
    }

    public static int query(int idx, int l, int r, int tl, int tr) {
        if (tr < l || r < tl) {
            return 0;
        }

        if (tl <= l && r <= tr)
            return tree[idx];

        int m = (l + r) / 2;

        return query(2 * idx, l, m, tl, tr) + query(2 * idx + 1, m + 1, r, tl, tr);
    }

    public static int getAndRemove(int idx, int l, int r, int fIdx) {
        if (l == r) {
            // terminal node
            tree[idx] = 0;
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