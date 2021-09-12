package ru.academits.ignatkov.tree_main;

import ru.academits.ignatkov.tree.Tree;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        Consumer<Integer> print = node -> System.out.print(node + " ");

        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(4);

        System.out.println("getSize() = " + tree.getSize());
        tree.remove(3);
        System.out.println("getSize() = " + tree.getSize());

        tree.traverseDepth(print);
        tree.traverseDepthRecursion(tree.getRoot(), print);
        tree.traverseWidth(print);
    }
}
