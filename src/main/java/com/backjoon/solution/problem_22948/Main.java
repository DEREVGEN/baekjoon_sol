package com.backjoon.solution.problem_22948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Circle> circles;
    static int N;
    static int A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());

        circles = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int cNum = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            circles.add(new Circle(cNum, x, r));
        }

        st = new StringTokenizer(input.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());


        circles.add(new Circle(0, 0, 20000000)); // 루트노드 입력

        circles.sort((Circle o1, Circle o2) -> { // 반지름 기준으로 내림차순 정렬
            return o2.r - o1.r;
        });

        List<Integer> aTracer = new ArrayList<>();
        List<Integer> bTracer = new ArrayList<>();

        for (int currentIdx = 0; currentIdx <= N; currentIdx++) {

            // 내림차순 정렬되어 있어서, A와 B에 대한 circle의 num에 대한 객체의 index를 찾아야함
            int cNum = circles.get(currentIdx).cNum;
            if (cNum == A || cNum == B) {
                int mIdx = currentIdx;

                if (cNum == A) aTracer.add(A);
                else bTracer.add(B);

                while(mIdx > 0) {
                    if (isInnerCircle(circles.get(mIdx-1), circles.get(currentIdx))) {
                        if (cNum == A) aTracer.add(circles.get(mIdx-1).cNum);
                        else bTracer.add(circles.get(mIdx-1).cNum);
                    }
                    mIdx--;
                }
            }
        }

        // 끝자락부터 시작
        int aIdx = aTracer.size() - 1;
        int bIdx = bTracer.size() - 1;

        while (aIdx >= 0 && bIdx >= 0 && aTracer.get(aIdx) == bTracer.get(bIdx)) {
            aIdx--;
            bIdx--;
        }

        aIdx++;

        int size = aIdx + bIdx + 2;
        System.out.println(size);

        for (int i = 0; i <= aIdx; i++) {
            System.out.print(aTracer.get(i) + " ");
        }
        for (int i = bIdx; i >= 0; i--) {
            System.out.print(bTracer.get(i) + " ");
        }
    }

    public static boolean isInnerCircle(Circle A, Circle B) { // 원 내부인지 외부인지 검사

/*        if (A.cNum == 0) // A circle이 root이며, A >= B 관계를 가짐
            return true;*/

        int d = Math.abs(A.x - B.x); // 좌표간 거리

        if (d < Math.abs(A.r - B.r) || d == 0) // 원의 내부
            return true;

        return false; // 외부
    }


}

class Circle {
    int cNum, x, r;

    public Circle(int cNum, int x, int r) {
        this.cNum = cNum;
        this.x = x;
        this.r = r;
    }
}
