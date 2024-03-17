public class LinkedSetClient {
    public static void main(String[] args) {
        Set<Integer> set = new LinkedSet<Integer>();
        Set<Integer> set2 = new LinkedSet<Integer>();
        set.add(1);
        set2.add(1);

        System.out.println(set.equals(set2));
    }
}
