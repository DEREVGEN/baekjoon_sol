package com.backjoon.solution.problem_1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        Queue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(input.readLine());

            if (num == 0) {
                if (pq.isEmpty())
                    sb.append('0');
                else
                    sb.append(pq.poll());
                sb.append('\n');
            } else {
                pq.offer(num);
            }
        }

        System.out.println(sb);
    }
}