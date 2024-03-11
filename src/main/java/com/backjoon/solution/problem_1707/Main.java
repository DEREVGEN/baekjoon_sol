package com.backjoon.solution.problem_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer>[] graph;
    static boolean[] isChecked;
    static int[] mark;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(input.readLine());

            int V, E;
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // [1, N]
            isChecked = new boolean[V+1];
            graph = new List[V+1];
            mark = new int[V+1];
            for (int i = 1; i <= V; i++)
                graph[i] = new ArrayList<>();

            for (int i = 1; i <= E; i++) {
                st = new StringTokenizer(input.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                graph[to].add(from);
            }
            for (int i = 1; i <= V; i++) {
                if (!graph[i].isEmpty())
                    isChecked[i] = true;
            }


            boolean res = false;
            for (int i = 1; i <= V; i++) {
                if (isChecked[i]) {
                     res = bfs(i);
                    if (!res)
                        break;
                }
            }
            if (!res) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }

        }
    }

    public static boolean bfs(int startIdx) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIdx);
        isChecked[startIdx] = false;
        mark[startIdx] = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : graph[node]) {
                if (mark[node] == mark[next])
                    return false;
                if (mark[next] != 0) continue;
                mark[next] = (mark[node] == 1) ? -1 : 1;
                isChecked[next] = false;
                queue.add(next);
            }
        }

        return true;
    }

}