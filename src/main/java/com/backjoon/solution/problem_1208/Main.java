package com.backjoon.solution.problem_1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N, T;
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        List<Integer> nums = new ArrayList<>();

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++)
            nums.add(Integer.parseInt(st.nextToken()));

        int midIdx = nums.size() / 2;

        Map<Integer, Integer> firstSubSumMap = new HashMap<>();
        powerSetAdding(nums.subList(0, midIdx), firstSubSumMap);
        Map<Integer, Integer> secondSubSumMap = new HashMap<>();
        powerSetAdding(nums.subList(midIdx, nums.size()), secondSubSumMap);

        /*System.out.println(firstSubSumMap);
        System.out.println(secondSubSumMap);*/

        long sum = 0;

        for (int key : firstSubSumMap.keySet()) {

            int subKey = T - key;

            if (secondSubSumMap.containsKey(subKey))
                sum += (long)firstSubSumMap.get(key) * secondSubSumMap.get(subKey);
        }
        if (T == 0)
            sum--;
        System.out.println(sum);
    }

    public static void powerSetAdding(List<Integer> nums, Map<Integer, Integer> subSumMap) {

        int size = nums.size();
        int powSize = (int)Math.pow(2, size);

        for (int i = 0; i < powSize; i++) {
            int sum = 0;
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) >= 1) {
                    sum += nums.get(j);
                }
            }
            subSumMap.merge(sum, 1, Integer::sum);
        }
    }
}