import java.util.Scanner;

public class BST {
    Node root;

    BST(String s) {
        Scanner string = new Scanner(s);
        while (string.hasNext()){
            insert(string.nextInt());
        }
    }

    void insert(int data) {
        root = insert(root, data);
    }

    Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
            return node;
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        }
        else if (data > node.data) {
            node.right = insert(node.right, data);
        }

        return node;
    }

    int count = 3;
    public void print()
    {
        printTree(root, 0);
    }

    void printTree(Node node, int space)
    {
        if (node == null) {
            return;
        }
        space += count;
        printTree(node.right, space);
        System.out.print("\n");
        for (int i = count; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(node.data);
        printTree(node.left, space);
    }

    void printInOrder()  {
        printInOrder(root);
    }

    void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST("5 2 6 1 3 9");
        tree.print();
        System.out.print("\n\nIn order: ");
        tree.printInOrder();
    }

    class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }
}
