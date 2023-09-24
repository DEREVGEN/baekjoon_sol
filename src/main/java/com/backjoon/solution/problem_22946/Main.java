package com.backjoon.solution.problem_22946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Circle> circles;
    static List<Tree> trees;

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(input.readLine());
        trees = new ArrayList<>();
        circles = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(input.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            circles.add(new Circle(x, y, r));

            trees.add(new Tree());
        }

        // 반지름 기준으로 원을 내림차순으로 정렬
        circles.sort((Circle o1, Circle o2) -> {
            return o2.r - o1.r;
        });

        findMaxCircleVisit();
    }

    public static void findMaxCircleVisit() {

        // 트리 관계형성
        trees.add(new Tree()); // 루트노드 포함
        int rootNodeIdx = N;

        boolean[] isVisit = new boolean[N+1];
        isVisit[rootNodeIdx] = true;


        for (int i = 0; i < N; i++) {
            if (isVisit[i]) continue;
            isVisit[i] = true;

            // 루트노드하고, 자식노드 관계형성
            trees.get(N).children.add(i);
            trees.get(i).children.add(N);

            makeTree(i, isVisit);
        }


        isVisit = new boolean[N+1];
        isVisit[rootNodeIdx] = true;
        // DFS 를 통하여, 트리의 지름구하기
        DFS(N, 0, isVisit);

        /*System.out.println("max: " + max);
        System.out.println("maxIdx: " + maxIdx);*/

        isVisit = new boolean[N+1];
        isVisit[maxIdx] = true;
        max = 0;
        DFS(maxIdx, 0, isVisit);

        /*System.out.println("\n-----트리 지름 구하기-----");
        System.out.println("max: " + max);
        System.out.println("maxIdx: " + maxIdx);*/

        System.out.println(max);
    }

    public static void makeTree(int circleIdx, boolean[] isVisit) {

        for (int subCircleIdx = circleIdx + 1; subCircleIdx < N; subCircleIdx++) {

            if (isVisit[subCircleIdx]) continue;

            Circle currentCircle = circles.get(circleIdx);
            Circle subCircle = circles.get(subCircleIdx);

            if (isInnerCircle(currentCircle, subCircle)) { // 원의 관계 형성
                trees.get(circleIdx).children.add(subCircleIdx);
                trees.get(subCircleIdx).children.add(circleIdx);

                isVisit[subCircleIdx] = true;
                makeTree(subCircleIdx, isVisit);
            }
        }
    }


    public static boolean isInnerCircle(Circle A, Circle B) { // 원 내부인지 외부인지 검사
        int d = (int) Math.sqrt(Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2));

        if (d == 0 || d < Math.abs(A.r - B.r)) // 원의 내부 일 때,
            return true;

        return false; // 그렇지 않으면,
    }

    static int maxIdx;
    static int max = 0;

    public static void DFS(int findIdx, int sumWeight, boolean[] isVisit) {
        Tree subTree = trees.get(findIdx);

        if (sumWeight > max) {
            max = sumWeight;
            maxIdx = findIdx;
        }

        for (var iter = subTree.children.iterator(); iter.hasNext();) {
            int nextCircleIdx = iter.next();

            if (isVisit[nextCircleIdx]) continue;
            isVisit[nextCircleIdx] = true;

            DFS(nextCircleIdx, sumWeight+1, isVisit);
        }
    }
}

class Tree {
    List<Integer> children; // 자식노드의 indx만 보관

    public Tree() {
        this.children = new ArrayList<>();
    }
}


class Circle{
    int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}