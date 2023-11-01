package com.backjoon.solution.problem_1477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(input.readLine());

        int N, M, L;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        List<Integer> nums = new ArrayList<>();

        nums.add(0);

        st = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(nums);
        nums.add(L);

        int start = 1;
        int end = L;
        int gapMin = 0;

        while(start <= end) {
             int mid = (start + end) / 2;
             int cnt = 0;

             for (int i = 1; i < nums.size(); i++) {
                 int gap = nums.get(i) - nums.get(i-1);

                 cnt += gap / mid;
                 if (gap % mid == 0)
                     cnt--;
             }

             if (cnt > M) {
                 start = mid + 1;
             } else {
                 gapMin = mid;
                 end = mid - 1;
             }
        }

        System.out.println(gapMin);
    }
}