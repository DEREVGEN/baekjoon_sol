package com.backjoon.algorithm.trie_algo;

public class Main {

    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("wow");
        System.out.println(trie.search("wow"));
        trie.insert("woow");
        System.out.println(trie.search("woow"));

        System.out.println(trie.search("wow"));
        System.out.println(trie.search("wow"));
        System.out.println(trie.search("woow"));
    }
}