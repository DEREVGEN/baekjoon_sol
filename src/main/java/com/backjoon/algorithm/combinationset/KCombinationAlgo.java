package com.backjoon.algorithm.combinationset;


// k combination set을 구하는 알고리즘
// 2023-06-16
// 백준 문제 17142번을 푸는 도중에,
// 바이러스의 N개 중 M개만 활성화 시키는 문제에서,
// M개에 해당하는 set만 구하는 알고리즘이다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//K는
public class KCombinationAlgo {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};

        Scanner in = new Scanner(System.in);

        System.out.print("K input: ");
        int K = in.nextInt();

        List<Integer> sets = new ArrayList<>();
        int[] subNums = new int[K];

        //nextElement(nums, 0, nums.length - K, sets, 0, K);
        nextElement2(nums, 0, 0, K, subNums);
    }

    static void nextElement(int[] nums, int start, int to, List<Integer> sets, int size, int limit) {
        if (size == limit) {
            System.out.println(sets);
            return;
        }

        for (int i = start; i <= to; i++) {
            sets.add(nums[i]);
            nextElement(nums, i+1, to+1, sets, size+1, limit);
            sets.remove(size);
        }
    }

    static void nextElement2(int[] nums, int idx, int currentIdx, int K, int[] subNums) {
        if (currentIdx == K) {
            System.out.println(Arrays.toString(subNums));
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            subNums[currentIdx] = nums[i];
            nextElement2(nums, i+1, currentIdx+1, K, subNums);
        }
    }


}
