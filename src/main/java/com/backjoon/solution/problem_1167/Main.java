package com.backjoon.solution.problem_1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Node>[] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(input.readLine());
        // [1, N]
        graph = new List[N+1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;

                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new Node(to, cost));
                graph[to].add(new Node(from, cost));
            }
        }

        findMaxDist();
    }

    public static void findMaxDist() {
        boolean[] isVisit = new boolean[N+1];
        isVisit[1] = true;

        dfs(1, 0, isVisit);

        isVisit = new boolean[N+1];
        isVisit[maxIdx] = true;

        dfs(maxIdx, 0, isVisit);

        System.out.println(max);
    }


    static int max, maxIdx;

    public static void dfs(int idx, int sumWeight, boolean[] isVisit) {

        if (sumWeight > max) {
            max = sumWeight;
            maxIdx = idx;
        }


        for (Node nextNode : graph[idx]) {
            if (isVisit[nextNode.to]) continue;
            isVisit[nextNode.to] = true;

            dfs(nextNode.to, sumWeight + nextNode.weight, isVisit);
        }
    }
}

class Node {
    int to, weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}