package com.backjoon.solution.problem_17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        for (int i = 0; i < N; i++) {
            String inputStr = input.readLine();
            System.out.println(isPalindromeWithOneAlpha(inputStr));
        }
    }

    public static int isPalindromeWithOneAlpha(String inputStr) {

        int size = inputStr.length();

        int palCnt = 0; // 알파벳이 틀릴때 추가

        int left = 0;
        int right = size-1;

        boolean canGoLeft = false;
        boolean canGoRight = false;

        while(left < right) {

            if (inputStr.charAt(left) != inputStr.charAt(right)) {

                if (inputStr.charAt(left+1) == inputStr.charAt(right)) {
                    canGoLeft = isPalindrome(inputStr, left+1, right);
                }
                if (inputStr.charAt(left) == inputStr.charAt(right-1)) {
                    canGoRight = isPalindrome(inputStr, left, right-1);
                }

                if (!canGoLeft && !canGoRight)
                    return 2;
                else if(canGoLeft)
                    left++;
                else
                    right--;

                palCnt++;

            } else {
                left ++;
                right --;
            }

        }
        return palCnt;
    }

    public static boolean isPalindrome(String inputStr, int left, int right) {

        while (left < right) {
            if (inputStr.charAt(left) != inputStr.charAt(right)) return false;

            left++;
            right--;
        }


        return true;
    }
}
