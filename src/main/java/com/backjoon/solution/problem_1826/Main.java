package com.backjoon.solution.problem_1826;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        // 연료 최대 힙 큐
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        List<int[]> nodeList = new ArrayList<>();

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(input.readLine());

            int to = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            nodeList.add(new int[]{to, fuel});
        }

        Collections.sort(nodeList, (o1, o2) -> o1[0] - o2[0]);

        st = new StringTokenizer(input.readLine());

        int cnt  = -1;

        int goal = Integer.parseInt(st.nextToken());
        int initFuel = Integer.parseInt(st.nextToken());

        nodeList.add(new int[]{goal, 0});

        int from = 0;

        pq.offer(new int[]{0, initFuel});
        int sumFuel = 0;

        for (int[] node : nodeList) {
            int to = node[0];
            int fuel = node[1];

            while (sumFuel < (to - from) && !pq.isEmpty()) {
                int[] prev = pq.poll();
                cnt++;
                sumFuel += prev[1];
            }

            if (pq.isEmpty() && sumFuel < (to - from)) {
                System.out.println(-1);
                return;
            }

            pq.offer(new int[]{to, fuel});

            sumFuel -= (to - from);
            from = to;
        }

        System.out.println(cnt);

    }
}