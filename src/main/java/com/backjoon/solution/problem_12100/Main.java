package com.backjoon.solution.problem_12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int max = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int[][] nums = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            for (int j = 0; j < N; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(nums, N, 1);

        System.out.println(max);
    }

    static void dfs(int[][] nums, int N, int height) {
        if (height > 5) {
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (nums[i][j] > max)
                        max = nums[i][j];
            return;
        }

        for (int f = 0; f < 4; f++) {
            int[][] dfsNums = new int[N][N];
            for (int i = 0; i < N; i++)
                dfsNums[i] = nums[i].clone();

            switch (f) {
                case 0:
                    goRight(dfsNums, N);
                    dfs(dfsNums, N, height+1);
                    break;
                case 1:
                    goDown(dfsNums, N);
                    dfs(dfsNums, N, height+1);
                    break;
                case 2:
                    goLeft(dfsNums, N);
                    dfs(dfsNums, N, height+1);
                    break;
                case 3:
                    goUp(dfsNums, N);
                    dfs(dfsNums, N, height+1);
                    break;
            }
        }
    }


    static void goLeft(int[][] nums, int N) {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i][j] == 0)
                    continue;
                q.add(nums[i][j]);
                nums[i][j] = 0;
            }

            int idx = 0;
            while (!q.isEmpty()) {
                int num = q.poll();

                if (nums[i][idx] == 0)
                    nums[i][idx] = num;
                else if (nums[i][idx] == num)
                    nums[i][idx++] *= 2;
                else
                    nums[i][++idx] = num;
            }
        }
    }

    static void goRight(int[][] nums, int N) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = nums.length-1; j >= 0; j--) {
                if (nums[i][j] == 0)
                    continue;
                q.add(nums[i][j]);
                nums[i][j] = 0;
            }

            int idx = nums.length-1;

            while (!q.isEmpty()) {
                int num = q.poll();

                if (nums[i][idx] == 0)
                    nums[i][idx] = num;
                else if (nums[i][idx] == num)
                    nums[i][idx--] *= 2;
                else
                    nums[i][--idx] = num;
            }
        }
    }

    static void goUp(int[][] nums, int N) {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j][i] == 0)
                    continue;
                q.add(nums[j][i]);
                nums[j][i] = 0;
            }

            int idx = 0;
            while (!q.isEmpty()) {
                int num = q.poll();

                if (nums[idx][i] == 0)
                    nums[idx][i] = num;
                else if (nums[idx][i] == num)
                    nums[idx++][i] *= 2;
                else
                    nums[++idx][i] = num;
            }
        }
    }

    static void goDown(int[][] nums, int N) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = nums.length-1; j >= 0; j--) {
                if (nums[j][i] == 0)
                    continue;
                q.add(nums[j][i]);
                nums[j][i] = 0;
            }

            int idx = nums.length-1;

            while (!q.isEmpty()) {
                int num = q.poll();

                if (nums[idx][i] == 0)
                    nums[idx][i] = num;
                else if (nums[idx][i] == num)
                    nums[idx--][i] *= 2;
                else
                    nums[--idx][i] = num;
            }
        }
    }
}