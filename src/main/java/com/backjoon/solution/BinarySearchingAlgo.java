package com.backjoon.solution;

import java.util.Arrays;

public class BinarySearchingAlgo {

    public static void main(String[] args){

        int[] nums = {1,3,213,3,22,3,5,6};

        Arrays.sort(nums);

        int result = binary_search(nums, 212);

        System.out.println("result: " + result + " : " + ((result != -1)?nums[result]:null));
    }

    static int binary_search(int[] nums, int key) {
        int left = 0, right = nums.length-1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == key)
                return mid;
            else if (nums[mid] > key)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

}
