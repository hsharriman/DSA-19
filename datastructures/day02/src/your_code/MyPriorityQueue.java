package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private LinkedList<Integer> queue;
    private int size;
    public MyPriorityQueue(){
        queue = new LinkedList<>();
    }
//remove each item from queue, test value, rebuild queue.
    public void enqueue(int item) {
        boolean itemAdded = false;
        if (queue.isEmpty()) {
            queue.add(item);
        } else if (queue.size()==1) {
            if (queue.getFirst() < item) {
                queue.addFirst(item);
            } else if (queue.getFirst() >= item) {
                queue.addLast(item);
            }
        } else {
            for (int i=0; i<queue.size(); i++){
                int testVal = queue.getFirst();
                if (testVal > item || itemAdded){
                    queue.addLast(queue.removeFirst());
                } else if (testVal <= item){
                    queue.addLast(item);
                    itemAdded = true;
                }
            }
        }
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        return queue.removeFirst();
    }

}