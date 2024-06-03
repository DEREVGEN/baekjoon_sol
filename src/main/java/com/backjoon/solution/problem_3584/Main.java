package com.backjoon.solution.problem_3584;

import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] level;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(input.readLine());

            parent = new int[N+1];
            level = new int[N+1];

            graph = new List[N+1];
            for (int i = 0; i <= N; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < N-1; i++) {
                st = new StringTokenizer(input.readLine());

                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[p].add(c);
                graph[c].add(p);

                parent[c] = p;
            }

            for (int i = 1; i <= N; i++) {
                if (parent[i] == 0) {
                    init(0, i);
                    break;
                }
            }

            st = new StringTokenizer(input.readLine());

            int fNum1 = Integer.parseInt(st.nextToken());
            int fNum2 = Integer.parseInt(st.nextToken());

            System.out.println(findCommonParent(fNum1, fNum2));
        }
    }

    public static void init(int p, int c) {
        level[c] = level[p] + 1;

        for (int n : graph[c]) {
            if (n == p) continue;

            init(c, n);
        }
    }

    public static int findCommonParent(int fNum1, int fNum2) {

        // level[fNum2] <= level[fNum1] 로 만들기 위함
        if (level[fNum2] > level[fNum1]) {
            int temp = fNum2;
            fNum2 = fNum1;
            fNum1 = temp;
        }

        while (level[fNum1] != level[fNum2])
            fNum1 = parent[fNum1];

        int p1 = fNum1;
        int p2 = fNum2;

        while (p1 != p2) {
            p1 = parent[p1];
            p2 = parent[p2];
        }

        return p1;
    }


}