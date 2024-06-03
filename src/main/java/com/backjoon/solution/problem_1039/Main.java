package com.backjoon.solution.problem_1039;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        String strNum = st.nextToken();
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(strNum);

        if (N < 10 || (N < 100 && N % 10 == 0)) {
            System.out.println(-1);
            return;
        }

        Queue<String> queue = new LinkedList<>();

        queue.offer(strNum);

        int l = strNum.length();

        int max = -1;

        int k = 0;
        while (!queue.isEmpty() && k < K) {
            Set<String> strSet = new HashSet<>();
            int ql = queue.size();

            for (int qs = 0; qs < ql; qs++) {
                String cs = queue.poll();

                for (int i = 0; i < l - 1; i++) {
                    for (int j = i + 1; j < l; j++) {
                        if (i == 0 && cs.charAt(j) == '0') continue;

                        String swapStr = swapAndGet(cs, i, j);

                        if (!strSet.contains(swapStr)) {
                            if (k == K-1 && max < Integer.parseInt(swapStr)) {
                                max = Integer.parseInt(swapStr);
                            }

                            queue.add(swapStr);
                            strSet.add(swapStr);
                        }
                    }
                }
            }
            k++;
        }

        System.out.println(max);
    }

    public static String swapAndGet(String s, int li, int ri) {
        char[] charArr = s.toCharArray();
        char temp = charArr[li];
        charArr[li] = charArr[ri];
        charArr[ri] = temp;

        return new String(charArr);
    }
}