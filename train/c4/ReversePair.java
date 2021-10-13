package train.c4;

/**
 *数组中的逆序对:
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 思路：
 * 该题与求小和的思路一致，只是在逻辑上有部分不同。
 * 求小和问题相当于求出在数组中元素A的右边有几个比元素A大的逻辑；
 * 逆序对问题相当于求出在数组中元素A的右边有几个比元素A小的逻辑；
 * 在归并排序的基础上，注意边界条件即可。
 * */
public class ReversePair {

    public static int ReversePairNum(int[] arr){

        if (arr==null||arr.length<2){
            return 0;
        }

        return process(arr,0,arr.length-1);
    }

    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    // 左 排序 merge并产生逆序对数量
    // 右 排序 merge并产生逆序对数量
    public static int process(int[] arr,int l ,int r){

        if (arr==null||l==r){//base case
            return 0;
        }

        int m = l+((r-l)>>1);
        return process(arr,l,m) + process(arr,m+1,r) + merge(arr,l,m,r);
    }

    public static int merge(int[] arr,int l,int m,int r){
        if (arr==null||l>r){
            return 0;
        }

        int[] help = new int[r-l+1];
        int index = help.length-1;
        int p1 = m;
        int p2 = r;
        int res = 0;

        for (;p1>=l && p2>=m+1;){
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }


        for (;p1>=l;){
            help[index--] = arr[p1--];
        }

        for (;p2>=m+1;){
            help[index--] = arr[p2--];
        }

        for (int i = 0;i<help.length;i++){
            arr[l+i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
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
            if (ReversePairNum(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
