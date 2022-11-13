public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    int len = a.length;
    // Edge case: last element, no need to add
    for (int i = 0; i < len - 1; ++i) {
      if (a[i] < 0) continue;
      // a[i] > 0, sum up till the end;
      for (int j = 1; j <= n; ++j) {
        if (i + j >=len) break;
        a[i] += a[i + j]; 
      }
    }
  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;
    windowPosSum(a, n);
    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(java.util.Arrays.toString(a));
  }
} 

