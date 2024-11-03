
package trainticketbookingsystem;

public class Node<T> {
    
    T data;
    Node<T> next;

    public Node(T data) {
        this(data,null);
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    
    
}