package DataStructures;

/**
 * LinkedList
 */
public class MyLinkedList<E> {

    public Node<E> head;
    /**
     * define whatever you want
     */
    public void insert(E item){
        if (head == null){
            head = new Node<>(item);
        }else{
            Node<E> n = head;
            while(n.next != null){
                n = n.next;
            }
            n.next = new Node<>(item);
        }
    }

}