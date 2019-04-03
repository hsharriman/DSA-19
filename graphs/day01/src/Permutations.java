import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//
public class Permutations {
    private static void backtrack(LinkedList<Integer> curr, Set<Integer> unused, List<List<Integer>>perms){
        if (unused.isEmpty()){
            perms.add(new LinkedList<>(curr));
        }
        for (int num : new LinkedList<>(unused)){
            curr.addLast(num);
            unused.remove(num);
            backtrack(curr, unused, perms);
            curr.removeLast();
            unused.add(num);
        }
    }
    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        Set<Integer> unused = new HashSet<>(A);
        backtrack(new LinkedList<Integer>(), unused, permutations);

        return permutations;
    }

}
