package train.c3;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用队列实现栈
 *
 * 思路：
 * 用两个队列来实现栈，
 * 入栈时，将所有数据都入队到A队列中
 * 出栈时，将除了A队列中最后一个元素外的其他元素入队到B队中，之后将A，B队列互换即可，即：A队列变成B队列，B队列变成A队列
 * */
public class TwoQueueImplementStack {

    public static class TwoQueueStack<T>{
        private Queue<T> queueA;
        private Queue<T> queueB;

        public TwoQueueStack(){
            queueA = new LinkedList<T>();
            queueB = new LinkedList<T>();
        }

        public void push(T value){
            queueA.offer(value);
        }

        public T poll(){
            for(;queueA.size()>1;){
                queueB.offer(queueA.poll());
            }

            T ans = queueA.poll();
            Queue<T> tmp = queueA;
            queueA = queueB;
            queueB = tmp;

            return ans;

        }

        public T peek() {
            while (queueA.size() > 1) {
                queueB.offer(queueA.poll());
            }
            T ans = queueA.poll();
            queueB.offer(ans);//和poll不同，我们只是检索栈顶的元素，并不会移除栈顶元素。
            Queue<T> tmp = queueA;
            queueA = queueB;
            queueB = tmp;
            return ans;
        }

        public boolean isEmpty() {
            return queueA.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
