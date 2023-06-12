package com.backjoon.solution.problem_12100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TestCode {

    public static void main(String[] args) {

        int[] nums = {2,0,0,2,4,8};

        goLeft(nums);

        System.out.println(Arrays.toString(nums));
    }

    static void goLeft(int[] nums) {

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                continue;
            q.add(nums[i]);
            nums[i] = 0;
        }

        int idx = 0;

        while(!q.isEmpty()) {
            int n = q.poll();

            if (nums[idx] == 0)
                nums[idx] = n;
            else if (nums[idx] == n)
                nums[idx++] *= 2;
            else
                nums[++idx] = n;
        }
    }

}
