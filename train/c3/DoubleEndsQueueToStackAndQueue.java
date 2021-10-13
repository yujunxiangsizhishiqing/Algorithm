package train.c3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双链表实现 栈 和 队列（普通队列和双端队列）
 * */
public class DoubleEndsQueueToStackAndQueue {

    //节点结构
    public static class DoubleNode<T>{
        public T value;
        public DoubleNode<T> next;
        public DoubleNode<T> last;

        public DoubleNode(T value){
            this.value = value;
        }
    }


    //实现双端队列
    public static class DoubleEndsQueue<T>{
        public DoubleNode<T> head;
        public DoubleNode<T> tail;


        //从头部添加数据
        public void addFromHead(T value){
            DoubleNode<T> cur = new DoubleNode<>(value);

            if (head==null){
                head = cur;
                tail = cur;
            }else{
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        //从尾部添加数据
        public void addFromTail(T value){
            DoubleNode<T> cur = new DoubleNode<T>(value);

            if (head == null){
                head = cur;
                tail = tail;
            }else{
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        //从头部弹出数据
        public T popFromHead(){
            if (isEmpty()){
                return null;
            }

            DoubleNode<T> cur = head;
            if (head == tail){
                head = null;
                tail = null;
            }else{
                head = head.next;

                head.last = null;
                cur.next = null;//这一行的逻辑加上比较好
            }
            return cur.value;

            //左程云
//            if (head == null) {
//                return null;
//            }
//            DoubleNode<T> cur = head;
//            if (head == tail) {
//                head = null;
//                tail = null;
//            } else {
//                head = head.next;
//                cur.next = null;
//                head.last = null;
//            }
//            return cur.value;
        }

        //从尾部弹出数据
        public T popFromTail(){
            if (isEmpty()){
                return null;
            }

            DoubleNode<T> cur = tail;
            if (head==tail){
                head=null;
                tail=null;
            }else{
                tail = tail.last;

                tail.next=null;
                cur.last=null;//这一行的逻辑加上比较好
            }
            return cur.value;

            //左程云
//            if (head == null) {
//                return null;
//            }
//            DoubleNode<T> cur = tail;
//            if (head == tail) {
//                head = null;
//                tail = null;
//            } else {
//                tail = tail.last;
//                tail.next = null;
//                cur.last = null;
//            }
//            return cur.value;
        }

        //队列是否为空
        public boolean isEmpty() {
            return head == null;
        }
    }

    //实现栈
    public static class MyStack<T>{
        private DoubleEndsQueue<T> stack;

        public MyStack(){
            stack = new DoubleEndsQueue<T>();
        }

        public void Push(T value){
            stack.addFromHead(value);
        }

        public T Pop(){
            return stack.popFromHead();
        }

        public boolean isEmpty(){
            return stack.isEmpty();
        }
    }


    public static class MyQueue<T>{
        private DoubleEndsQueue<T> queue;

        public MyQueue(){
            queue = new DoubleEndsQueue<T>();
        }

        public void Push(T value){
            queue.addFromHead(value);
        }

        public T Poll(){
            return queue.popFromTail();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                //栈的测试
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.Push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.Push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.Pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                //队列的测试
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.Push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.Push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.Poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
