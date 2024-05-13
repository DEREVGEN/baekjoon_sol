package com.backjoon.solution.problem_19585;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        HashSet<String> nameSet = new HashSet<>();

        Trie colorTrie = new Trie();

        int cMin = Integer.MAX_VALUE;
        int cMax = Integer.MIN_VALUE;

        for (int i = 0; i < C; i++) {
            String color = input.readLine();
            colorTrie.insert(color);

            cMin = Math.min(cMin, color.length());
            cMax = Math.max(cMax, color.length());
        }

        int nMin = Integer.MAX_VALUE;
        int nMax = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            String name = input.readLine();

            nameSet.add(name);

            nMin = Math.min(nMin, name.length());
            nMax = Math.max(nMax, name.length());
        }

        int M = Integer.parseInt(input.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String name = input.readLine();
            boolean res = (name.length() > cMax + nMax) ? false : colorTrie.search(name, nMin, nameSet);

            sb.append(res ? "Yes" : "No").append('\n');
        }

        System.out.println(sb);

    }
}

class Trie {

    private TrieNode rootNode;

    public Trie() {
        this.rootNode = makeNode();
    }

    private TrieNode makeNode() {
        return new TrieNode();
    }

    public void insert(String inputStr) {
        TrieNode curNode = rootNode;

        for (int i = 0; i < inputStr.length(); i++) {
            int idx = inputStr.charAt(i) - 'a';

            if (curNode.childNodes[idx] == null) {
                curNode.childNodes[idx] = makeNode();
            }
            curNode = curNode.childNodes[idx];
        }
        curNode.isEnd = true;
    }

    public boolean search(String searchStr, int nMin, HashSet<String> nameSet) {
        TrieNode curNode = rootNode;

        int len = searchStr.length();

        for (int i = 0; i < len; i++) {

            // 가장 작은 이름에 대한 길이의 미만시,
            if (len - i < nMin) {
                return false;
            }

            int idx = searchStr.charAt(i) - 'a';

            if (curNode.childNodes[idx] == null)
                return false;
            curNode = curNode.childNodes[idx];

            // color 찾고, 뒤에 닉네임 찾고..
            if (curNode.isEnd) {
                String name = searchStr.substring(i+1);
                if (nameSet.contains(name))
                    return true;
            }
        }

        return false;
    }

    private static class TrieNode {
        TrieNode[] childNodes;
        boolean isEnd;

        public TrieNode() {
            childNodes = new TrieNode[26];
            Arrays.fill(childNodes, null);
        }
    }
}