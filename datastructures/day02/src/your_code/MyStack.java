package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxStack;

    public MyStack() {
        ll = new LinkedList<>();
        maxStack = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        if (maxStack.isEmpty() || maxStack.getFirst() <= e){
            maxStack.addFirst(e);
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop == maxStack.getFirst()){
            maxStack.removeFirst();
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return maxStack.getFirst();
    }
}
