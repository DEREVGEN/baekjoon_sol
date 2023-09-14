package com.backjoon.algorithm.trie_algo;

public class Trie {

    private TrieNode rootNode;

    public Trie() {
        this.rootNode = new TrieNode(); // root node 초기화
    }

    public void insert(String inputStr) {

        TrieNode currentNode = rootNode;

        for (int i = 0; i < inputStr.length(); i++) {
            currentNode.getChild().putIfAbsent(inputStr.charAt(i), new TrieNode());
            currentNode = currentNode.getChild().get(inputStr.charAt(i));
        }
        currentNode.setEndOfWord(true);
    }

    public boolean search(String searchStr) {
        TrieNode currentNode = rootNode;

        for (int i = 0; i < searchStr.length(); i++) {
            if (!currentNode.getChild().containsKey(searchStr.charAt(i)))
                return false;
            currentNode = currentNode.getChild().get(searchStr.charAt(i));
        }

        return currentNode.isEndOfWord();
    }
}
