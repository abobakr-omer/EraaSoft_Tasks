package sheet1;

import java.util.Scanner;

public class T_SortNumbers {
    public static void main(String[] args) {
        int[] arr=new int[3];
        int[] arr1=new int[3];
        Scanner scanner=new Scanner(System.in);

        arr[0]=scanner.nextInt();
        arr[1]=scanner.nextInt();
        arr[2]=scanner.nextInt();

        for (int i=0;i<arr.length;i++){
            arr1[i]=arr[i];
        }

        int max=0,last=arr.length-1,temp=0;

        for (int j=0;j<arr.length;j++){
            max=0;
            for (int i=0;i<=last;i++){
                if (arr[i]>arr[max])
                {
                    max=i;
                }
            }
            temp=arr[max];
            arr[max]=arr[last];
            arr[last]=temp;
            last--;
        }


        for (int i:arr){
            System.out.println(i);
        }
        System.out.println();
        for (int i:arr1){
            System.out.println(i);
        }



    }

}
