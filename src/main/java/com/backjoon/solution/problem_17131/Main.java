package com.backjoon.solution.problem_17131;

import java.io.*;
import java.util.*;

public class Main {

    static int[] tree;
    static int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        List<Node> stars = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new Node(x, y));
        }

        Collections.sort(stars,(o1, o2) -> {
            if (o1.y == o2.y)
                return o1.x - o2.x;
            return o2.y - o1.y;
        });

        TreeSet<Integer> ts = new TreeSet<>();
        for (Node star : stars) {
            ts.add(star.x);
        }

        HashMap<Integer, Integer> xMapper = new HashMap<>();

        // -----시그먼트 트리의 시작은 1부터 시작-----
        int idx = 0;
        for (Integer x : ts) {
            xMapper.put(x, idx++);
        }

        int size = idx;

        int i = 1;
        for (int e = 1; e < size; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        tree = new int[l + 1];

        long sum = 0;

        int prevY = Integer.MIN_VALUE;
        int prexX = Integer.MIN_VALUE;
        int prevCnt = 0;
        int duplicCnt = 0;

        for (Node star : stars) {
            int compX = xMapper.get(star.x);

            if (prevY != star.y) {
                prevY = star.y;
                prexX = compX;
                prevCnt = 0;
                duplicCnt = 0;
            } else {
                if (prexX != compX) {
                    prevCnt += duplicCnt + 1;
                    prexX = compX;
                    duplicCnt = 0;
                } else {
                    duplicCnt++;
                }
            }

            // 끄트머리는 검사 안함
            if (0 < compX && compX < size-1) {
                long left = query(1, 0, size-1, 0, compX-1) % MOD;
                long right = query(1, 0, size-1, compX+1, size-1) % MOD;

                sum += (left - prevCnt) * right % MOD;
                sum %= MOD;
            }

            update(1, 0, size-1, compX);
        }

        System.out.println(sum);

    }

    public static void update(int idx, int l, int r, int nIdx) {
        if (l == r) {
            tree[idx]++;
            return;
        }

        int m = (l + r) / 2;
        if (l <= nIdx && nIdx <= m)
            update(2 * idx, l, m, nIdx);
        else
            update(2 * idx + 1, m + 1, r, nIdx);

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
    }

    public static long query(int idx, int l, int r, int tl, int tr) {
        if(tr < l || r < tl)
            return 0;

        if (tl <= l && r <= tr)
            return tree[idx];

        int m = (l + r) / 2;

        return query(2 * idx, l, m, tl, tr) + query(2 * idx + 1, m + 1, r, tl, tr);
    }

}

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}