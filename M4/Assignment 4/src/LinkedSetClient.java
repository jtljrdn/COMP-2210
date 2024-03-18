public class LinkedSetClient {
    public static void main(String[] args) {
        LinkedSet<Integer> set = new LinkedSet<Integer>();
        LinkedSet<Integer> set2 = new LinkedSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);

        set2.add(3);
        set2.add(2);



        Set<Integer> output = set.complement(set2);
        System.out.println(output);
    }
}
