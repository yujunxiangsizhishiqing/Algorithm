package train.c4;

/**
 * 小和问题:
 * 给定一个数组，对某个元素而言，所有在其左边且比它小的元素的和，我们称之为小和，求这个数组中所有元素的小和的和。
 *
 * 思路：
 * 小和问题其实就是统计每个元素（A）的右边有几个元素比它大，有几个就加上几个元素A的值。
 * 在归并排序过程中，小和的和就可以直接被累加出来，只需要在归并排序的逻辑中添加新的球会逻辑即可。
 * */
public class SmallSum {

    public static int SmallSum(int[] arr){
        if (arr==null||arr.length<2){
            return 0;
        }

        return Process(arr,0,arr.length-1);
    }

    public static int Process(int[]arr,int L,int R){
        if (arr==null||L>=R){
            return 0;
        }

        int M = L+((R-L)>>1);

        return Process(arr,L,M)+Process(arr,M+1,R)+Merge(arr,L,M,R);

    }

    public static int Merge(int[]arr,int L,int M,int R){
        if (arr==null||L>R){
            return 0;
        }

        int[] help = new int[R-L+1];
        int p1 = L;
        int p2 = M+1;
        int index=0;
        int res = 0;
        for (;p1<=M && p2<=R;){
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[index++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }

        for (;p1<=M;){
            help[index++] = arr[p1++];
        }
        for (;p2<=R;){
            help[index++] = arr[p2++];
        }

        for (int i = 0 ;i<help.length;i++){
            arr[L+i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if (SmallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}

