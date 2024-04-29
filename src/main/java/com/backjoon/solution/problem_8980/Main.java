package com.backjoon.solution.problem_8980;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(input.readLine());

        List<Node> truck = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            truck.add(new Node(from, to, cost));
        }

        Collections.sort(truck, (o1, o2) -> {
            if (o1.to == o2.to)
                return o1.from - o2.from;

            return o1.to - o2.to;
        });

        int[] cost = new int[N+1];
        int sum = 0;

        for (Node t : truck) {
            int m = 0;
            for (int i = t.from; i < t.to; i++) {
                m = Math.max(m, cost[i]);
            }

            int g = Math.min(t.cost, limit - m);

            for (int i = t.from; i < t.to; i++)
                cost[i] += g;
            sum += g;
        }

        System.out.println(sum);
    }
}


class Node {
    int from, to, cost;

    public Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}