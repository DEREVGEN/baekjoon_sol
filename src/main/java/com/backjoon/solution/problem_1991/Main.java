package com.backjoon.solution.problem_1991;

import javax.swing.plaf.PanelUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Tree<Integer> binaryTree = new Tree<>();

        binaryTree.add('A'- 0);
    }
}

class Tree<T extends Number & Comparable<? super T>> {
    Node<T> head;

    public Tree() {
        head = null;
    }

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value);
            return;
        }

        Node<T> finder = head;
        while (true) {
            if (value.compareTo(finder.getValue()) < 1) {
                if (finder.getLeft() == null) {
                    finder.setLeft(new Node<>(value));
                    break;
                }
                finder = finder.getLeft();
            } else {
                if (finder.getRight() == null) {
                    finder.setRight(new Node<>(value));
                    break;
                }
                finder = finder.getRight();
            }
        }
    }

    public void printPreOrder() {
        if (head == null)
            return;
        preOrderDFS(head);
    }

    public void preOrderDFS(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue());

        preOrderDFS(node.getLeft());
        preOrderDFS(node.getRight());
    }

    public void printInOrder() {
        if (head == null)
            return;
        inOrderDFS(head);
    }

    public void inOrderDFS(Node<T> node) {
        if (node == null)
            return;

        inOrderDFS(node.getLeft());
        System.out.print(node.getValue());
        inOrderDFS(node.getRight());
    }

    public void printPostOrder() {
        if (head == null)
            return;

        postOrderDFS(head);
    }

    public void postOrderDFS(Node<T> node) {
        if (node == null)
            return;

        postOrderDFS(node.getLeft());
        postOrderDFS(node.getRight());
        System.out.print(node.getValue());
    }
}

class Node<T extends Number> {
    private T value;

    private Node<T> left, right;

    public Node(T value) {
        this.value = value;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }
}