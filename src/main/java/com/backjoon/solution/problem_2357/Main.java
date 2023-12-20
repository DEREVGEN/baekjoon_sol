package com.backjoon.solution.problem_2357;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] nums;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];

        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(input.readLine());

        int i = 1;
        for (int e = 1; e < N; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        tree = new Node[l + 1];

        build(1, 0, N-1);


        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(input.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            Node r = query(1, 0, N-1, a, b);
            System.out.println(r.min + " " + r.max);

        }

    }
    public static void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(nums[l], nums[l]);
            return;
        }

        int m = (l + r) / 2;

        build(2 * idx, l, m);
        build(2 * idx + 1, m + 1, r);

        int max = Math.max(tree[2 * idx].max, tree[2 * idx + 1].max);
        int min = Math.min(tree[2 * idx].min, tree[2 * idx + 1].min);

        tree[idx] = new Node(min, max);
    }

    static Node nullNode = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);

    public static Node query(int idx, int l, int r, int tl, int tr) {
        if (tr < l || r < tl)
            return nullNode;

        if (tl <= l && r <= tr) {
            return tree[idx];
        }

        int m = (l + r) / 2;

        Node n1 = query(2 * idx, l, m, tl, tr);
        Node n2 = query(2 * idx + 1, m + 1, r, tl, tr);

        int max = Math.max(n1.max, n2.max);
        int min = Math.min(n1.min, n2.min);

        return new Node(min, max);
    }

}

class Node {
    int min, max;

    public Node(int min, int max) {
        this.min = min;
        this.max = max;
    }
}