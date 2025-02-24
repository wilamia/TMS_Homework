package SingleList;

public class SingleLinkedList<T> {
    Node<T> first;
    Node<T> last;

    public SingleLinkedList() {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void add(T value) {
        Node<T> p = new Node<>(value);
        if (isEmpty()) {
            first = p;
            last = p;
        } else {
            first.next = p;
            first = p;
        }
    }

    public void print() {
        if (isEmpty()) System.out.println("[]");
        Node<T> t = this.last;
        while (t != null) {
            System.out.print(t.item + " ");
            t = t.next;
        }
        System.out.println();
    }

    public void remove(int id) {
        int count = 0;
        Node<T> t = this.last;

        if (id == 0) {
            last = t.next;
            return;
        }

        while (t.next != null) {
            if (count == id - 1) {
                t.next = t.next.next;
                return;
            }
            count++;
            t = t.next;
        }
    }

    public void remove(T value) {
        Node<T> t = this.last;

        while (t.next != null) {
            if (value == t.next.item) {
                t.next = t.next.next;
                return;
            }
            t = t.next;
        }
    }

    public T get(int id) {
        int count = 0;
        Node<T> t = this.last;

        while (t.next != null) {
            if (count == id) {
                return t.item;
            }
            count++;
            t = t.next;
        }
        return null;
    }

    public int getId(T value) {
        Node<T> t = this.last;
        int count = -1;

        while (t.next != null) {
            count++;
            if (value == t.item) {
                break;
            }
            t = t.next;
        }
        return count;
    }

    public void set(int id, T value) {
        Node<T> t = this.last;
        int count = -1;

        while (t.next != null) {
            count++;
            if (count == id) {
                t.item = value;
                break;
            }
            t = t.next;
        }
    }

    public void add(int id, T value) {
        Node<T> t = this.last;
        int count = 0;

        while (t != null) {
            if (count == id) {
                T valueItem = t.item;
                t.item = value;

                while (t.next != null) {
                    t = t.next;
                    T nextValueItem = t.item;
                    t.item = valueItem;
                    valueItem = nextValueItem;
                }
                return;
            }
            t = t.next;
            count++;
        }
    }

}
