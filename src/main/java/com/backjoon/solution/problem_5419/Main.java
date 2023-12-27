package com.backjoon.solution.problem_5419;

import java.util.*;
import java.io.*;

public class Main {

    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(input.readLine());

            List<Node> islands = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(input.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                islands.add(new Node(x, y));
            }
            Collections.sort(islands, (o1, o2) -> {
                if (o1.x == o2.x)
                    return o2.y - o1.y;

                return o1.x - o2.x;
            });


            // y를 좌표 압축 실행.
            TreeSet<Integer> ts = new TreeSet<>();
            for (Node island : islands) {
                ts.add(island.y);
            }
            HashMap<Integer, Integer> yMapper = new HashMap<>();
            int idx = 0;
            for (Integer y : ts)
                yMapper.put(y, idx++);


            tree = new long[idx * 4];

            long res = 0;

            // islands: x를 기준으로 정렬된 상태,
            for (Node island : islands) {
                res += query(1, 0, idx-1, yMapper.get(island.y), idx-1);
                update(1, 0, idx-1, yMapper.get(island.y));
            }
            sb.append(res).append('\n');
        }
        System.out.println(sb);

    }

    public static void update(int idx, int l, int r, int tIdx) {
        if (l == r) {
            tree[idx]++;
            return;
        }

        int m = (l + r) / 2;

        if (l <= tIdx && tIdx <= m) {
            update(2 * idx, l, m, tIdx);
        } else {
            update(2 * idx + 1, m + 1, r, tIdx);
        }

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
    }

    public static long query(int idx, int l, int r, int tl, int tr) {
        if (r < tl || tr < l) {
            return 0;
        }

        if (tl <= l && r <= tr) {
            return tree[idx];
        }

        int m = (l + r) / 2;

        return query(2 * idx, l, m, tl, tr)
                + query(2 * idx + 1, m + 1, r, tl, tr);
    }


}

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}