package com.backjoon.solution.problem_20166;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static char[][] map;
    static HashMap<String, Integer> cntMapper;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cntMapper = new HashMap<>();
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String rowStr = input.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = rowStr.charAt(j);
            }
        }

        String[] searchWord = new String[K];
        for (int i = 0; i < K; i++) {
            searchWord[i] = input.readLine();
            cntMapper.put(searchWord[i], 0);
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                dfs(y, x, String.valueOf(map[y][x]));
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            sb.append(cntMapper.get(searchWord[i])).append('\n');
        }

        System.out.println(sb);
    }

    static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    public static void dfs(int y, int x, String s) {

        if (cntMapper.get(s) != null) {
            cntMapper.merge(s, 1, Integer::sum);
        }

        if (s.length() == 5) {
            return;
        }

        for (int d = 0; d < 8; d++) {
            int movedY = y + direction[d][0];
            int movedX = x + direction[d][1];

            if (movedY >= N)
                movedY = 0;
            else if (movedY < 0)
                movedY = N - 1;

            if (movedX >= M)
                movedX = 0;
            else if (movedX < 0)
                movedX = M - 1;

            dfs(movedY, movedX, s+map[movedY][movedX]);
        }
    }
}