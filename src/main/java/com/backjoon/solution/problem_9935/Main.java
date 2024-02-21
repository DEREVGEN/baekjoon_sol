package com.backjoon.solution.problem_9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Stack<Node> stack = new Stack<>();

        String str = input.readLine();
        String sep = input.readLine();

        StringBuilder sb = new StringBuilder();

        if (sep.length() == 1) {
            // 폭발 문자열의 길이가 1이라면,
            char sepChar = sep.charAt(0);
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != sepChar)
                    sb.append(str.charAt(i));
            }
            System.out.println(sb.isEmpty() ? "FRULA" : sb);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (stack.isEmpty()) {
                if (sep.charAt(0) == c) {
                    stack.push(new Node(i, 0));
                } else {
                    stack.push(new Node(i, -1));
                }
            } else {
                Node node = stack.peek();

                if (sep.charAt(node.sepIdx + 1) == c) {
                    // 만약 폭발문자열 끝에 다다르면
                    if (node.sepIdx + 1 == sep.length() - 1) {
                        for (int j = 0; j < sep.length() - 1; j++) {
                            stack.pop();
                        }
                    } else {
                        stack.push(new Node(i, node.sepIdx + 1));
                    }
                } else {
                    if (sep.charAt(0) == c) {
                        stack.push(new Node(i, 0));
                    } else {
                        stack.push(new Node(i, -1));
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
            return;
        }

        char[] ans = new char[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[idx--] = str.charAt(stack.pop().strIdx);
        }

        for (char an : ans)
            sb.append(an);
        System.out.println(sb);
    }
}

class Node {
    int strIdx, sepIdx;

    public Node(int strIdx, int sepIdx) {
        this.strIdx = strIdx;
        this.sepIdx = sepIdx;
    }
}