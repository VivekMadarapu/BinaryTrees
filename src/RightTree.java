import java.util.Scanner;

public class RightTree {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = input.nextInt();
        input.nextLine();
        for (int i = 0; i < cases; i++) {
            String in = input.nextLine();
            Scanner treeIn = new Scanner(in);
            treeIn.useDelimiter("");
            IntBinaryTree tree = new IntBinaryTree(new IntBinaryTree.IntTreeNode(treeIn.nextInt(), null, null));
            int left = treeIn.nextInt();
            int right = treeIn.nextInt();
            if(left == 1){
                tree.overallRoot.left = new IntBinaryTree.IntTreeNode(left, null, null);
            }
            if(right == 1){
                tree.overallRoot.right = new IntBinaryTree.IntTreeNode(right, null, null);
            }

            System.out.println(tree);
        }

    }




}
