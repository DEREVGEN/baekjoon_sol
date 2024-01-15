package com.backjoon.solution.problem_1948;

import java.io.*;
import java.util.*;

public class Main {

    static List<Node>[] graph;
    static List<Node>[] reverseGraph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M;

        N = Integer.parseInt(input.readLine());
        M = Integer.parseInt(input.readLine());

        graph = new List[N+1];
        reverseGraph = new List[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
            reverseGraph[to].add(new Node(from ,weight));
        }

        st = new StringTokenizer(input.readLine());
        int startIdx = Integer.parseInt(st.nextToken());
        int lastIdx = Integer.parseInt(st.nextToken());

        dp = new int[N+1];

        Queue<Integer> q = new LinkedList<>();
        q.add(startIdx);

        while (!q.isEmpty()) {
            int nodeIdx = q.poll();

            for (Node nextNode : graph[nodeIdx]) {
                if (dp[nodeIdx] + nextNode.weight > dp[nextNode.to]) {
                    dp[nextNode.to] = dp[nodeIdx] + nextNode.weight;
                    q.offer(nextNode.to);
                }
            }
        }
        System.out.println(dp[lastIdx]);

        boolean[] isVisit = new boolean[N+1];
        int edgeCnt = 0;
        Queue<Node> fq = new LinkedList<>();
        fq.add(new Node(lastIdx, dp[lastIdx]));

        while (!fq.isEmpty()) {
            Node node = fq.poll();

            for (Node nextNode : reverseGraph[node.to]) {
                if (node.weight - nextNode.weight == dp[nextNode.to]) {
                    if (!isVisit[nextNode.to]) {
                        isVisit[nextNode.to] = true;
                        fq.add(new Node(nextNode.to, dp[nextNode.to]));
                    }
                    edgeCnt++;
                }
            }
        }

        //System.out.println(Arrays.toString(dp));
        System.out.println(edgeCnt);
    }
}

class Node {
    int to, weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}