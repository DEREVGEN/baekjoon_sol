package com.backjoon.solution.problem_3015;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());
        int[] nums = new int[N];

        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(input.readLine());

        Stack<Node> stack = new Stack<>();
        long res = 0;

        for (int i = 0; i < N; i++) {
            long cnt = 1;

            while (!stack.isEmpty() && stack.peek().val <= nums[i]) {
                Node node = stack.pop();

                // 중복이 있을 때는 cnt에 + 1을 해주어야 한다.
                if (node.val == nums[i]) {
                    res += node.cnt;
                    cnt = node.cnt + 1;
                } else {
                    res += node.cnt;
                    cnt = 1;
                }
            }

            // stack에 데이터가 있다는 것은 자신보다 키가 큰 사람이 있다는 것이다.
            if (!stack.isEmpty()) res++;
            stack.add(new Node(nums[i], cnt));
        }

        System.out.println(res);
    }
}


class Node {
    long val, cnt;

    public Node(long val, long cnt) {
        this.val = val;
        this.cnt = cnt;
    }
}