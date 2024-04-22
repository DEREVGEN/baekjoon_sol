package com.backjoon.solution.problem_6549;

import java.io.*;
import java.util.*;

public class Main {

    static Node[] tree;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(input.readLine());
            int N = Integer.parseInt(st.nextToken());

            if (N == 0) {
                return;
            }

            init(N);

            heights = new int[N];

            for (int i = 0; i < N; i++) {
                int h = Integer.parseInt(st.nextToken());

                heights[i] = h;
            }

            build(1, 0, N - 1);

            long res = findMaxArea(0, N-1);
            System.out.println(res);
        }
    }

    public static void init(int n) {
        int i = 1;
        for (int e = 1; e < n; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        tree = new Node[l + 1];
    }

    public static void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(l);
            return;
        }

        int m = (l + r) / 2;

        build(2 * idx, l, m);
        build(2 * idx + 1, m + 1, r);

        int minIdx;
        if (heights[tree[idx * 2].minIdx] <= heights[tree[idx * 2 + 1].minIdx]) {
            minIdx = tree[idx * 2].minIdx;
        } else {
            minIdx = tree[idx * 2 + 1].minIdx;
        }

        tree[idx] = new Node(minIdx);
    }

    public static int query(int idx, int l, int r, int tl, int tr) {
        if (tr < l || r < tl)
            return -1;

        if (tl <= l && r <= tr) {
            return tree[idx].minIdx;
        }

        int m = (l + r) / 2;

        int leftMinIdx = query(2 * idx, l, m, tl, tr);
        int rightMinIdx = query(2 * idx + 1, m + 1, r, tl, tr);

        if (leftMinIdx == -1) {
            return rightMinIdx;
        } else if (rightMinIdx == -1) {
            return leftMinIdx;
        } else {
            return heights[leftMinIdx] <= heights[rightMinIdx] ? leftMinIdx : rightMinIdx;
        }
    }

    public static long findMaxArea(int l, int r) {
        int N = heights.length - 1;
        int m = query(1, 0, N, l, r);

        long area = (long) (r - l + 1) * heights[m];

        if (l < m) {
            long left = findMaxArea(l, m-1);

            area = Math.max(area, left);
        }

        if (m < r) {
            long right = findMaxArea(m + 1, r);

            area = Math.max(area, right);
        }

        return area;
    }
}

class Node {
    int minIdx;

    public Node(int minIdx) {
        this.minIdx = minIdx;
    }
}