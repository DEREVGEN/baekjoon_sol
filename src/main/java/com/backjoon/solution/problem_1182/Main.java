package com.backjoon.solution.problem_1182;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[1 << N];
        //bit mask
        int cnt = 0;

        for (int i = 1; i < (1 << N); i++) {
            for (int j = 0; j < N; j++) {
                if ((i&(1 << j)) > 0) {
                    dp[i] = dp[(i^(1<<j))] + nums[j];
                }
            }
        }

        for (int i = 1; i < (1 << N); i++) {
            if (dp[i] == S)
                cnt++;
        }

        System.out.println(cnt);


    }
}
