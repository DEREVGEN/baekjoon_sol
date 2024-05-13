package com.backjoon.solution.problem_14425;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(input.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        // trie 자료구조에 집어넣음.
        for (int i = 0; i < N; i++) {
            String inputStr = input.readLine();

            trie.insert(inputStr);
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            String searchStr = input.readLine();

            if (trie.search(searchStr)) cnt++;
        }

        System.out.println(cnt);
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
            if (i == inputStr.length() - 1)
                curNode.isEnd = true;
        }
    }

    public boolean search(String searchStr) {
        TrieNode curNode = rootNode;

        for (int i = 0; i < searchStr.length(); i++) {
            int idx = searchStr.charAt(i) - 'a';

            if (curNode.childNodes[idx] == null)
                return false;
            curNode = curNode.childNodes[idx];
            if (i == searchStr.length() - 1 && curNode.isEnd)
                return true;
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