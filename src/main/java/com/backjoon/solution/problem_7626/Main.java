package com.backjoon.solution.problem_7626;

import java.io.*;
import java.util.*;

public class Main {

    public static TreeNode[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        List<Node> coords = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st =  new StringTokenizer(input.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            coords.add(new Node(x1, x2, y1, y2));
        }

        // y좌표 압축
        TreeSet<Integer> ts = new TreeSet<>();
        for (Node coord : coords) {
            ts.add(coord.y1);
            ts.add(coord.y2);
        }

        int idx = 0;
        HashMap<Integer, Integer> yMapper = new HashMap<>();

        for (Integer y : ts)
            yMapper.put(y, idx++);


        int[] gapY = new int[idx];
        int prev = 0;
        int m = 0;

        // sortedY 의 인덱스는 1부터 시작
        for (Integer y : ts) {
            gapY[m++] =  y - prev;
            prev = y;
        }

        // x 좌표 얻고, x좌표만 정렬
        List<Xnode> xNodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Node coord = coords.get(i);

            xNodes.add(new Xnode(coord.x1, i, false));
            xNodes.add(new Xnode(coord.x2, i, true));
        }

        int i = 1;
        for (int e = 1; e < idx; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        tree = new TreeNode[l + 1];

        Collections.sort(xNodes, (o1, o2) -> o1.x - o2.x);

        build(1, 0, idx-2, gapY);

        int prevX = 0;
        long sum = 0;

        for (Xnode xNode : xNodes) {
            Node coord = coords.get(xNode.idx);

            int height = tree[1].value;
            sum += (long) (xNode.x - prevX) * height;
            prevX = xNode.x;

            if (!xNode.isEnd) {
                // 시작하는 x좌표 일 때,
                update(1, 0, idx-2, yMapper.get(coord.y1), yMapper.get(coord.y2) -1, 1);
            } else {
                // 종료하는 x좌표 일 때,
                update(1, 0, idx-2, yMapper.get(coord.y1), yMapper.get(coord.y2) -1, -1);
            }
        }

        System.out.println(sum);
    }

    public static void build(int idx, int l, int r, int[] gapY) {
        if (l == r) {
            tree[idx] = new TreeNode(gapY[l+1], 0);
            return;
        }
        int m = (l + r) / 2;
        build(2 * idx, l, m, gapY);
        build(2 * idx + 1, m + 1, r, gapY);

        tree[idx] = new TreeNode(tree[2 * idx].sum + tree[2 * idx + 1].sum, 0);
    }

    public static void update(int idx, int l, int r, int tl, int tr, int val) {
        if (tr < l || r < tl)
            return;

        if (tl <= l && r <= tr) {
            tree[idx].count += val;
            if (tree[idx].count == 0) {
                if (2 * idx >= tree.length || tree[2*idx] == null) // 진심 에바 코드;
                    tree[idx].value = 0;
                else
                    tree[idx].value = tree[2 * idx].value + tree[2 * idx + 1].value;
            } else {
                tree[idx].value = tree[idx].sum;
            }
            return;
        }

        int m = (l + r) / 2;

        update(2 * idx, l, m, tl, tr, val);
        update(2 * idx + 1, m + 1, r, tl, tr, val);

        if (tree[idx].count == 0)
            tree[idx].value = tree[2 * idx].value + tree[2 * idx + 1].value;
        else
            tree[idx].value = tree[idx].sum;
    }
}

class Node {
    int x1, x2, y1, y2;

    public Node(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}

class Xnode {
    int x, idx;
    boolean isEnd;

    public Xnode(int x, int idx, boolean isEnd) {
        this.x = x;
        this.idx = idx;
        this.isEnd = isEnd;
    }
}

class TreeNode {
    int sum, count, value;

    public TreeNode(int sum, int count) {
        this.sum = sum;
        this.count = count;
    }
}
