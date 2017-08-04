
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yxzuo on 2017/8/3.
 */
public class BinaryTree{

    static class TreeNode{
        TreeNode left;
        TreeNode right;
        Integer val;

        TreeNode(Integer val){
            this.val = val;
        }
    }

    private TreeNode root;

    private List<TreeNode> list;

    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(TreeNode node){
        this.root = node;
    }

    public TreeNode getRoot(){
        return  this.root;
    }

    public void createTree(Integer[] arr){
        if(arr == null || arr.length == 0) return;
        int len = arr.length;
        list = new ArrayList<TreeNode>(len);
        for(int i = 0; i < len; i++){
            if(arr[i] == null){
                list.add(null);
            }
            else{
                list.add(new TreeNode(arr[i]));
            }
        }
        for(int j = 0; j < (len / 2 ) ; j ++){
            TreeNode node;
            if( (node = list.get(j)) != null) {
                if (2 * j + 1 < len) {
                    node.left = list.get(j * 2 + 1);
                }
                if (2 * j + 2 < len) {
                    node.right = list.get(j * 2 + 2);
                }
            }
        }
        root = list.get(0);
    }

    public static void preoderTraversal(TreeNode root){
        if(root == null) return;
        System.out.println(root.val);
        preoderTraversal(root.left);
        preoderTraversal(root.right);
    }

    public static void  inoderTraversal(TreeNode root){
        if(root == null) return;
        inoderTraversal(root.left);
        System.out.println(root.val);
        inoderTraversal(root.right);
    }

    public static void postoderTraversal(TreeNode root){
        if(root == null) return;
        postoderTraversal(root.left);
        postoderTraversal(root.right);
        System.out.println(root.val);
    }

    public static void depthFirstTraserval(TreeNode root){
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.val+"  ");
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    public static void breadthFirstTraserval(TreeNode root){
        if(root == null) return;
        ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            System.out.print(node.val + "  ");
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        System.out.print("\n");
    }

    public static int getMaxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(getMaxDepth(root.left), getMaxDepth(root.right)) + 1;
    }

    public static TreeNode invertTree(TreeNode root){
        if(root == null) return null;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }
}