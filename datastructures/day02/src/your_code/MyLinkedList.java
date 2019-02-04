package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (isEmpty()){
            head = new Node(c);
            tail = head;
        } else{
            Node oldTail = tail;
            tail = new Node(c, oldTail, null);
            oldTail.next = tail;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        if (isEmpty()){
            head = new Node(c);
            tail = head;
        } else{
            Node oldHead = head;
            head = new Node(c, null, oldHead);
            oldHead.prev = head;
        }
        size++;
    }

    public Chicken get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node node = head;
        for (int i=0; i < index; i++){
            node = node.next;
        }
        return node.val;
    }

    public Chicken remove(int index) {
        Node node = head;
        if (index == 0){
            removeFirst();
        } else if (index == size -1){
            node = tail;
            removeLast();
        } else{
            for (int i=0; i<index; i++){
                node = node.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        return node.val;
    }

    public Chicken removeFirst() {
        Node removedHead = head;
        size--;
        if (isEmpty()){
            head = null;
            tail = null;
        } else{
            head = head.next;
            head.prev = null;
        }
        return removedHead.val;
    }

    public Chicken removeLast() {
        Chicken removedChicken = tail.val;
        size--;
        if (isEmpty()){
            head = null;
            tail = null;
        } else{
            tail = tail.prev;
            tail.next = null;
        }
        return removedChicken;
    }
}