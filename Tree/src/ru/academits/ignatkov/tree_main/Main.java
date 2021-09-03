package ru.academits.ignatkov.tree_main;

import ru.academits.ignatkov.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();

        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(4);

        System.out.println("getSize() = " + tree.getSize());
        tree.remove(3);
        System.out.println("getSize() = " + tree.getSize());

        tree.depthRound();
        tree.depthRoundRecursion(tree.getRoot());
        tree.widthRound();
    }
}
