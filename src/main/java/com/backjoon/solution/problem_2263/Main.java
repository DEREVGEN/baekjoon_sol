package com.backjoon.solution.problem_2263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] inOrder;
    static int[] postOrder;
    static int[] idx;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        idx = new int[N+1];
        inOrder = new int[N+1];
        postOrder = new int[N+1];

        st = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            idx[inOrder[i]] = i;
        }

        st = new StringTokenizer(input.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            postOrder[i] = num;
        }

        recursion(1, N, 1, N);
    }

    public static void recursion(int inOrderStart, int inOrderEnd, int postOrderStart, int postOrderEnd) {

        if (inOrderStart > inOrderEnd || postOrderStart > postOrderEnd)
            return;


        int parent = idx[postOrder[postOrderEnd]];
        System.out.print(inOrder[parent] + " ");
        int left = parent - inOrderStart;
        int right = inOrderEnd - parent;


        recursion(inOrderStart, parent - 1, postOrderStart, postOrderStart + left -1);
        recursion(parent + 1, inOrderEnd, postOrderEnd - right, postOrderEnd - 1);
    }
}