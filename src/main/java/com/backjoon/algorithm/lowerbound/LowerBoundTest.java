package com.backjoon.algorithm.lowerbound;

public class LowerBoundTest {

    public static void main(String[] args) {

        int[] nums = {1,2,2,3,3,3,7};
        int target = 2;

        int r = lowerBound(nums, nums.length, target);


        int li = lowerBound(target, nums);
        int ui = upperBound(target, nums);

        System.out.println("lower bound: " + li + " upper bound: " + ui);
    }

    static int lowerBound(int[] nums, int size, int target) {

        int left = 0, right = size-1;

        while(left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target)
                right = mid-1;
            else
                left = mid + 1;
        }

        return right;
    }

    static int lower_bound(int a[], int x, int size)
    {

        // x is the target value or key
        int l = -1, r = size;
        while (l + 1 < r)
        {
            int m = (l + r) >>> 1;
            if (a[m] >= x)
                r = m;
            else
                l = m;
        }
        return r;
    }

    static int lowerBound(int key, int[] arr) {
        int start = 0, end = arr.length-1;
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

    static int upperBound(int key, int[] arr) {

        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] > key)
                end = mid - 1;
            else
                start = mid + 1;
        }

        return end;
    }
}
