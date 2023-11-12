package com.backjoon.solution.problem_20183;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, startV, destV;
    static Long C;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startV = Integer.parseInt(st.nextToken());
        destV = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        graph = new List[N+1];

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        long sum = 0;

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));

            sum += cost;
        }

        long start = 1;
        long end = sum; // 총 거리 합
        long minCost = Long.MAX_VALUE;

        while (start <= end) {
            long mid = (start + end) / 2; // 임계값 base 변수

            Long res = findLowCostDest(mid);

            if (res == Long.MAX_VALUE) {
                start = mid + 1;
            } else {
                minCost = mid;
                end = mid - 1;
            }
        }

        System.out.println(minCost == Long.MAX_VALUE ? -1 : minCost);
    }


    public static long findLowCostDest(long d) {
        Queue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(startV, 0));
        long[] visitNum = new long[N+1];
        Arrays.fill(visitNum, Long.MAX_VALUE);

        while (!pq.isEmpty()) {
            Node vertex = pq.poll();

            if (vertex.to == destV)
                return vertex.cost;

            if (visitNum[vertex.to] < vertex.cost) continue;

            for (Node nextV : graph[vertex.to]) {
                long dist = vertex.cost  + nextV.cost;

                // 임계값 설정 및 최단거리 검사
                if (nextV.cost <= d && dist <= C && visitNum[nextV.to] > dist) {
                    visitNum[nextV.to] = dist;
                    pq.add(new Node(nextV.to, dist));
                }
            }
        }

        return visitNum[destV]; // 갱신이 안되었으면, Long.MAX_VALUE 값,
    }
}

class Node implements Comparable<Node>{
    int to;
    long cost;

    public Node(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }
}