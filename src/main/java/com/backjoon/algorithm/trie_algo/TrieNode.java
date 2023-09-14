package com.backjoon.algorithm.trie_algo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class TrieNode {

    public TrieNode() {
        child = new HashMap<>();
        isEndOfWord = false;
    }

    private HashMap<Character, TrieNode> child;
    private boolean isEndOfWord;
}
