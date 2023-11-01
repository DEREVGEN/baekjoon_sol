package com.backjoon.solution.problem_12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        int[] nums = new int[N];

        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int[] lis = new int[N];

        // 초기 설정, 첫번째 input의 요소 값이, lis 값으로 초기화
        // lis 값은 lowerBound의 기준이 됨.
        lis[0] = nums[0];
        int lisLength = 1;

        for (int i = 1; i < N; i++) {

            if (nums[i] > lis[lisLength - 1]) {
                lis[lisLength] = nums[i];
                lisLength++;
                continue;
            }

            int idx = lowerBound(nums[i], lisLength, lis);

           // target보다 같거나 작을 때만, lis 의 해당 자리(lower bound)에 갱신
           lis[idx] = nums[i];
        }

        for (int i = 0; i < lisLength; i++) {
            System.out.print(lis[i] + " ");
        }
        System.out.println();
        System.out.println(lisLength);
    }

    static int lowerBound(int target, int size, int[] arr) {
        int start = 0, end = size-1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (arr[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}