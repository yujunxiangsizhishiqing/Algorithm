package Novice.c1;

public class Code7_Sort {

    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //选择排序
    public static void selectSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[i]){
                    swap(arr,i,j);
                }
            }
        }

    }
    //冒泡排序
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }

        for(int end = arr.length-1;end>=0;end--){
            for(int start=0;start<end;start++){
                if( arr[start]>arr[start+1]){
                    swap(arr,start,start+1);
                }
            }
        }
    }

    //插入排序
    public static void insertSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }

        for(int end=1;end<arr.length;end++){
            while((end-1>=0)&&(arr[end-1]>arr[end])){
                swap(arr,end,end-1);
                end--;
            }
        }

    }

    public static void Print(int[] arr){
        if(arr==null){
            return;
        }

        for(int i=0;i<arr.length-1;i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("选择排序");
        int arr[] = {5,4,8,0,1,3,3,3};
        Print(arr);
        selectSort(arr);
        Print(arr);

        System.out.println("冒泡排序");
        int arr2[] = {5,4,8,0,1,3,3,3};
        Print(arr2);
        bubbleSort(arr2);
        Print(arr2);

        System.out.println("插入排序");
        int arr3[] = {5,4,8,0,1,3,3,3};
        Print(arr3);
        insertSort(arr3);
        Print(arr3);
    }

}
