package train.c3;

/**
 * 数组实现队列
 *
 * 思路：循环数组
 * 给定一个limit长度的数组，用pushi和polli来记录入队和出队的位置；
 * 用size记录当前队列的数据量，一旦超过limit，就不可以再进行入队操作；
 *
 * 注：不要使用追赶的思路去解这道题，否则算法会非常低效且代码冗长！！！。
 * */
public class RingArray {
    private int[] arr;
    private int pushI;
    private int pollI;
    private int size;
    private int limit;

    public RingArray(int limit){
        pollI = 0;
        pushI = 0;
        size = 0;
        this.limit = limit;
        arr = new int[limit];
    }

    private int getNextIndex(int index){
        return (index+1>=limit)?0:index+1;
    }

    public void Push(int value){
        if (size>=limit){
            System.out.println("队列已满。不可再添加数据");
            return;
        }
        arr[pushI] = value;
        size++;

        pushI = getNextIndex(pushI);
    }

    public int Poll(){
        if (isEmpty()){
            //System.out.println("队列中没有数据");
            throw new RuntimeException("队列空了，不能再拿了");
        }
        int ans = arr[pollI];
        arr[pollI]=0;
        size--;

        pollI = getNextIndex(pollI);
        return ans;
    }

    public boolean isEmpty(){
        return size==0;
    }
}
