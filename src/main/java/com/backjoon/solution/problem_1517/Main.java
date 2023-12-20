package com.backjoon.solution.problem_1517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Node[] nums;

    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        int o = 1;
        for (int e = 1; e < N; o++)
            e = e << 1;

        int l = (int) (Math.pow(2, o) - 1);

        nums = new Node[N];
        tree = new int[l+1];

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            nums[i] = new Node(value, i);
        }

        Arrays.sort(nums, (o1, o2) -> o1.value - o2.value);

        long sum = 0;

        for (int i = 0; i < N; i++) {
            Node n = nums[i];

            int r = query(1, 0, N-1, n.idx, N-1);
            update(1, 0, N-1, n.idx);

            sum += r;
        }

        System.out.println(sum);

    }

    public static int query(int idx, int l, int r, int tl, int tr) {
        if (tr < l || r < tl)
            return 0;

        if (tl <= l && r <= tr) {
            return tree[idx];
        }

        int m = (l + r) / 2;

        return query(2 * idx, l, m, tl, tr) + query(2 * idx + 1, m + 1, r, tl, tr);
    }


    public static void update(int idx, int l, int r, int nIdx) {
        if (l == r) {
            tree[idx] = 1;
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
}

class Node {
    int value, idx;

    public Node(int value, int idx) {
        this.value = value;
        this.idx = idx;
    }
}