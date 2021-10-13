package train.c3;


/**
 * 删除链表中值等于value的节点
 * */
public class DeleteGivenValue {

    public static class Node{
        public int value;
        public Node next;

        public Node(int value){
            this.value = value;
        }
    }


    public static Node RemoveValue(Node head,int value){

        if (head==null){
            return head;
        }

        Node cur = null;
        for (;head != null;){

            if (head.value != value){
                if (cur == null) {
                    cur = head;
                } else {
                    cur.next = head;
                    cur = cur.next;
                }
            }
            head = head.next;
        }

        return cur;
    }

}
