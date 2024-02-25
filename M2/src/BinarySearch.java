public class BinarySearch {
    public static int binarySearch(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (a[middle] == target){
                return middle;
            }
            if (a[middle] < target){
                left = middle + 1;
            }
            if (a[middle] > target){
                right = middle-1;
            }
        }
        return -1;
    }

    public static int foo(int n, int f){
        System.out.println(n);
        System.out.println(f);
        if (n == 1){
            return f;
        }
        return foo(n-1, n+f);
    }



    public static void main(String[] args) {
        System.out.println(foo(5,1));
    }
}
