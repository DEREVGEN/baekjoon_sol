package com.backjoon.solution.problem_22870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Node>[] graph;
    static int start, goal;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i], (o1, o2) -> o1.to - o2.to);
        }

        st = new StringTokenizer(input.readLine());

        start = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());

        // 해당 정점을 방문 해도 되는가 에 대한 변수
        boolean[] canGo = new boolean[N+1];
        Arrays.fill(canGo, true);

        int[] res1 = findMinPath(goal, canGo);
        eraseEdge(canGo, res1);
        canGo[goal] = true;
        int[] res2 = findMinPath(start, canGo);
        System.out.println(res1[start] + res2[goal]);
    }

    public static int[] findMinPath(int s, boolean[] canGo) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        int[] visitNum = new int[N+1];
        Arrays.fill(visitNum, Integer.MAX_VALUE);

        pq.add(new Node(s, 0));
        visitNum[s] = 0;

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (visitNum[vertex.to] < vertex.cost) continue;

            for (Node nextV : graph[vertex.to]) {
                if (!canGo[nextV.to]) continue;

                int dist = vertex.cost + nextV.cost;

                if (visitNum[nextV.to] > dist) {
                    pq.add(new Node(nextV.to, dist));
                    visitNum[nextV.to] = dist;
                }
            }

        }

        return visitNum;
    }

    public static void eraseEdge(boolean[] canGo, int[] dist) {
        // 오름차순으로 접근해야함..

        int prevSum = 0;

        int c = start;
        while (c != goal) {
            for (Node v : graph[c]) {
                if (prevSum + v.cost + dist[v.to] == dist[start]) {
                    prevSum += v.cost;
                    canGo[v.to] = false;
                    c = v.to;

                    break;
                }
            }
        }

    }
}

class Node {
    int to, cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}