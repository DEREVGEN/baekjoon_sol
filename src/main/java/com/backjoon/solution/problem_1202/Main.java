package com.backjoon.solution.problem_1202;

import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, K;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Gem> gems = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(input.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            gems.add(new Gem(m, v));
        }

        Collections.sort(gems, (Gem o1, Gem o2) -> {
            return o1.m - o2.m;
        });

        List<Integer> bags = new ArrayList<>();
        for (int i = 1; i <= K; i++) {
            int bag = Integer.parseInt(input.readLine());

            bags.add(bag);
        }
        Collections.sort(bags);

        Queue<Integer> maxQue = new PriorityQueue<>((Integer o1, Integer o2) -> o2 - o1);

        long maxSum = 0;

        for (int i = 0, j = 0; i < K; i++) {

            while(j < N && bags.get(i) >= gems.get(j).m) {
                maxQue.offer(gems.get(j++).v);
            }

            if (!maxQue.isEmpty()) {
                maxSum += maxQue.poll();
            }
        }

        System.out.println(maxSum);
    }
}

class Gem {
    int m, v;

    public Gem(int m, int v) {
        this.m = m;
        this.v = v;
    }
}
