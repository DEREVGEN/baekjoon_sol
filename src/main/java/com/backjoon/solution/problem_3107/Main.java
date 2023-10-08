package com.backjoon.solution.problem_3107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String inputStr = input.readLine();

        if (inputStr.startsWith("::")) {
            inputStr = inputStr.replace("::", "t:");
        } else if (inputStr.endsWith("::")) {
            inputStr = inputStr.replace("::", ":t");
        } else {
            inputStr = inputStr.replace("::", ":t:");
        }

        String[] tokens = inputStr.split(":");

        //System.out.println(Arrays.asList(tokens));

        int size = 8 - tokens.length + 1;

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.equals("t")) {
                for(int s = 1; s <= size; s++) {
                    if (s == size && i == tokens.length-1)
                        System.out.println("0000");
                    else
                        System.out.print("0000:");

                }
            } else {
                for (int j = 1; j <= 4 - token.length(); j++) {
                    System.out.print("0");
                }
                System.out.print(token);
                if (i != tokens.length-1)
                    System.out.print(":");
            }
        }

    }
}
