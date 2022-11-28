public interface Deque<T> {
    // JLS: The public modifier should be omitted in Java interfaces
    void addFirst(T item);
    void addLast(T item);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
