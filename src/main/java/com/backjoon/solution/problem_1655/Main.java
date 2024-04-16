package com.backjoon.solution.problem_1655;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        /*
            중위값을 유지하면서,
            left (최대힙)
            right (최소힙 유지)
         */

        StringBuilder sb = new StringBuilder();

        Queue<Integer> leftPq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Queue<Integer> rightPq = new PriorityQueue<>((o1, o2) -> o1 - o2);

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                leftPq.offer(nums[i]);
            } else if (i == 1) {
                if (leftPq.peek() < nums[i]) {
                    rightPq.offer(nums[i]);
                } else {
                    rightPq.offer(leftPq.poll());
                    leftPq.offer(nums[i]);
                }
            } else {
                leftPq.offer(nums[i]);

                if (leftPq.peek() > rightPq.peek()) {
                    rightPq.offer(leftPq.poll());

                    if (rightPq.size() > leftPq.size()) {
                        leftPq.offer(rightPq.poll());
                    }
                }

                if (rightPq.size() + 1 < leftPq.size()) {
                    rightPq.offer(leftPq.poll());
                }
            }

            sb.append(leftPq.peek()).append('\n');
        }
        System.out.println(sb);
    }
}