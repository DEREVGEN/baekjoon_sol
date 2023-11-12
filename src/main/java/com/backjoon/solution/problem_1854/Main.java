package com.backjoon.solution.problem_1854;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        findKPath();
    }

    public static void findKPath() {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        Queue<Integer>[] visitDp = new Queue[N+1]; // 정점을 담는 변수

        for (int i = 1; i <= N; i++)
            visitDp[i] = new PriorityQueue<>((o1, o2) -> o2 - o1);

        visitDp[1].offer(0); // 시작점 담음
        pq.add(new Node(1, 0)); // 다익스트라 우선 순위 큐

        while(!pq.isEmpty()) {
            Node vertex = pq.poll();

            for (var nextV : graph[vertex.to]) {
                int dist = vertex.cost + nextV.cost;

                if (visitDp[nextV.to].size() < K) {
                    visitDp[nextV.to].offer(dist);
                    pq.add(new Node(nextV.to, dist));
                } else if (!visitDp[nextV.to].isEmpty() && visitDp[nextV.to].peek() > dist) {
                    visitDp[nextV.to].poll();
                    visitDp[nextV.to].offer(dist);
                    pq.add(new Node(nextV.to, dist));
                }
            }
        }


        for (int i = 1; i <= N; i++) {
            if (visitDp[i].isEmpty() || visitDp[i].size() < K) {
                System.out.println(-1);
            } else {
                System.out.println(visitDp[i].peek());
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