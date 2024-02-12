public class BinarySearch {
    public static int binarySearch(int[] a, int target) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            System.out.println(middle);
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

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 9, 10};
        int actual = binarySearch(a, 2);
        System.out.println("Expected: 3 Actual: " + actual);
    }
}
