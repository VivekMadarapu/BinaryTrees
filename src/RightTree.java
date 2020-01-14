import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class RightTree {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("righttree.dat"));
        int cases = input.nextInt();
        input.nextLine();

        outer:
        for (int i = 0; i < cases; i++) {
            BinaryTree tree = new BinaryTree(input.nextLine());

            if(tree.root == null){
                System.out.println("Tree " + (i+1) + " is a right-tree");
                continue;
            }

            Stack<BinaryTree.Node> stack = new Stack<>();
            stack.add(tree.root);

            while(stack.size() > 0)
            {
                BinaryTree.Node curr = stack.pop();
                if(curr.left != null){
                    stack.add(curr.left);
                }
                if(curr.right != null){
                    stack.add(curr.right);
                }

                if(tree.areaUnder(curr.left) > tree.areaUnder(curr.right)) {
                    System.out.println("Tree " + (i+1) + " is not a right-tree");
                    continue outer;
                }
            }
            System.out.println("Tree " + (i+1) + " is a right-tree");
        }
    }
}
