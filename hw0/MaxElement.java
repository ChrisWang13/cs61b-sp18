public class MaxElement {
  public static int max(int[] m) {
    int len = m.length;
    int idx = 0;
    for (int i = 0; i < len; i++) {
      if (m[i] > m[idx]) {
        idx = i;
      }
    }
    return m[idx];
  }
  public static void main(String[] args) {
     int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};      
     int max_val = max(numbers);
     System.out.println(max_val);
  }
}