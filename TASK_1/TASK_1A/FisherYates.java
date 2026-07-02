import java.util.Arrays;
import java.util.Random;

public class FisherYates{
    private static final Random random = new Random();
    public static void shuffle(int[] arr){ 
        if(arr == null){ return; }
        for(int i = arr.length - 1 ;i>0;i-- ){
            int r_index = random.nextInt(i + 1);
            
            int temp = arr[i];
            arr[i] = arr[r_index];
            arr[r_index] = temp;
        }
    }

    public static void main(String[] args){
        int[] arr ={1,2,3,4,5};
        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));
        shuffle(arr);
        System.out.println("After Fisher Yates Shuffle:");
        System.out.println(Arrays.toString(arr));
    }
}