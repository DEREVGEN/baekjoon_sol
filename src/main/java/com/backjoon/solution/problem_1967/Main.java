package com.backjoon.solution.problem_1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Tree[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        tree = new Tree[N+1];
        for (int i = 0; i <= N; i++)
            tree[i] = new Tree();

        // 트리 구성
        for (int i = 1; i < N; i++) { // N-1 개 입력
            st = new StringTokenizer(input.readLine());

            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree[p].children.add(new Node(c, w));
            tree[c].children.add(new Node(p, w));
        }

        findMaxDFS();
    }

    public static void findMaxDFS() { // DFS 탐색을 통한, 최댓값 찾음

        boolean[] isVisit = new boolean[N+1];

        isVisit[1] = true;
        DFS(1, 0, isVisit);

        /*System.out.println("max: " + max);
        System.out.println("maxIdx: " + maxIdx);*/

        isVisit = new boolean[N+1];
        isVisit[maxIdx] = true;

        max = 0;
        DFS(maxIdx, 0, isVisit);

        /*System.out.println("\n-----트리 지름 구하기-----");
        System.out.println("max: " + max);
        System.out.println("maxIdx: " + maxIdx);*/

        System.out.println(max);
    }

    static int maxIdx;
    static int max = 0;

    public static void DFS(int findIdx, int sumWeight, boolean[] isVisit) {
        Tree subTree = tree[findIdx];

        if (sumWeight > max) {
            max = sumWeight;
            maxIdx = findIdx;
        }

        for (var iter = subTree.children.iterator(); iter.hasNext();) {
            Node childNode = iter.next();
            int childIdx = childNode.childIdx;

            if (isVisit[childIdx]) continue;

            isVisit[childIdx] = true;

            DFS(childIdx, sumWeight + childNode.weight, isVisit);
        }
    }
}

class Tree {
    List<Node> children;

    public Tree () {
        children = new ArrayList<>();
    }
}

class Node {
    int childIdx;
    int weight;

    public Node(int childIdx, int weight) {
        this.childIdx = childIdx;
        this.weight = weight;
    }
}
