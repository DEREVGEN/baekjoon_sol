package com.backjoon.solution.problem_1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static Node[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        // 0 == A, 1 == B, 2 == C ...
        graph = new Node[27];

        for (int i = 0; i < 27; i++)
            graph[i] = new Node(-1, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int parentIdx = st.nextToken().charAt(0) - 'A';
            Character leftChar = st.nextToken().charAt(0);
            Character rightChar = st.nextToken().charAt(0);

            int leftIdx = 0;
            int rightIdx = 0;

            if (leftChar == '.')
                leftIdx = -1;
            else
                leftIdx = leftChar - 'A';
            if (rightChar == '.')
                rightIdx = -1;
            else
                rightIdx = rightChar - 'A';

            graph[parentIdx].left = leftIdx;
            graph[parentIdx].right = rightIdx;
        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
    }

    public static void preOrder(int parentIdx) {
        System.out.print((char) (parentIdx + 'A'));

        if (graph[parentIdx].left != -1)
            preOrder(graph[parentIdx].left);

        if (graph[parentIdx].right != -1)
            preOrder(graph[parentIdx].right);
    }

    public static void inOrder(int parentIdx) {
        if (graph[parentIdx].left != -1)
            inOrder(graph[parentIdx].left);

        System.out.print((char) (parentIdx + 'A'));

        if (graph[parentIdx].right != -1)
            inOrder(graph[parentIdx].right);
    }

    public static void postOrder(int parentIdx) {
        if (graph[parentIdx].left != -1)
            postOrder(graph[parentIdx].left);

        if (graph[parentIdx].right != -1)
            postOrder(graph[parentIdx].right);

        System.out.print((char) (parentIdx + 'A'));
    }
}

class Node {
    int left, right;

    public Node(int left, int right) {
        this.left = left;
        this.right = right;
    }
}