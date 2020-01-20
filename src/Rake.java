import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Rake {

    private static int[] tree;
    private static int index;
    private static Hashtable<Integer, Integer> positions;

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
            IntNode overallNode = addNode(0);
            storePositions(overallNode);

            System.out.printf("Case %d:\n", i);
            Object[] collection = positions.keySet().toArray();
            for (int j = collection.length - 1; j >= 0; j--) {
                System.out.print(positions.get(collection[j]) + " ");
            }

            System.out.println("\n");
        }
    }

    private static IntNode addNode(int pos){
        if(tree[index] == -1){
            return null;
        }

        IntNode node = new IntNode(tree[index], pos);
        index++;
        node.setLeft(addNode(pos - 1));
        index++;
        node.setRight(addNode(pos + 1));

        return node;
    }

    private static void storePositions(IntNode node){
        if(node == null){
            return;
        }
        if(positions.containsKey(node.getPosition() + tree.length)){
            int num = positions.get(node.getPosition() + tree.length) + node.data;
            positions.remove(node.getPosition() + tree.length);
            positions.put(node.getPosition() + tree.length, num);
            storePositions(node.getLeft());
            storePositions(node.getRight());
        }
        else{
            positions.put(node.getPosition() + tree.length, node.data);
            storePositions(node.getLeft());
            storePositions(node.getRight());
        }

    }

    private static class IntNode{
        IntNode left;
        IntNode right;
        int data;
        int position;

        IntNode(int data, int position){
            this.data = data;
            this.position = position;
        }

        public int endIndex(int[] tree, int startIndex){
            StringBuilder string = new StringBuilder();
            for (int i: tree) {
                string.append(i);
            }

            return (string.toString()).indexOf("-1", startIndex) + 1;

        }

        void setLeft(IntNode left) {
            this.left = left;
        }

        void setRight(IntNode right) {
            this.right = right;
        }

        public String toString(){
            return toString2(this);
        }

        String toString2(IntNode node){
            if(node == null){
                return "-1";
            }

            return "(" + node.data + ", " + toString2(node.left) + ", " + toString2(node.right) + ")";
        }

        int getPosition() {
            return position;
        }

        IntNode getLeft() {
            return left;
        }

        IntNode getRight() {
            return right;
        }

    }
}