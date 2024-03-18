import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Jordan Lee (jtl0071@auburn.edu)
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /**
     * References to the first and last node of the list.
     */
    Node front;
    Node rear;

    /**
     * The number of nodes in the list.
     */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////

    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////

    /**
     * Returns the current size of this collection.
     *
     * @return the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////

    /**
     * Tests to see if this collection is empty.
     *
     * @return true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param element The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {
        if (element == null || contains(element)) {
            return false;
        }
        Node n = new Node(element);
        // if front is null, set is empty so add to front
        if (front == null) {
            front = n;
            rear = n;
            size++;
            return true;
        }
        // if the element is greater than the front, add it to the front
        if (front.element.compareTo(element) > 0) {
            n.next = front;
            front.prev = n;
            front = n;
            size++;
            return true;
        }
        // if the element is less than the front, add it to the back
        if (rear.element.compareTo(element) < 0) {
            rear.next = n;
            n.prev = rear;
            rear = n;
            size++;
            return true;
        }
        // iterate over set and find which value is less than the element, then add it behind that element.
        Node f = front;
        while (f != null) {
            if (f.element.compareTo(element) > 0) {
                n.next = f;
                n.prev = f.prev;
                f.prev.next = n;
                f.prev = n;
                size++;
                return true;
            }
            f = f.next;
        }
        return false;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param element The element to be removed.
     * @return true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {
        if (element == null || !contains(element)){
            return false;
        }
        Node n = locate(element);
        if (n == null) {
            return false;
        }
        if (n == front) {
            front = front.next;
            if (front == null) {
                rear = null;
            } else {
                front.prev = null;
            }
            size--;
            return true;
        }
        if (n == rear) {
            rear = rear.prev;
            rear.next = null;
            size--;
            return true;
        }
        n.prev.next = n.next;
        n.next.prev = n.prev;
        size--;
        return true;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param element The element whose presence in this collection is to be tested.
     * @return true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
        Node f = front;
        while (f != null) {
            if (f.element.equals(element)) {
                return true;
            }
            f = f.next;
        }
        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return true if this set contains exactly the same elements as
     * the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        if (s == null){
            return false;
        }
        if (this.size() != s.size()){
            return false;
        }
        for (T element : s){
            if (!contains(element)){
                return false;
            }
        }
        return true;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return true if this set contains exactly the same elements as
     * the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        if (s == null){
            return false;
        }
        if (size != s.size){
            return false;
        }
        Node n = front;
        Node p = s.front;
        while (n != null && p != null){
            if (!n.element.equals(p.element)){
                return false;
            }
            n = n.next;
            p = p.next;
        }
        return true;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s) {
        if (s == null){
            return null;
        }
        if (s.equals(this) || this.isEmpty()){
            return s;
        }
        if (s.isEmpty()){
            return this;
        }
        Set<T> union = new LinkedSet<T>();
        for (T element : s){
            union.add(element);
        }
        for (T element : this){
            union.add(element);
        }
        return union;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     * @return a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s) {
        if (s == null){
            return null;
        }
        if (s.equals(this) || this.isEmpty()){
            return s;
        }
        if (s.isEmpty()){
            return this;
        }
        LinkedSet<T> union = new LinkedSet<T>();
        Node n = front;
        Node p = s.front;
        while (n != null){
            union.add(n.element);
            n = n.next;
        }
        while (p != null){
            union.add(p.element);
            p = p.next;
        }
        return union;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        if (s == null){
            return null;
        }
        Set<T> intersection = new LinkedSet<T>();
        for (T element : s){
            if (this.contains(element)){
                intersection.add(element);
            }
        }
        return intersection;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     *
     * @return a set that contains elements that are in both
     * this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {
        if (s == null){
            return null;
        }
        Set<T> intersection = new LinkedSet<T>();
        Node n = front;
        Node p = s.front;
        if (n == null || p == null){
            return intersection;
        }
        while (n != null){
            if (s.contains(n.element)){
                intersection.add(n.element);
            }
            n = n.next;
        }
        return intersection;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {
        if (s == null){
            return null;
        }
        Set<T> comp = new LinkedSet<>();
        for (T element : this){
            if (s.contains(element)){
                continue;
            }
            comp.add(element);
        }
        return comp;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return a set that contains elements that are in this
     * set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {
        if (s == null){
            return null;
        }
        Set<T> comp = new LinkedSet<>();
        Node n = front;
        while (n != null){
            if (!s.contains(n.element)){
                comp.add(n.element);
            }
            n = n.next;
        }
        return comp;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new LinkedSetIterator();
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new DescendingLinkedSetIterator();
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {
        return new PowerSetLinkedSetIterator();
    }


    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Feel free to add as many private methods as you need.



    private Node locate(T element) {
        Node f = front;
        Node n = null;
        while (f != null) {
            if (f.element.equals(element)) {
                n = f;
                break;
            }
            f = f.next;
        }
        return n;
    }



    ////////////////////
    // Nested classes //
    ////////////////////

    private class LinkedSetIterator implements Iterator<T> {
        private Node current = front;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the set.");
            }
            T value = current.element;
            current = current.next;
            return value;
        }
    }

    private class DescendingLinkedSetIterator implements Iterator<T> {
        private Node current = rear;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the set.");
            }
            T value = current.element;
            current = current.prev;
            return value;
        }
    }

    private class PowerSetLinkedSetIterator implements Iterator<Set<T>> {
        private int powerSetSize = (int) Math.pow(2, size);
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < powerSetSize;
        }

        @Override
        public Set<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the power set.");
            }
            LinkedSet<T> subset = new LinkedSet<>();
            int mask = 1;
            for (int i = 0; i < size; i++) {
                if ((current & mask) != 0) {
                    subset.add(getElement(i));
                }
                mask = mask << 1;
            }
            current++;
            return subset;
        }

        private T getElement(int index) {
            Node n = front;
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            return n.element;
        }
    }

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /**
         * the value stored in this node.
         */
        T element;
        /**
         * a reference to the node after this node.
         */
        Node next;
        /**
         * a reference to the node before this node.
         */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that contains element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}
