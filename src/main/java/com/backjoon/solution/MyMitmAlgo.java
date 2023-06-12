package com.backjoon.solution;

import java.util.Arrays;

public class MyMitmAlgo {

    static int[] nums1 = new int[1048576];
    static int[] nums2 = new int[1048576];

    public static void main(String[] args) {
        //문제정의, 주어진 배열의 subSet중, 특정 변수 S보다 작거나 같은 SubSet의 Sum을 구하는 문제.
        // 핵심 알고리즘 - meet in the middle 알고리즘, 반으로 쪼개어 더한 값에서, S와 같거나 작은 것 중 가장 큰 값 구하기
        // 두 개의 나누어진 배열 X, Y에서 X와 Y를 각 subSum을 구한 다음, S - X[i] 에 대한 값을 Y에서 찾음, X[i] + Y[j]가 S에 작거나 같음
        // 또한 가장 큰 값을 구하는 거임 max <= S

        /* 첫번째, int 배열을 받는다, {a1,a2,a3...an}
        * 반으로 쪼개어, powerSet에 대한 sum을 구한다.
        * X와 Y로 나뉨.
        * Y를 sorting해서, 이진트리로 구성, lower bound 방식으로 동일한 값에 대한 가장 작은 index를 구함
        * [1] 이진트리에서 만족하는 값을 못찾을 경우...
        * */

        int[] nums = {1,3,2,5,6,7};

        int result = findSubSum(nums, nums.length, 19);

        System.out.println("result: " + result);
    }

    public static int findSubSum(int[] nums, int size ,int S) {

        powerSetAdding(nums, nums1, size/2, 0);
        powerSetAdding(nums, nums2, size-size/2, size/2);

        Arrays.sort(nums2, size/2, size);

        int sizeNums1 = (1 << size/2);
        int sizeNums2 = (1 << (size-size/2));

        int max = 0;

        for (int i = 0; i < sizeNums1; i++) {

            if (nums1[i] > S)
                continue;

            int y = lowerBound(S - nums1[i], nums2, sizeNums2); // nums2에 대한 배열 중 S-nums1[i] 에 대한 작은 근사값을 찾음

            if (y == sizeNums2 || nums1[i] + nums2[y] > S) {
                if (y == 0)
                    continue;
                y--;
            }

            if (max < nums1[i] + nums2[y])
                max = nums1[i] + nums2[y];

        }

        return max;
    }

    public static void powerSetAdding(int[] nums, int[] x, int size, int idx) {

        for (int i = 0; i < (1 << size); i++) {
            int sum = 0;
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) >= 1) {
                    sum += nums[idx+j];
                }
            }

            x[i] = sum;
        }
    }

    static int lowerBound(int key, int[] arr, int size) {
        int start = 0, end = size - 1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid] >= key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

}
