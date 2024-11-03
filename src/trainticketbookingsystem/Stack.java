
package trainticketbookingsystem;

public class Stack<T> {
    
    private Node<T> top;

    public Stack(){
        top = null;
    }
    
    public void push(T data){
        Node<T> helptr = new Node(data);
        helptr.next = top;
        top = helptr;
        
    }
    
    public T pop(){
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        return data;
    }
    
    public T top(){
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }
    
    public boolean isEmpty(){
        return top == null;
    }

}