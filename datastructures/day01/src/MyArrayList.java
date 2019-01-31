public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        this.elems = new Cow[10];
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        this.elems = new Cow[capacity];
    }

    //Runtime: O(1)*
    public void add(Cow c) {
        if (size >= elems.length) {
            Cow[] newElems = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, newElems, 0, elems.length);
            this.elems = newElems;
        } else {
            elems[size] = c;
        }
        size++;
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        if (elems[index] == null) {
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    // Runtime: O(n)*
    public Cow remove(int index) {
        Cow c = get(index);
        if (elems.length >= 8 && size <= elems.length / 4) {
            Cow[] newElems = new Cow[elems.length / 2];
            System.arraycopy(elems, 0, newElems, 0, index);
            System.arraycopy(elems, index + 1, newElems, index, size - (index + 1));
            this.elems = newElems;
        } else {
            for (int i = index; i < size - 1; i++) {
                elems[i] = elems[i + 1];
            }
        }
        size--;
        return c;
    }

    // Runtime: O(n)*
    public void add(int index, Cow c) {
        System.out.printf("%d, %d", index, size);
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= elems.length) {
            Cow[] newElems = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, newElems, 0, index);
            System.arraycopy(elems, index, newElems, index + 1, size - index);
            this.elems = newElems;
        } else {
            for (int i = size - 1; i > index; i--) {
                elems[i] = elems[i - 1];
            }
        }
        elems[index] = c;
        size++;
    }
}