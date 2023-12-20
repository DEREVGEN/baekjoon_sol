package com.backjoon.solution.problem_9345;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Node[] tree;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(input.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            nums = new int[N];
            for (int k = 0; k < N; k++)
                nums[k] = k;

            int i = 1;
            for (int e = 1; e < N; i++)
                e = e << 1;

            int l = (int) (Math.pow(2, i) - 1);

            tree = new Node[l + 1];
            build(1, 0, N-1);

            for (int j = 0; j < M; j++){
                st = new StringTokenizer(input.readLine());

                int q = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (q == 0) {
                    // change dvd
                    update(1, 0, N-1, a, b);
                    update(1, 0, N-1, b, a);
                    swap(a, b);
                } else {
                    // take dvd
                    Node r = query(1, 0, N-1, a, b);

                    if (r.min == a && r.max == b)
                        sb.append("YES\n");
                    else
                        sb.append("NO\n");
                }
            }
        }

        System.out.println(sb);
    }

    public static void swap(int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
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

    static final Node nullNode = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);

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

    public static void update(int idx, int l, int r, int nIdx, int swapIdx) {
        if (l == r) {
            tree[idx].max = nums[swapIdx];
            tree[idx].min = nums[swapIdx];
            return;
        }

        int m = (l + r) / 2;

        if (l <= nIdx && nIdx <= m) {
            update(2 * idx, l, m, nIdx, swapIdx);
        } else {
            update(2 * idx + 1, m + 1, r, nIdx, swapIdx);
        }

        Node n1 = tree[2 * idx];
        Node n2 = tree[2 * idx + 1];

        int max = Math.max(n1.max, n2.max);
        int min = Math.min(n1.min, n2.min);

        tree[idx].max = max;
        tree[idx].min = min;
    }

}

class Node {
    int min, max;

    public Node(int min, int max) {
        this.min = min;
        this.max = max;
    }
}