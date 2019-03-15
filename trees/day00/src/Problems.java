import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        Collections.sort(values);
        addVals(BST, values, 0, values.size());
        return BST;
    }

    private static void addVals(BinarySearchTree<Integer> bst, List<Integer> vals, int begin, int end){
        int middle = (begin + end)/2; //should always add the middle first
        if (begin > end || bst.contains(vals.get(middle))){
            return;
        } else if (begin == end){
            bst.add(vals.get(begin));
            return;
        } else {
            bst.add(vals.get(middle));
            addVals(bst, vals, begin, middle);
            addVals(bst, vals, middle, end);
        }
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
