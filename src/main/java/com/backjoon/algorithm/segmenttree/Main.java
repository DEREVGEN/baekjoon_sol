package com.backjoon.algorithm.segmenttree;

public class Main {

    static int[] nums;
    static int[] st;
    static int n;

    public static void main(String[] args) {
        init();

        int n = nums.length;

        build(1, 0, n - 1);

        int res = query(1, 0, n-1, 0, 4);
        System.out.println(res);

        update(1, 0, n - 1, 1, 100);

        System.out.println(query(1, 0, n-1, 0, 4));
    }

    public static void init() {
        nums = new int[]{ 1, 2, 3, 4, 5 };

        n = nums.length;

        int i = 1;
        for (int e = 1; e < n; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        st = new int[l + 1];
    }

    public static void build(int idx, int l, int r) {
        if (l == r) {
            st[idx] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        build(2 * idx, l, mid);
        build(2 * idx + 1, mid+1, r);

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }

    /**
     *   param(tl, tr) : 0-4의 합,
     *   param(l,
     *   r) : tree 순회 범위,
     *   param(idx) : tree index
     */
    public static int query(int idx, int l, int r, int tl, int tr) {

        if (tr < l || r < tl)
            return 0;

        if (tl <= l && r <= tr)
            return st[idx];

        int tm = (l + r) / 2;

        return query(2 * idx, l, tm, tl, tr) + query(2 * idx + 1, tm + 1, r , tl, tr);
    }

    public static void update(int idx, int l, int r, int nIdx, int val) {
        if (l == r) {
            nums[nIdx] += val;
            st[idx] += val;

            return;
        }

        int mid = (l + r) / 2;

        if (l <= nIdx && nIdx <= mid) {
            update(2 * idx, l, mid, nIdx, val);
        } else {
            update(2 * idx + 1, mid + 1, r, nIdx, val);
        }

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }

}
