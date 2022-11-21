
public class ArrayDequeTest {
  public static void main(String[] args) {
    ArrayDeque<Integer> arr = new ArrayDeque<Integer>();

//    arr.addLast(5);
//    arr.addLast(6);
//    arr.addFirst(4);
//    arr.addFirst(7);
//    for (int i = 0; i < 10000000; ++i) {
//      arr.addLast(i);
//    }
    arr.addFirst(0);
    arr.addFirst(1);
    arr.addFirst(2);
    arr.addFirst(3);
    arr.addFirst(4);
    arr.isEmpty();
    arr.addFirst(6);
    arr.addFirst(7);
    arr.addFirst(8);
    //  expected:<0>
    System.out.println(arr.removeLast());
//    arr.removeFirst();
//    arr.removeFirst();

    arr.printDeque();

//    for (int i = 0; i < 4; ++i) {
//      System.out.print(arr.get(i) + " ");
//    }
    System.out.println(" ");
//    System.out.println(arr.removeFirst());
//    System.out.println(arr.removeLast());
//    arr.printDeque();
    System.out.println(arr.size());
  }


}