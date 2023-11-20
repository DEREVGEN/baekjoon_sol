package com.backjoon.solution.problem_5719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int startIdx, goalIdx;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {

            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                return;

            st = new StringTokenizer(input.readLine());
            startIdx = Integer.parseInt(st.nextToken());
            goalIdx = Integer.parseInt(st.nextToken());

            graph = new List[N];
            for (int i = 0; i < N; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(input.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new Node(to, cost));
            }


            /*
                1. 다익스트라
                2. bfs로 해당 min 최단 경로를 갖는 간선 제거
                3. 남은 간선으로, 다익스트라 구현
             */

            // 목적지 부터 출발지 까지 vertex 기록을 위한 변수
            List<Integer>[] hist = new List[N];
            for (int i = 0; i < N; i++)
                hist[i] = new ArrayList<>();
            boolean[][] isRemoved = new boolean[N][N];

            find(startIdx, goalIdx, hist, isRemoved);
            removeEdge(hist, isRemoved);
            int res = find(startIdx, goalIdx, hist, isRemoved);
            System.out.println(res);
        }

    }

    public static int find(int startIdx, int goalIdx, List<Integer>[] hist, boolean[][] isRemoved) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        int[] visitNum = new int[N];
        Arrays.fill(visitNum, Integer.MAX_VALUE);
        visitNum[startIdx] = 0;
        pq.add(new Node(startIdx, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visitNum[node.to] < node.cost) continue;

            for (Node nextNode : graph[node.to]) {

                // 삭제된 간선 일 때,
                if (isRemoved[node.to][nextNode.to]) continue;

                int dist = node.cost + nextNode.cost;
                if (visitNum[nextNode.to] > dist) {
                    visitNum[nextNode.to] = dist;
                    pq.add(new Node(nextNode.to, dist));
                    hist[nextNode.to] = new ArrayList<>();
                    hist[nextNode.to].add(node.to);
                } else if (visitNum[nextNode.to] == dist) {
                    hist[nextNode.to].add(node.to);
                }
            }
        }

        return visitNum[goalIdx] == Integer.MAX_VALUE ? -1 : visitNum[goalIdx];
    }

    public static void removeEdge(List<Integer>[] hist, boolean[][] isRemoved) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(goalIdx);

        while (!queue.isEmpty()) {
            int vIdx = queue.poll();

            for (int prevVIdx : hist[vIdx]) {
                if (isRemoved[prevVIdx][vIdx]) continue;

                queue.add(prevVIdx);
                isRemoved[prevVIdx][vIdx] = true;
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