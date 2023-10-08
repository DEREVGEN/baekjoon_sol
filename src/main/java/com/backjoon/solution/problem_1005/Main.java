package com.backjoon.solution.problem_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] cost;
    static int T;
    static List<Integer>[] graph;
    static int[] time;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(input.readLine());

        for (int j = 1; j <= n; j++) {

            st = new StringTokenizer(input.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cost = new int[N+1];
            graph = new List[N+1];
            time = new int[N+1];

            Arrays.fill(time, -1);

            st = new StringTokenizer(input.readLine());

            for (int i = 1; i <= N; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(input.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[to].add(from);
            }

            T = Integer.parseInt(input.readLine());

            int res = findBuildTime(T);
            System.out.println(res);
        }
    }

    public static int findBuildTime(int idx) {

        if (time[idx] != -1)
            return time[idx];

        if (graph[idx].isEmpty())
            return cost[idx];

        int max = Integer.MIN_VALUE;

        for (var iter = graph[idx].iterator(); iter.hasNext(); ) {
            int nextIdx = iter.next();

            max = Math.max(max, findBuildTime(nextIdx));
        }
        time[idx] = cost[idx] + max;

        return time[idx];
    }

}
