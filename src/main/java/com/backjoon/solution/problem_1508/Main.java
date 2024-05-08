package com.backjoon.solution.problem_1508;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());
        int N, M, K;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] nums = new int[K];
        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < K; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = N;

        int max = 0;

        // max 값 찾고 난 이후에, 0 1 체크
        while (start <= end) {
            int mid = (start + end) / 2;

            int cnt = 1;

            int prev = nums[0];

            for (int i = 1; i < K; i++) {
                if (nums[i] - prev >= mid) {
                    prev = nums[i];
                    cnt++;
                }
            }

            if (cnt >= M) {
                max = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(1);

        int cnt = 1;

        int prev = nums[0];
        for (int i = 1; i < K; i++) {
            if (nums[i] - prev >= max && cnt < M) {
                sb.append(1);
                prev = nums[i];
                cnt++;
            } else {
                sb.append(0);
            }
        }

        System.out.println(sb);
    }
}