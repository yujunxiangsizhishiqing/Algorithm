package train.c3;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * 思路：
 * 用两个栈即可实现队列，一个push栈，一个pop栈
 * 出队时要遵循以下规则：
 * （1）pop栈和push栈为空，说明队列中无数据可出队
 * （2）pop栈为空时，将push栈中所有数据倒入pop栈中，这样pop栈的出栈顺序自然就是队列的出对顺序。
 * （3）注意，当pop栈不为空时，无论如何都不可以将push栈的数据倒入pop栈中，否则出队顺序会出错。
 * */
public class TwoStacksImplementQueue {


    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue(){
            stackPop = new Stack<Integer>();
            stackPush = new Stack<Integer>();
        }


        public void pushToPop(){
            if (stackPop.empty()){
                for (;!stackPush.empty();){
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt){
            stackPush.push(pushInt);
            pushToPop();
        }


        public int poll(){
            if (stackPop.empty()&&stackPush.empty()){
                throw new   RuntimeException("队列已空");
            }

            pushToPop();
            return stackPop.pop();
        }

        public int peek(){
            if (stackPop.empty()&&stackPush.empty()){
                throw new   RuntimeException("队列已空");
            }

            pushToPop();
            return stackPop.peek();
        }
    }


    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }
}
