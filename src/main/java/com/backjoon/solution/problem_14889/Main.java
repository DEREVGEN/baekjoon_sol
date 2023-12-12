package com.backjoon.solution.problem_14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

     static int N;
     static int[][] ability;

     static boolean[] team; // false: 스타트, true: 링크

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        ability = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        team = new boolean[N];
        backTrack(0, 0);

        System.out.println(min);
    }

    static int min = Integer.MAX_VALUE;

    public static void backTrack(int idx, int depth) {

        if (depth == N/2) {

            int startSum = 0;
            int linkSum = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    if (team[i] && team[j]) {
                        startSum += ability[j][i] + ability[i][j];
                    } else if (!team[i] && !team[j]) {
                        linkSum += ability[j][i] + ability[i][j];
                    }
                }
            }

            min = Math.min(min, Math.abs(startSum - linkSum));
            return;
        }

        for (int i = idx; i <= N/2 + depth; i++) {
            team[i] = true;
            backTrack(i+1, depth + 1);
            team[i] = false;
        }
    }

}