package com.backjoon.solution.problem_1781;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
           if (o1.deadline == o2.deadline)
               return o2.ramen - o1.ramen;
           return o1.deadline - o2.deadline;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            pq.offer(new Node(d, r));
        }

        Queue<Integer> pq2 = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Node c = pq.poll();

            if (pq2.size() < c.deadline) {
                pq2.offer(c.ramen);
            } else {
                if (pq2.peek() < c.ramen) {
                    pq2.poll();
                    pq2.offer(c.ramen);
                }
            }
        }

        long res = 0;
        while (!pq2.isEmpty()) {
            res += pq2.poll();
        }

        System.out.println(res);
    }
}

class Node {

    int deadline;
    int ramen;

    public Node(int deadline, int ramen) {
        this.deadline = deadline;
        this.ramen = ramen;
    }
}