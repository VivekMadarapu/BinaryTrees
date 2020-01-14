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

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int count = 2;
        while(queue.size() > 0)
        {
            Node curr = queue.remove();

            if(count > s.length()){
                break;
            }
            if(s.charAt(count - 1) == '0'){
                toRemove.add(count);
            }
            curr.left = new Node(count);
            queue.add(curr.left);
            count++;

            if(count > s.length()){
                break;
            }
            if(s.charAt(count - 1) == '0'){
                toRemove.add(count);
            }
            curr.right = new Node(count);
            queue.add(curr.right);
            count++;
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

    public void addNode(int val)
    {
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
            return new ArrayList<Integer>();

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


    private void printTree(Node node, int space)
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
        System.out.print(node.data + "\n");

        printTree(node.left, space);
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