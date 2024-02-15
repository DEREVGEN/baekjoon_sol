package com.backjoon.solution.problem_11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            int an1 = Math.abs(o1.value);
            int an2 = Math.abs(o2.value);

            if (an1 == an2) {
                return o1.value - o2.value;
            }

            return an1 - an2;
        });
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(input.readLine());

            if (num == 0) {
                if (pq.isEmpty()) {
                    sb.append('0');
                } else {
                    Node node = pq.poll();
                    sb.append(node.value);
                }
                sb.append('\n');
            } else {
                pq.offer(new Node(num, i));
            }
        }

        System.out.println(sb);
    }
}

class Node {
    int value, idx;

    public Node(int value, int idx) {
        this.value = value;
        this.idx = idx;
    }
}