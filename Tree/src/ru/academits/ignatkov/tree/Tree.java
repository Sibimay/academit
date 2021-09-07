package ru.academits.ignatkov.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;

    public int getSize() {
        return size;
    }

    private int compare(T data1, T data2) {
        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    public TreeNode<T> getNode(T data) {
        TreeNode<T> node = root;

        while (node != null) {
            if (compare(data, node.getData()) == 0) {
                return node;
            }

            if (compare(data, node.getData()) < 0) {
                TreeNode<T> leftNode = node.getLeft();

                if (leftNode != null) {
                    node = leftNode;
                } else {
                    return null;
                }
            } else {
                TreeNode<T> rightNode = node.getRight();

                if (rightNode != null) {
                    node = rightNode;
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    public void insert(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            size++;

            return;
        }

        TreeNode<T> node = root;

        while (true) {
            if (compare(data, node.getData()) < 0) {
                TreeNode<T> leftNode = node.getLeft();

                if (leftNode != null) {
                    node = leftNode;
                } else {
                    node.setLeft(new TreeNode<>(data));
                    size++;

                    return;
                }
            } else {
                TreeNode<T> rightNode = node.getRight();

                if (rightNode != null) {
                    node = rightNode;
                } else {
                    node.setRight(new TreeNode<>(data));
                    size++;

                    return;
                }
            }
        }
    }

    public void remove(T data) {
        if (root == null) {
            return;
        }

        TreeNode<T> nodeToRemove = root;
        TreeNode<T> parentNode = null;
        boolean isLeft = false;

        while (true) {
            int compareResult = compare(data, nodeToRemove.getData());

            if (compareResult == 0) {
                break;
            }

            parentNode = nodeToRemove;

            if (compareResult < 0) {
                if (nodeToRemove.getLeft() != null) {
                    isLeft = true;
                    nodeToRemove = nodeToRemove.getLeft();
                } else {
                    return;
                }
            } else {
                if (nodeToRemove.getRight() != null) {
                    isLeft = false;
                    nodeToRemove = nodeToRemove.getRight();
                } else {
                    return;
                }
            }
        }

        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
            if (parentNode == null) {
                root = null;
            } else if (isLeft) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
            size--;

            return;
        }

        if (nodeToRemove.getLeft() == null || nodeToRemove.getRight() == null) {
            TreeNode<T> currentNode = nodeToRemove.getLeft() == null ? nodeToRemove.getRight() : nodeToRemove.getLeft();

            if (parentNode == null) {
                root = currentNode;
            } else if (isLeft) {
                parentNode.setLeft(currentNode);
            } else {
                parentNode.setRight(currentNode);
            }
            size--;

            return;
        }

        TreeNode<T> minNode = nodeToRemove.getRight();
        TreeNode<T> minParent = null;

        while (minNode.getLeft() != null) {
            minParent = minNode;
            minNode = minNode.getLeft();
        }

        if (minParent != null) {
            minParent.setLeft(minNode.getRight());
            minNode.setRight(nodeToRemove.getRight());
        }
        minNode.setLeft(nodeToRemove.getLeft());

        if (parentNode == null) {
            root = minNode;
        } else {
            if (isLeft) {
                parentNode.setLeft(minNode);
            } else {
                parentNode.setRight(minNode);
            }
        }

        size--;
    }

    public void widthRound() {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthRoundRecursion(TreeNode<T> currentNode) {
        if (root == null) {
            return;
        }

        if (currentNode.getLeft() != null) {
            depthRoundRecursion(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            depthRoundRecursion(currentNode.getRight());
        }
    }

    public void depthRound() {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.removeLast();

            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }
            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
            }
        }
    }

    public TreeNode<T> getRoot() {
        return root;
    }
}
