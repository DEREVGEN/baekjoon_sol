package com.backjoon.solution.problem_9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(input.readLine());

        map = new int[N];

        dfs(0);
        System.out.println(cnt);
    }


    static int cnt = 0;

    // idx: 현재 위치, queens: 놓아야 하는 퀸의 개수
    public static void dfs(int depth) {
        if (depth == N) {
            cnt++;
            return;
        }


        for (int i = 0; i < N; i++) {
            map[depth] = i;

            if (check(depth))
                dfs(depth+1);
        }
    }

    public static boolean check(int depth) {
        for (int i = 0; i < depth; i++) {
            if (map[depth] == map[i])
                return false;
            else if (Math.abs(depth - i) == Math.abs(map[depth] - map[i]))
                return false;
        }

        return true;
    }
}