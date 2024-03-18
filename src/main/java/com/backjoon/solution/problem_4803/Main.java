package com.backjoon.solution.problem_4803;

import java.io.*;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int T = 1;
        while (true) {
            st = new StringTokenizer(input.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0)
                return;

            // (0, N]
            graph = new List[N + 1];
            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            isVisit = new boolean[N + 1];

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(input.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            int treeCnt = 0;

            for (int i = 1; i <= N; i++) {
                isTree = true;

                if (isVisit[i]) continue;

                findTree(0, i);

                if (isTree) treeCnt++;
            }

            switch (treeCnt) {
               case 0 -> System.out.println("Case " + T + ": No trees.");
               case 1 -> System.out.println("Case " + T + ": There is one tree.");
                default -> System.out.println("Case " + T + ": A forest of " + treeCnt + " trees.");
            }

            T++;
        }
    }

    static boolean isTree = true;
    public static void findTree(int grandParentIdx, int parentIdx) {
        if (!isTree)
            return;

        for (int childIdx : graph[parentIdx]) {
            if (childIdx == grandParentIdx) {
                continue;
            }

            if (isVisit[childIdx]) {
                isTree = false;
                return;
            }

            isVisit[childIdx] = true;

            findTree(parentIdx, childIdx);
        }
    }

}