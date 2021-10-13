package train.c1;

/**
 * 局部最小值的问题
 */
public class BSAwesome {
    public static int getLessIndex(int[] arr) {
        //初始条件
        int index = -1;
        if(arr==null||arr.length==0){
            return index;//no exist
        }

        if(arr.length==1||arr[0]<arr[1]){
            return index = 0;//数组最左边的就满足局部最小值
        }

        if (arr[arr.length-1]<arr[arr.length-2]){
            return index = arr.length-1;//数组的最右边满足局部最小值
        }

        //func1
//        for(int i = 1; i < arr.length-2 ; i++){
//            if((arr[i]<arr[i-1])&&(arr[i]<arr[i+1])){
//                return index = i;
//            }
//        }

        //左程云
//        int left = 1;
//        int right = arr.length - 2;
//        int mid = 0;
//        while (left < right) {
//            mid = (left + right) / 2;
//            if (arr[mid] > arr[mid - 1]) {
//                right = mid - 1;
//            } else if (arr[mid] > arr[mid + 1]) {
//                left = mid + 1;
//            } else {
//                return mid;
//            }
//        }
//        return left;

        //func3
        int left = 0;
        int right = arr.length-2;
        int mid = 0;

        for (;left<=right;){
            mid = left+(((right-left)/2)>>1);
            if(arr[mid]>arr[mid-1]){
                right = mid-1;
            }else if(arr[mid]>arr[mid+1]){
                left = mid+1;
            }else{
                return index = mid;
            }
        }
        return index;
    }
}
