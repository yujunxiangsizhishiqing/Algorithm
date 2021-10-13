package train.c4;

/**
 * 实现归并排序
 * */
public class MergeSort {

    //用递归实现
    public static void MergeSort1(int[] arr){
        if (arr == null||arr.length<2){
            return ;
        }

        Process(arr,0,arr.length-1);
    }

    //递归调用，将数组分为左右两部分
    // 请把arr[L..R]排有序
    // l...r N
    // T(N) = 2 * T(N / 2) + O(N)
    // O(N * logN)
    public static void Process(int[] arr,int L,int R){
        if (arr==null||L>=R){
            return;
        }

        int M = 0;
        //M = (L+R)/2;
        M = L+((R-L)>>1);
        Process(arr,L,M);
        Process(arr,M+1,R);
        Merge(arr,L,M,R);
    }

    //排序过程，通过比较左数组和右数组的大小，将数导入辅助数组中，待排序完成后，将辅助数组原样复制到原数组中
    public static void Merge(int[] arr,int L,int M,int R){
        if (arr==null||L>R){
            return;
        }

        int[] help = new int[R-L+1];
        int index = 0;
        int p1 = L;
        int p2 = M+1;

        for (;((p1<=M)&&(p2<=R));){
//            help[index] = arr[p1]<arr[p2]?arr[p1]:arr[p2];
//            index++;
//            p1++;
//            p2++;
            help[index++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }

        for (;p1<=M;){
            help[index++] = arr[p1++];
        }
        for (;p2<=R;){
            help[index++] = arr[p2++];
        }

        for (int i = 0;i<help.length;i++){
            arr[L+i] = help[i];
        }

        //左程云
//        int[] help = new int[R - L + 1];
//        int i = 0;
//        int p1 = L;
//        int p2 = M + 1;
//        while (p1 <= M && p2 <= R) {
//            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
//        }
//        // 要么p1越界了，要么p2越界了
//        while (p1 <= M) {
//            help[i++] = arr[p1++];
//        }
//        while (p2 <= R) {
//            help[i++] = arr[p2++];
//        }
//        for (i = 0; i < help.length; i++) {
//            arr[L + i] = help[i];
//        }
    }


    //非递归实现，迭代
    public static void MergeSort2(int[] arr){
        if (arr==null||arr.length<2){
            return;
        }

        int N = arr.length;
        int mergeSize = 1;//1,2,4,8,16,32,....2^n;
        for (;mergeSize<N;){
            int L = 0;
            for(;L<N;){
                if (mergeSize>=N-L){
                    break;
                }
                int M = L+mergeSize-1;
                //int R = M+Math.min(mergeSize,N-M-1);
                int R = Math.min(M+mergeSize,N-1);
                Merge(arr,L,M,R);
                L=R+1;
            }
            if (mergeSize>N/2){
                break;
            }
            mergeSize<<=1;
        }

        //左程云
//        if (arr == null || arr.length < 2) {
//            return;
//        }
//        int N = arr.length;
//        // 步长
//        int mergeSize = 1;
//        while (mergeSize < N) { // log N
//            // 当前左组的，第一个位置
//            int L = 0;
//            while (L < N) {
//                if (mergeSize >= N - L) {
//                    break;
//                }
//                int M = L + mergeSize - 1;
//                int R = M + Math.min(mergeSize, N - M - 1);
//                Merge(arr, L, M, R);
//                L = R + 1;
//            }
//            // 防止溢出
//            if (mergeSize > N / 2) {
//                break;
//            }
//            mergeSize <<= 1;
//        }
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
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            MergeSort1(arr1);
            MergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
