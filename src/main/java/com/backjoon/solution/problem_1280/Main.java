package com.backjoon.solution.problem_1280;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());


        // 일단, 좌표 압축해서, 그 인덱스로, 펜윅트리 구성
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(input.readLine());
        }

        //List<Integer> treeSortedSet = new ArrayList<>(Arrays.stream(nums).boxed().collect(Collectors.toSet()).stream().toList());

        Set<Integer> treeSet = new HashSet<>();
        for (int num : nums) {
            treeSet.add(num);
        }

        List<Integer> treeSortedSet = new ArrayList<>(treeSet);
        Collections.sort(treeSortedSet);


        Collections.sort(treeSortedSet);

        int M = treeSortedSet.size();

        long sum = 1;
        final int MODULO_VALUE = 1_000_000_007;

        // 합계를 위한 펜윅트리
        FenwickTree sumFt = new FenwickTree(M);
        // 개수를 위한 펜윅트리
        FenwickTree cntFt = new FenwickTree(M);

        // 한가지 값만 있다면 종료,
        if (treeSortedSet.size() == 1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            int setIdx = Collections.binarySearch(treeSortedSet, nums[i]);

            if (i > 0) {
                // index는 1부터 시작하며, 자기자신 이상을 검색하려면 +2
                long rightSum = sumFt.sum(setIdx + 1, M);
                long rightCnt = cntFt.sum(setIdx + 1, M);
                long leftSum = sumFt.sum(1, setIdx);
                long leftCnt = cntFt.sum(1, setIdx);

                sum *= ((rightSum - nums[i] * rightCnt) + (nums[i] * leftCnt - leftSum)) % MODULO_VALUE;
                sum %= MODULO_VALUE;
            }


            // 합계 갱신
            sumFt.add(setIdx+1, nums[i]);
            // 개수 갱신
            cntFt.add(setIdx+1, 1);
        }

        System.out.println(sum);
    }
}

class FenwickTree {
    long[] tree;

    public FenwickTree(int N) {
        // 인덱스는 1부터 시작
        tree = new long[N+1];
    }

    public void add(int idx, long val) {

        while (idx < tree.length) {
            tree[idx] += val;
            idx += (idx & -idx);
        }
    }

    public long sum(int l, int r) {
        return findLeftSum(r) - findLeftSum(l-1);
    }

    private long findLeftSum(int idx) {
        long sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= (idx & -idx);
        }
        return sum;
    }
}