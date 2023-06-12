package com.backjoon.solution.problem_13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        List<Integer> testRoom = new ArrayList<>();

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++)
            testRoom.add(Integer.valueOf(st.nextToken()));

        st = new StringTokenizer(input.readLine());

        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Long count = (long) testRoom.size();

        for (int numStudent : testRoom) {
            int subNumStudent = (numStudent - B);
            if (subNumStudent > 0) {
                count += subNumStudent/C;
                if (subNumStudent%C > 0)
                    count++;
            }
        }
        System.out.println(count);
    }
}
