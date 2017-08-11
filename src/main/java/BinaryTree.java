
import java.util.*;

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

    /**
     * 根据先序遍历和中序遍历数组重构二叉树
     * @param pre 先序遍历数组
     * @param in 中序遍历数组
     * @return 二叉树根节点
     */
    public static TreeNode reconstructByPreIn(int [] pre, int [] in) {
        if(pre == null || in == null || 0 == pre.length || 0 == in.length || pre.length != in.length) return null;
        TreeNode root = new TreeNode(pre[0]);

        int index = -1;
        for(int i = 0; i < in.length; ++i){
            if(root.val == in[i]){
                index = i;
                break;
            }
        }

        int[] leftPre = Arrays.copyOfRange(pre, 1, index + 1);
        int[] leftIn = Arrays.copyOfRange(in, 0, index);

        int[] rightPre = Arrays.copyOfRange(pre, index + 1, pre.length);
        int[] rightIn = Arrays.copyOfRange(in, index + 1, in.length);

        root.left = reconstructByPreIn(leftPre, leftIn);
        root.right = reconstructByPreIn(rightPre, rightIn);
        return root;
    }

    public static TreeNode reconstructByPostIn(int [] post, int [] in) {
        if(post == null || in == null || post.length == 0 || in.length == 0 || post.length != in.length) return null;
        int len = post.length;
        TreeNode root = new TreeNode(post[len - 1]);
        int index = -1;
        for (int i = 0; i < len; i++) {
            if(root.val == in[i]){
                index = i;
                break;
            }
        }
        int[] leftIn = Arrays.copyOfRange(in, 0 , index);
        int[] rightIn = Arrays.copyOfRange(in, index + 1, len);
        int[] leftPost = Arrays.copyOfRange(post, 0, index);
        int[] rightPost = Arrays.copyOfRange(post, index, len - 1);

        root.left = reconstructByPostIn(leftPost, leftIn);
        root.right = reconstructByPostIn(rightPost, rightIn);
        return root;
    }

    public static TreeNode reconstructByPostIn_WithoutExtraSpace(int [] post, int [] in) {
        if(post == null || in == null || post.length == 0 || in.length == 0 || post.length != in.length) return null;
        TreeNode root = reconstructByPostInHelper(post, 0, post.length - 1, in, 0, in.length - 1);
        return root;
    }

    public static TreeNode reconstructByPostInHelper(int[] post, int startPost, int endPost, int[] in, int startIn, int endIn){
        if(startIn > endIn || startPost > endPost) return null;
        TreeNode root = new TreeNode(post[endPost]);
        for (int i = startIn; i <= endIn; i++) {
            if(root.val == in[i]){
                root.left = reconstructByPostInHelper(post, startPost, i - startIn + startPost - 1, in, startIn, i - 1);
                root.right = reconstructByPostInHelper(post, i - startIn + startPost, endPost - 1, in, i + 1, endIn );
                break;
            }
        }
        return root;
    }

    public static TreeNode reconstructByPreIn_WithoutExtraSpace(int [] pre, int [] in) {
        if(pre == null || in == null || pre.length == 0 || in.length == 0 || pre.length != in.length) return null;
        TreeNode root = reconstructByPreInHelper(pre, 0, pre.length - 1, in ,0 , in.length - 1);
        return root;
    }

    public static TreeNode reconstructByPreInHelper(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn){
        if(startPre > endPre || startIn > endIn) return null;
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn ; i++) {
            if (root.val == in[i]){
                root.left = reconstructByPreInHelper(pre, startPre + 1, i - startIn +startPre, in, startIn, i - 1);
                root.right = reconstructByPreInHelper(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        }
        return root;
    }

}