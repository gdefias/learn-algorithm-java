package Algorithm.Set;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Defias on 2020/06.
 * Description: 背包数据类型
 */


public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of bag
    private int n;               // number of elements in bag

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<String>();
        String[] inputs = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (String item : inputs) {
            bag.add(item);
        }

        System.out.println("size of bag = " + bag.size());
        for (String s : bag) {
            System.out.print(s+" ");
        }
    }

    public Bag() {
        first = null;
        n = 0;
    }


    public boolean isEmpty() {
        return first == null;
    }


    //Returns the number of items in this bag
    public int size() {
        return n;
    }


    //Adds the item to this bag.
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }



    //Returns an iterator that iterates over the items in this bag in arbitrary order
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  {
            return current != null;
        }

        public void remove()   {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}