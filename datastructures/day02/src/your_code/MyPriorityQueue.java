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

    public void enqueue(int item) {
        if (queue.isEmpty()){
            queue.add(item);
        } else if (queue.get(0) <= item){
            queue.addFirst(item);
        } else if (queue.get(queue.size()-1) >= item){
            queue.addLast(item);
        } else {
            for (int i=0; i<queue.size()-1; i++){
                if (queue.get(i) >= item && queue.get(i+1) <= item){
                    queue.add(i+1, item);
                    break;
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