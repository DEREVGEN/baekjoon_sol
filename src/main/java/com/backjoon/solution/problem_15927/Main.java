package com.backjoon.solution.problem_15927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = input.readLine();

        System.out.println(findNoPalindrome(inputStr));
    }

    public static int findNoPalindrome(String inputStr) {

        int left = 0;
        int right = inputStr.length()-1;

        char firstAlpha = inputStr.charAt(left);
        boolean flag = false;

        while (left <= right) {
            if (inputStr.charAt(left) == inputStr.charAt(right)) {
                if (firstAlpha != inputStr.charAt(left) || firstAlpha != inputStr.charAt(right)) {
                    flag = true;
                }

                left++;
                right--;
            } else {
                return inputStr.length();
            }
        }

        if (!flag)
            return -1;

        //문자열 전체가 회문인 경우
        return inputStr.length()-1;
    }
}
