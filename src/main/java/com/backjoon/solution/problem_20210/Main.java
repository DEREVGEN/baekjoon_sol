package com.backjoon.solution.problem_20210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(input.readLine());
        ArrayList<String> inputStrings = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            inputStrings.add(input.readLine());
        }

        inputStrings.sort((String o1, String o2) -> {
            int idx1 = 0;
            int idx2 = 0;

            int l1 = o1.length();
            int l2 = o2.length();
            for (; idx1 < l1 && idx2 < l2; idx1++, idx2++) {

                boolean isLetter1 = Character.isLetter(o1.charAt(idx1));
                boolean isLetter2 = Character.isLetter(o2.charAt(idx2));

                if (isLetter1 && isLetter2) { // 둘 다 문자일 때,
                    char c1 = o1.charAt(idx1);
                    char c2 = o2.charAt(idx2);

                    char lc1 = Character.toLowerCase(c1);
                    char lc2 = Character.toLowerCase(c2);

                    if (lc1 == lc2) { // 같은 문자라면,
                        if (c1 < c2) { // string 2 가 더 크다면,
                            return -1;
                        } else if (c1 > c2) { // string 1 이 더 크다면,
                            return 1;
                        }
                    } else if(lc1 < lc2) { // string 2 문자가 더 크다면,
                        return -1;
                    } else { // string 1 문자가 더 크다면,
                        return 1;
                    }
                } else if (isLetter1) { // string 1 만 문자일 때, string 2 숫자
                    return 1;
                } else if (isLetter2){ // string 2 만 문자일 때, string 1 숫자
                    return -1;
                } else { // 둘 다 숫자일 때,
                    int z1 = 0;
                    int z2 = 0;

                    while(idx1 < l1 && o1.charAt(idx1) == '0') {
                        idx1++;
                        z1++;
                    }
                    while(idx2 < l2 && o2.charAt(idx2) == '0') {
                        idx2++;
                        z2++;
                    }

                    int nIdx1 = idx1;
                    int nIdx2 = idx2;

                    int n1 = 0;
                    int n2 = 0;

                    while(nIdx1 < l1 && Character.getNumericValue(o1.charAt(nIdx1)) < 10) {
                        nIdx1++;
                        n1++;
                    }
                    while (nIdx2 < l2 && Character.getNumericValue(o2.charAt(nIdx2)) < 10) {
                        nIdx2++;
                        n2++;
                    }

                    if (n1 < n2) // string2가 숫자가 더 클 때,
                        return -1;
                    else if (n1 > n2)
                        return 1;

                    // 이후 본격적인 숫자 비교, 길이가 같다는 가정하에..
                    while(idx1 < nIdx1) { // nIdx1 만 충족해도됨, 이미 길이는 아는상태.. 같음
                        char c1 = o1.charAt(idx1);
                        char c2 = o2.charAt(idx2);
                        if (c1 > c2) // string 1의 숫자 개수가 더 클 때,
                            return 1;
                        else if (c1 < c2)
                            return -1;
                        idx1++;
                        idx2++;
                    }
                    idx1--;
                    idx2--;

                    // 숫자도 같은 상황
                    if (z1 > z2) // string 1의 0의 개수가 더 많을 때,
                        return 1;
                    else if (z1 < z2)
                        return -1;
                }
            }

            if (idx1 >= l1 && idx2 < l2) // string1의 index 범위 초과 = 끝
                return -1;
            else if (idx1 < l1)
                return 1;

            return 0;
        });

        inputStrings.forEach(System.out::println);
    }
}
