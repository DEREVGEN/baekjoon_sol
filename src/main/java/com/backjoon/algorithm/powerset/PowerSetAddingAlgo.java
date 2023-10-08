package com.backjoon.algorithm.powerset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PowerSetAddingAlgo {

    public static void main(String[] args) {
        /*일단, 배열을 생성한다, {1,3,5} 든 뭐든, 일단 int array를 생성해서, sorting한다.
        * 그래서, power set의 길이 n^2 4라면, 15까지 for문을 통한 adding 을 해야한다.
        * */

        int[] nums = {1,3,2};

        powerSetAdding(nums, nums.length);
    }

    public static void powerSetAdding(int[] nums, int size) {

        // Arrays.sort(nums);

        int powSize = (int)Math.pow(2, size);

        List<Integer> powerSetSum = new ArrayList<>();

        for (int i = 0; i < powSize; i++) {
            int sum = 0;
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) >= 1) {
                    sum += nums[j];
                }
            }

            powerSetSum.add(sum);
        }

        AtomicInteger i = new AtomicInteger(1);
        powerSetSum.forEach(x -> System.out.println(i.getAndIncrement() + ": " + x));
    }
}
