package com.backjoon.solution.problem_2370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Node> inputNode;
    static Node[] tree;
    static int cnt = 0;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        inputNode = new ArrayList<>();

        TreeSet<Integer> ts = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            inputNode.add(new Node(f, t));
            ts.add(f); ts.add(t);
        }

        HashMap<Integer, Integer> mapper = new HashMap<>();

        int idx = 0;
        for (int n : ts) {
            mapper.put(n, ++idx);
        }

        // 아마 HashMap을 사용하여, 느리지만, 공간복잡도는 많이 줄것임..

        // 시그먼트 트리 초기화
        int b = 1;
        for (int e = 1; e < idx; b++)
            e = e << 1;

        int l = (int) (Math.pow(2, b) - 1);
        tree = new Node[l + 1];

        // 시그먼트 트리 구성
        build(1, 1, idx);

        for (int i = 0; i < N; i++) {
            Node n = inputNode.get(i);

            update(1, mapper.get(n.f), mapper.get(n.t), i+1);
        }

        isVisit = new boolean[N + 2]; // color는 1부터 시작..
        findDfs(1);
        System.out.println(cnt);
    }

    static public void build(int idx, int l, int r) {
        tree[idx] = new Node(l, r);

        if (l == r)
            return;

        int m = (l + r) >> 1;

        build(idx * 2, l, m);
        build(idx * 2 + 1, m+1, r);
    }

    static public void update(int idx, int l, int r, int color) {
        if (r < tree[idx].f || tree[idx].t < l) {
            return;
        }

        if (l <= tree[idx].f && tree[idx].t <= r) {
            tree[idx].color = color;
            return;
        }

        if (tree[idx].color >= 0) {
            tree[2 * idx].color = tree[idx].color;
            tree[2 * idx + 1].color = tree[idx].color;
            tree[idx].color = -1; // 구간 나뉘는 부분 체크
        }

        // 이후, 이분 탐색 진행
        update(2 * idx, l, r, color);
        update(2 * idx + 1, l, r, color);
    }

    static public void findDfs(int idx) {
        if (tree[idx].color == 0)
            return;

        if (tree[idx].color > 0) {
            if (!isVisit[tree[idx].color]) {
                cnt++;
                isVisit[tree[idx].color] = true;
            }
            return;
        }

        if (tree[idx].color == -1) {
            findDfs(2 * idx);
            findDfs(2 * idx + 1);
        }
    }
}

class Node {
    int f, t, color;

    public Node(int f, int t) {
        this.f = f;
        this.t = t;
    }
}
