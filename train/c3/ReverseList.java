package train.c3;


import java.util.ArrayList;
import java.util.List;

/**
 * 反转单链表
 * 反转双链表
 * */
public class ReverseList {

    //单链表节点结构
    public static class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
        }
    }

    //双链表结构
    public static class DoubleNode{
        int value;
        DoubleNode next;
        DoubleNode last;

        public DoubleNode(int value){
            this.value = value;
        }
    }

    //反转单链表
    public static Node reverseLinkedList(Node head){
        if (head==null||head.next==null){
            return head;
        }

        //起始点head
        Node next =null;
        Node pre = null;

        for (;head != null;){
            next = head.next;//预先存储下次处理的节点
            head.next = pre;//将当前节点的next重新指向
            pre = head;//存储本次处理的节点，用于下次循环中节点的next指向
            head = next;//获取到下次处理的节点
        }

        return pre;

        //左程云
//        Node pre = null;
//        Node next = null;
//        while (head != null) {
//            next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
//        return pre;
    }

    //反转双向链表
    public static DoubleNode reverseDoubleList(DoubleNode head){
        if (head==null||head==null){
            return head;
        }

        //起始点head
        DoubleNode next = null;
        DoubleNode last = null;

        for (;head != null;){
            next = head.next;//记录下次要处理的节点

            head.next = last;//反转节点的next指向
            head.last = next;//反转节点的pre指向

            last = head;//记录本次的节点，以便确定下次循环中的节点的next指向
            head = next;//获取下次处理的节点
        }

        return last;

        //左程云
//        DoubleNode pre = null;
//        DoubleNode next = null;
//        while (head != null) {
//            next = head.next;
//            head.next = pre;
//            head.last = next;
//            pre = head;
//            head = next;
//        }
//        return pre;
    }

    public static Node testReverseLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int N = list.size();
        for (int i = 1; i < N; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(N - 1);
    }

    public static DoubleNode testReverseDoubleList(DoubleNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int N = list.size();
        for (int i = 1; i < N; i++) {
            DoubleNode cur = list.get(i);
            cur.last = null;
            cur.next = pre;
            pre.last = cur;
            pre = cur;
        }
        return list.get(N - 1);
    }

    //生成随机的单链表
    public static Node generateRandomLinkedList(int len, int value) {

        int listSize = (int)(Math.random()*(len+1));//[0,len],确定链表的尺寸
        if (listSize == 0){
            return null;//如果size==0，那么返回一个空链表
        }

        //创建单链表的头节点，并且为头节点的value进行随即赋值,赋值范围[0,value]
        int randomValue = (int)(Math.random()*(value+1));
        Node head = new Node(randomValue);
        listSize--;//创建头节点后，还有size-1的节点需要创建，故size--

        Node cur = head;
        for (;listSize>0;){
            //创建新的节点，并对value进行赋值，同时将其链到链表中
            randomValue = (int)(Math.random()*(value+1));;
            Node next = new Node(randomValue);

            cur.next = next;//当前节点的next指向下一个节点
            cur = next;//记录下次处理的节点
            listSize--;
        }
        return head;

        //左程云
//        int size = (int) (Math.random() * (len + 1));
//        if (size == 0) {
//            return null;
//        }
//        size--;
//        Node head = new Node((int) (Math.random() * (value + 1)));
//        Node pre = head;
//        while (size != 0) {
//            Node cur = new Node((int) (Math.random() * (value + 1)));
//            pre.next = cur;
//            pre = cur;
//            size--;
//        }
//        return head;
    }

    //生成随机的双链表
    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int listSize = (int)(Math.random()*(len+1));//[0,len],确定链表的尺寸
        if (listSize==0){
            return null;//如果size==0，那么返回一个空链表
        }

        //创建双链表的头节点，并且为头节点的value进行随即赋值,赋值范围[0,value]
        int randomValue = (int)(Math.random()*(value+1));
        DoubleNode head = new DoubleNode(randomValue);
        listSize--;

        DoubleNode cur = head;
        for (;listSize>0;){
            //创建新的节点，并对value进行赋值，同时将其链到链表中
            randomValue = (int)(Math.random()*(value+1));
            DoubleNode next = new DoubleNode(randomValue);

            cur.next = next;//当前节点的next指向为下一节点
            next.last = cur;//下一个节点的last指向当前节点

            cur = next;//获取下次处理的节点
            listSize--;
        }
        return head;

        //左程云
//        int size = (int) (Math.random() * (len + 1));
//        if (size == 0) {
//            return null;
//        }
//        size--;
//        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
//        DoubleNode pre = head;
//        while (size != 0) {
//            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
//            pre.next = cur;
//            cur.last = pre;
//            pre = cur;
//            size--;
//        }
//        return head;


    }

    //获取单链表源顺序
    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    //检测单链表是否反转成功
    public static boolean checkLinkedListReverse(List<Integer> origin, Node head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //获取双链表源顺序
    public static List<Integer> getDoubleListOriginOrder(DoubleNode head) {
        List<Integer> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    //检测双链表是否反转成功
    public static boolean checkDoubleListReverse(List<Integer> origin, DoubleNode head) {
        DoubleNode end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.value)) {
                return false;
            }
            end = end.last;
        }
        return true;
    }

    // for test
    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("测试开始!");
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);
            List<Integer> list1 = getLinkedListOriginOrder(node1);
            node1 = reverseLinkedList(node1);
            if (!checkLinkedListReverse(list1, node1)) {
                System.out.println("Oops1!");
            }

            Node node2 = generateRandomLinkedList(len, value);
            List<Integer> list2 = getLinkedListOriginOrder(node2);
            node2 = testReverseLinkedList(node2);
            if (!checkLinkedListReverse(list2, node2)) {
                System.out.println("Oops2!");
            }

            DoubleNode node3 = generateRandomDoubleList(len, value);
            List<Integer> list3 = getDoubleListOriginOrder(node3);
            node3 = reverseDoubleList(node3);
            if (!checkDoubleListReverse(list3, node3)) {
                System.out.println("Oops3!");
            }

            DoubleNode node4 = generateRandomDoubleList(len, value);
            List<Integer> list4 = getDoubleListOriginOrder(node4);
            node4 = reverseDoubleList(node4);
            if (!checkDoubleListReverse(list4, node4)) {
                System.out.println("Oops4!");
            }

        }
        System.out.println("测试结束!");

    }

}
