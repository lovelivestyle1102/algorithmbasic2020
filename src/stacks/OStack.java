package stacks;

public class OStack {
    private int cap;

    private int top;

    private int[] container;

    public OStack(int cap){
        this.cap = cap;
        top = -1;
        container = new int[cap];
    }

    public void push(int elem){
        if(top == cap -1){
            throw new RuntimeException("栈已满！");
        }

        container[top++] = elem;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈已空");
        }

        int elem = container[top--];

        return elem;
    }

    public boolean isEmpty(){
        return top == -1;
    }
}
