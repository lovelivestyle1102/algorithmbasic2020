package class03;

public class Code031_DoubleEndsQueueToStackAndQueue {
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public boolean addFromTop(T data){
             Node node = new Node(data);

             if(head == null){
                 head = node;
                 tail = node;
             }else{
                 node.next = head;
                 head.last = node;
                 head = node;
             }
             return true;
        }

        public boolean addFromBottle(T data){
            Node node = new Node(data);

            if(tail == null){
                head = node;
                tail = node;
            }else{
                tail.next = node;
                node.last = tail;
                tail = node;
            }
            return true;
        }

        public T popFromTop(){
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        public T popFromBottle(){
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                cur.next = null;
                tail.next = null;
            }
            return cur.value;
        }

        public boolean isEmpty(){
            return head != null ? head == tail:false;
        }

    }

    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack(DoubleEndsQueue queue){
            this.queue = queue;
        }

        public boolean push(T data){
            return queue.addFromTop(data);
        }

        public T pop(){
            return queue.popFromTop();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T> {
        private Code03_DoubleEndsQueueToStackAndQueue.DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new Code03_DoubleEndsQueueToStackAndQueue.DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

}
