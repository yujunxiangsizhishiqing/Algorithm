package train.c1;

import java.util.Arrays;

/**
 * 在一个有序数组中，找到满足>=value的最左边的位置
 * */
public class BSNearLeft {

    // 在arr上，找满足>=value的最左位置
    public static int nearestIndex(int[] arr, int value) {
//        int L = 0;
//        int R = arr.length - 1;
//        int index = -1; // 记录最左的对号
//        while (L <= R) { // 至少一个数的时候
//            int mid = L + ((R - L) >> 1);
//            if (arr[mid] >= value) {
//                index = mid;
//                R = mid - 1;
//            } else {
//                L = mid + 1;
//            }
//        }
//        return index;


        if(arr==null||arr.length==0){
            return -1;
        }

        int left = 0;
        int right = arr.length-1;
        int index = -1;//始终记录这最贴近答案的位置，答案不存在则值为-1
        int mid = 0;

        for(;left<=right;){
            mid = left+(((right-left)/2)>>1);//（L+R）/2可能存在越界行为。
            if (arr[mid]<value){//有序数组的中间位置的值小于目标值，说明：左边不可能存在答案
                left = mid+1;
            }else{//有序数组的中间位置的值大于等于目标值，说明：目前的mid是最接近答案的，但需要继续二分，因为该位置的左边的值可能与mid的值相等
                index = mid;
                right = mid-1;
            }
        }
        return index;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
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
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != nearestIndex(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}
