import java.util.Arrays;
import java.util.Random;

public class NaiveShuffle
{
    private static final Random random = new Random();

    public static void shuffle(int[] array)
    {
        if(array == null)
        {
            return;
        }
        int n = array.length;
        for(int i = 0 ; i < n ; i++)
        {
            int randomIndex = random.nextInt(n);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));
        shuffle(arr);
        System.out.println("After Naive Shuffle:");
        System.out.println(Arrays.toString(arr));
    }
}