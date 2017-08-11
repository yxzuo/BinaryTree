/**
 * Created by yxzuo on 2017/8/3.
 */
public class Test {

    /**
     *                  13
     *                 /  \
     *               65    5
     *              /  \    \
     *             97  25   37
     *            /    /\   /
     *           22   4 28 32
     */

    public static void main(String[] args) {
//        Integer[] a = {1,2,3,4,null,6,7,null,null,null,null,null,null,null,8};
        Integer[] a = {13,65,5,97,25,null,37,22,null,4,28,null,null,32,null};
        int[] pre =  {13,65,97,22,25,4,28,5,37,32};
        int[] in =   {22,97,65,4,25,28,13,5,32,37};
        int[] post = {22,97,4,28,25,65,32,37,5,13};
        BinaryTree bt = new BinaryTree();
        bt.createTree(a);
        BinaryTree.preoderTraversal(BinaryTree.reconstructByPreIn_WithoutExtraSpace(pre,in));

    }
}
