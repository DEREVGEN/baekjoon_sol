package com.backjoon.solution.problem_2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][] map;
    static int N;
    static boolean[][] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String row = input.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) == '1';
            }
        }

        isVisit = new boolean[N][N];


        List<Integer> nums = new ArrayList<>();
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisit[i][j] || !map[i][j]) continue;

                int res = bfs(i, j);
                nums.add(res);

                cnt++;
            }
        }

        Collections.sort(nums);

        System.out.println(cnt);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();

        isVisit[startY][startX] = true;
        queue.add(new int[]{startY, startX});

        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int movedY = node[0] + direction[d][0];
                int movedX = node[1] + direction[d][1];

                if (0 > movedY || movedY >= N || 0 > movedX || movedX >= N || isVisit[movedY][movedX] || !map[movedY][movedX]) continue;

                isVisit[movedY][movedX] = true;
                queue.add(new int[]{movedY, movedX});
                cnt++;
            }
        }

        return cnt;
    }

}