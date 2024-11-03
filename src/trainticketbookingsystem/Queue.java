
package trainticketbookingsystem;

public class Queue<T> {
    
    private Node<T> front;
    private Node<T> rear;

    public Queue() {
        front = null;
        rear = null;
    }
    
    public void enqueue(T data){
        
        Node<T> helptr = new Node(data);
        
        if (isEmpty()) {
            front = helptr;
            rear = helptr;
        }else{
            rear.next = helptr;
            rear = helptr;
        }   
    }
    public T dequeue(){
        
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        
        Node<T> helptr;
        helptr = front;
        front = front.next;
        return helptr.data;
        
    } 
    public boolean isEmpty(){
       return front == null;
    }
    
    
    public Node<T> peak(){
        if (isEmpty()) {
            return null;
        }
        return front;
    }
}
