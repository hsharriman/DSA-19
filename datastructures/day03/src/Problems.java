import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // For now, return a List that's correct size, but contains only 0s
        LinkedList<Integer> l = new LinkedList<>();
        int targetSize = A.length - k;
        for (int i = 0; i < A.length; i++) {
            if (i > 0){
                System.out.printf("last L %d, curr %d\n", l.getLast(), A[i]);
            }
            if (l.isEmpty()){
                l.add(A[i]);
                System.out.printf("add %d\n", A[i]);
            } else if (l.getLast() >= A[i] && k>0){
                while (l.size() > 0 && A[i] < l.getLast() && k>0){
                    System.out.printf("pop %d\n", l.getLast());
                    l.removeLast();
                    k--;
                }
                l.add(A[i]);
                System.out.printf("add %d\n", A[i]);
            } else if (l.size() < targetSize){
                l.add(A[i]);
                System.out.printf("add %d\n", A[i]);
            }

        }
        for (int i=0; i < l.size(); i++){
            System.out.println(l.get(i));
        }
        return l;
    }
    private static int countList(Node n){
        int count = 1;
        if (n == null){
            return 0;
        } else if (n.next == null){
            return 1;
        } else{
            while (n.next != null) {
                n = n.next;
                count++;
            }
        }
        return count;
    }
    //O(2N) time, O(1) space
    public static boolean isPalindrome(Node n) {
        Node oldNext = n;
        Node prev = n;
        Node flip = n;
        final int count = countList(n);
        if (count == 0 || count == 1) {
            return true;
        }
        for (int i=0; i<(count/2); i++){
            if (i==0){
                oldNext = flip.next;
                flip.next = null;
                prev = flip;    //Set prev for next iteration ref
            } else {
                flip = oldNext;
                oldNext = flip.next;
                flip.next = prev;
                prev = flip;
            }
        }
        if (count % 2 != 0) {
            oldNext = oldNext.next; //skip if odd length
        }
        //Compare flipped half with second half
        for (int i = 0; i<(count/2); i++){
            if (oldNext.val != flip.val){
                return false;
            }
            flip = flip.next;
            oldNext = oldNext.next;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        LinkedList<Character> bin = new LinkedList<>();
        String fin = "";
        Character oper;
        for (int i=0; i<s.length(); i++){
            if (s.charAt(i) == '+' || s.charAt(i) == '*'){
                bin.add(s.charAt(i));
            } else if (s.charAt(i) == ')'){
                oper = bin.removeLast();
                fin = fin + Character.toString(oper) + " ";
            } else if (Character.isDigit(s.charAt(i))){
                fin = fin + s.charAt(i) + " ";
            }
        }
        for (int i=0; i<fin.length(); i++){
            System.out.print(fin.charAt(i));
        }
        return fin.trim();
    }

}
