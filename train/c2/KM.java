package train.c2;

import java.util.HashMap;
import java.util.HashSet;

/**
 *一个数组中有一种数出现K次，其他数都出现了M次，且M>1,K<M，找到出现了K次的数。
 * 要求：额外空间复杂度为O(1)，即不可以使用哈希表.
**/
public class KM {

    //经典解法，哈希表
    public static int test(int[] arr,int k , int m){
        HashMap<Integer,Integer> map= new HashMap<>();
        for (int num:arr){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }

        for (int num : map.keySet()){
            if (map.get(num)==k){
                return num;
            }
        }
        return -1;
    }

    //解法，异或
    public static int onlyKTimes(int[] arr, int k, int m) {
        int intByteNum = 32;
        int[] p = new int[intByteNum];

        for(int i = 0; i<arr.length;i++){
            for (int j = 0;j<intByteNum;j++){
//                if(((arr[i]>>j)&1 )!= 0){
////                    p[j]++;
////                }
                p[j] += ((arr[i]>>j)&1 );
            }
        }

        int ans = 0;
        for (int j = 0;j<intByteNum;j++){
            if(p[j]%m != 0){
                ans |= (1<<j);
            }
        }
        return ans;
    }

    //制作题目中用来测试的数组
    public static int[] generateRandomArray(int maxKinds,int range ,int k,int m){

        //生成一个随机数,
        int kNum = generateRangeNumber(range);
        //生成数组中最多有几种数，但不能小于两种。否则题目无意义，故+2。
        int numKinds = (int)(Math.random()*maxKinds)+2;
        int arrLen = k+(numKinds-1)*m;
        int[] arr  = new int[arrLen];

        //将出现了K次的数塞入到数组中
        int index = 0;
        for(;index<k;){
            arr[index++] = kNum;
        }
        numKinds--;

        //这里创建哈希表的目的是：确保每次生成的随机数是各不相等的，否则会破坏题目条件
        HashSet<Integer> set = new HashSet<>();
        set.add(kNum);
        while (numKinds!=0){
            int mNum = 0;
            //见上述哈希表的目的
            do {
                mNum = generateRangeNumber(range);
            }while(set.contains(mNum));
            set.add(mNum);
            for (int i = 0 ;i< m ;i++){
                arr[index++] = mNum;
            }
            numKinds--;
        }
        //到这里我们生成了一个规律的数组，即每个相同的数在数组中都是连在一起的
        //我们可以打乱他们的顺序
        for (int i = 0 ;i<arr.length;i++){
            //第i位置的数，我们和第j位置交换，j随机生成。
            int j = (int)(Math.random()*arr.length);//[0,arr.length-1]
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    //在[-maxValue,maxValue]范围生成一个随机数
    public static int generateRangeNumber(int maxValue){
        return ((int)(Math.random()*(maxValue+1)))-((int)(Math.random()*(maxValue+1)));
    }

    public static void main(String[] args) {
//        int arr[] = {4,3,1,3,3,1,1,4,4,-1,-1,-1,2,2};
//        int k = 2;
//        int m = 3;
//        System.out.println(test(arr,k,m));
//        System.out.println(onlyKTimes(arr,k,m));

        int maxKinds = 4;//数组里最多有几种数,不能小于2
        int range = 200;//数组中的值的范围
        int testTime = 100000;//测试次数
        int max = 9;//参数k和参数m通过这个值制作出来。

        System.out.println("测试开始");
        for(int i = 0 ;i< testTime;i++){
            /*
            * 根据max随机生成两个数a,b。
            * 其中较小的值给参数k；
            * 较大的值给参数m；
            * 如果出现出现参数k和参数m相等的情况，参数m+1即可
            * */
            int a = (int)(Math.random()*max)+1;//1~9
            int b = (int)(Math.random()*max)+1;//1~9
            int k = Math.min(a,b);
            int m = Math.max(a,b);
            if(k==m){
                m++;
            }
            //生成测试数组
            int[] arr = generateRandomArray(maxKinds,range,k,m);
            //测试结果
            int ans1 = test(arr,k,m);
            int ans2 = onlyKTimes(arr,k,m);

            if (ans1 != ans2){
                System.out.println("ans1:"+ans1+" "+"ans2:"+ans2+" "+"你出错了"+"arrLen:"+arr.length+" "+k+" "+m);
                for (int j = 0 ; j <arr.length;j++){
                    System.out.print(arr[j]+" ");
                }
                System.out.println();
                return;
            }

        }
        System.out.println("测试结束");
    }
}
