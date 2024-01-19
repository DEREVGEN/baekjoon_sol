package com.backjoon.solution.problem_3665;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {

            int N = Integer.parseInt(input.readLine());

            int[] nums = new int[N + 1];
            st = new StringTokenizer(input.readLine());

            for (int i = 1; i <= N; i++)
                nums[i] = Integer.parseInt(st.nextToken());

            boolean[][] graph = new boolean[N + 1][N + 1];
            int[] degree = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    graph[nums[i]][nums[j]] = true;
                    degree[nums[j]]++;
                }
            }

            int M = Integer.parseInt(input.readLine());
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(input.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (!graph[a][b]) {
                    // 순서가 a b 라는 뜻, 이때 [a][b] = false, [b][a] = true, degree[b]--, degree[a]++
                    int temp = a;
                    a = b;
                    b = temp;

                }
                graph[a][b] = false;
                graph[b][a] = true;
                degree[b]--;
                degree[a]++;
            }

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                if (degree[i] == 0)
                    queue.offer(i);
            }

            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            boolean cantFind = false;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                cnt++;

                sb.append(v).append(" ");
                for (int i = 1; i <= N; i++) {
                    if (graph[v][i]) {
                        degree[i]--;
                        if (degree[i] == 0)
                            queue.add(i);
                    }
                }
                if (queue.size() > 1) {
                    cantFind = true;
                    break;
                }
            }

            if (cantFind)
                System.out.println("?");
            else if (cnt != N)
                System.out.println("IMPOSSIBLE");
            else
                System.out.println(sb);
        }
    }
}