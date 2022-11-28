import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedListDeque<Item> extends LinkedList<Item> implements Deque<Item> {

    @Override
    public void addFirst(Item item) {
        super.addFirst(item);
    }

    @Override
    public void addLast(Item item) {
        super.addLast(item);
    }

    @Override
    public boolean isEmpty() {
        try {
            return super.isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public int size() {
        try {
            return super.size();
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    @Override
    public void printDeque() {
        System.out.print(super.toString());
    }

    @Override
    public Item removeFirst() {
        try {
            return super.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Item removeLast() {
        try {
            return super.removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Item get(int index) {
        try {
            return super.get(index);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}