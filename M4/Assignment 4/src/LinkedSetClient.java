public class LinkedSetClient {
    public static void main (String[] args){
        Set<Integer> set = new LinkedSet<Integer>();
        set.add(3);
        set.add(4);
        set.add(5);
        set.remove(5);
        System.out.println(set);
    }
}
