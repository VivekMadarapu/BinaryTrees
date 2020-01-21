import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Rake {

    static int[] tree;
    static int index;
    static Hashtable<Integer, Integer> positions;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner kb = new Scanner(new File("rake.dat"));

        for (int i = 1;kb.hasNext();i++) {
            String[] line = kb.nextLine().split(" ");
            if (line.length == 1 && line[0].equals("-1")) {
                break;
            }
            tree = new int[line.length];
            positions = new Hashtable<>();
            for (int j = 0; j < line.length; j++) {
                tree[j] = Integer.parseInt(line[j]);
            }

            index = 0;
            TreeNode overallNode = addNode(0);
            storePositions(overallNode);

            System.out.printf("Case %d:\n", i);
            Object[] collection = positions.keySet().toArray();
            for (int j = collection.length - 1; j >= 0; j--) {
                System.out.print(positions.get(collection[j]) + " ");
            }

            System.out.println("\n");
        }
    }

    static TreeNode addNode(int pos){
        if(tree[index] == -1){
            return null;
        }

        TreeNode node = new TreeNode(tree[index], pos);
        index++;
        node.setLeft(addNode(pos - 1));
        index++;
        node.setRight(addNode(pos + 1));

        return node;
    }

    static void storePositions(TreeNode node){
        if(node == null){
            return;
        }
        if(positions.containsKey(node.position + tree.length)){
            int num = positions.get(node.position + tree.length) + node.data;
            positions.remove(node.position + tree.length);
            positions.put(node.position + tree.length, num);
            storePositions(node.left);
            storePositions(node.right);
        }
        else{
            positions.put(node.position + tree.length, node.data);
            storePositions(node.left);
            storePositions(node.right);
        }
    }

    private static class TreeNode{
        TreeNode left;
        TreeNode right;
        int data;
        int position;

        TreeNode(int data, int position){
            this.data = data;
            this.position = position;
        }

        void setLeft(TreeNode left) {
            this.left = left;
        }

        void setRight(TreeNode right) {
            this.right = right;
        }

        public String toString(){
            return toString2(this);
        }

        String toString2(TreeNode node){
            if(node == null){
                return "-1";
            }
            return "(" + node.data + ", " + toString2(node.left) + ", " + toString2(node.right) + ")";
        }
    }
}