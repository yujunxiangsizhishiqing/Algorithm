package Novice.c1;

public class Code6_PrintB {

    public static void print(int num){
        for(int i=31;i>=0;i--){
            System.out.print((num & (1<<i))==0?"0":"1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int num = (int)(Math.random()*50);
//        System.out.println(num);
//        print(num);

        int min = Integer.MIN_VALUE;
        System.out.println(min);
        print(min);
        int mmin = (~min+1);
        System.out.println(mmin);
    }
}
