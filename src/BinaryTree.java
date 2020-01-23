import java.util.*;

public class BinaryTree
{
    public Node root;
    private static final int COUNT = 3;

    public BinaryTree()
    {
        root = null;
    }
    public BinaryTree(int t)
    {
        root = new Node(t);
    }

    public BinaryTree(String s)
    {
        HashSet<Integer> toRemove = new HashSet<>();

        root = new Node(1);

        Queue<Node> stk = new LinkedList<>();
        stk.add(root);

        int cnt = 2;
        while(stk.size() > 0)
        {
            Node curr = stk.remove();

            if(cnt > s.length()) break;
            if(s.charAt(cnt - 1) == '0') toRemove.add(cnt);
            curr.left = new Node(cnt);
            stk.add(curr.left);
            cnt++;


            if(cnt > s.length()) break;
            if(s.charAt(cnt - 1) == '0') toRemove.add(cnt);
            curr.right = new Node(cnt);
            stk.add(curr.right);
            cnt++;
        }

        root = remove(root, toRemove);
    }

    private Node remove(Node node, HashSet<Integer> id)
    {
        if(node == null){
            return null;
        }

        if(id.contains(node.data)) {
            return null;
        }
        node.left = remove(node.left, id);
        node.right = remove(node.right, id);

        return node;
    }

    public void addNode(int val){
        Node nNode = new Node(val);

        if (root == null) root = nNode;
        else
        {
            Node tNode = root;
            while (true)
            {
                if (tNode.data > val)
                {
                    if (tNode.left == null)
                    {
                        tNode.left = nNode;
                        break;
                    }
                    tNode = tNode.left;
                }
                else
                {
                    if (tNode.right == null)
                    {
                        tNode.right = nNode;
                        break;
                    }
                    tNode = tNode.right;
                }
            }
        }
    }

    public void print()
    {
        printTree(root, 0);
    }

    public ArrayList<Integer> getLeaves()
    {
        if(root == null)
            return new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        stack.add(root);

        ArrayList<Integer> leaves = new ArrayList<>();

        while(stack.size() > 0)
        {
            Node curr = stack.pop();

            if(curr.left != null){
                stack.add(curr.left);
            }
            if(curr.right != null){
                stack.add(curr.right);
            }

            if(curr.left == null && curr.right == null){
                leaves.add(curr.data);
            }
        }

        return leaves;
    }

    public int areaUnder(Node node)
    {
        if(node == null) {
            return 0;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(node);

        int count = 0;
        while(stack.size() > 0)
        {
            Node curr = stack.pop();

            if(curr.left != null){
                stack.add(curr.left);
            }
            if(curr.right != null){
                stack.add(curr.right);
            }
            count++;
        }

        return count;
    }

    public int getDepth(int value)
    {
        return getDepth(root, value, 1);
    }

    private int getDepth(Node node, int data, int level)
    {
        if(node == null){
            return 0;
        }
        if(node.data == data){
            return level;
        }

        int downLevel = getDepth(node.left, data, level + 1);

        if(downLevel != 0)
            return downLevel;

        return getDepth(node.right, data, level + 1);
    }


    public void printTree(Node node, int space)
    {
        if (node == null) {
            return;
        }

        space += COUNT;

        printTree(node.right, space);

        System.out.print("\n");
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(node.data);

        printTree(node.left, space);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree("1 2 3 4 6 7");
        tree.print();

    }

    public static class Node
    {
        public int data;
        public Node left;
        public Node right;

        public Node(int data)
        {
            this(data, null, null);
        }

        public Node(int data, Node left, Node right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}