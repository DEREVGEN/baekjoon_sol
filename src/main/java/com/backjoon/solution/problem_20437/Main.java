package com.backjoon.solution.problem_20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());

        for (int i = 1; i <= N; i++) {
            String inputStr = input.readLine();
            int K = Integer.parseInt(input.readLine());

            findKInString(K, inputStr);
        }
    }

    public static void findKInString(int K, String inputStr) {

        List<Integer>[] nums = new List[26];
        for (int i = 0; i < 26; i++)
            nums[i] = new ArrayList<>();

        for (int i = 0; i < inputStr.length(); i++) {
            nums[inputStr.charAt(i)-'a'].add(i);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int offSet = K-1;

        for (int i = 0; i < 26; i++) {
            if (nums[i].size() < K) continue;

            for (int j = offSet; j < nums[i].size(); j++) {
                int gap = nums[i].get(j) - nums[i].get(j-offSet) + 1;

                min = Math.min(min, gap);
                max = Math.max(max, gap);
            }
        }
        if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
            System.out.println(-1);
        }else {

            System.out.println(min + " " + max);
        }
    }
}