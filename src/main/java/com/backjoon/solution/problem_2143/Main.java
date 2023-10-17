package com.backjoon.solution.problem_2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(input.readLine());

        int N1 = Integer.parseInt(input.readLine());
        int[] nums1 = new int[N1];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N1; i++) {
            nums1[i] = Integer.parseInt(st.nextToken());
        }

        int N2 = Integer.parseInt(input.readLine());
        int[] nums2 = new int[N2];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N2; i++) {
            nums2[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> subSumMap1 = sumOfElements(nums1);
        Map<Integer, Integer> subSumMap2 = sumOfElements(nums2);

        long sum = 0;

        for (int key1 : subSumMap1.keySet()) {
            int key2 = T - key1;

            if (!subSumMap2.containsKey(key2)) continue;

            sum += (long) subSumMap1.get(key1) * subSumMap2.get(key2);
        }
        System.out.println(sum);
    }

    public static Map<Integer, Integer> sumOfElements(int[] nums) {

        Map<Integer, Integer> subSumMap = new HashMap<>();

        int[] dp = new int[nums.length];

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            dp[i] = sum;
            subSumMap.merge(dp[i], 1, Integer::sum);
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                subSumMap.merge(dp[j] - dp[i-1], 1, Integer::sum);
            }
        }

        return subSumMap;
    }
}