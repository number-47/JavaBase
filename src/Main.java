import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("获取值："+ testFinally());

    }

    public static int testFinally(){
        int i = 0;
        try{
            i = 10;
            System.out.println("正常" + i);
            i = i / 0;
            return i;
        }catch (Exception e){
            i = 100;
            System.out.println("异常" + i);
            return i;
        }finally {
            i = 200;
            System.out.println("finally: " + i);
            return i;
        }
    }

    public static int minSubArrayLen(int s, int[] nums) {
        int length = nums.length;
        int[] sums = new int[length + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 0; i <= length; i++) {
            int target = s + sums[i];
            int index = Arrays.binarySearch(sums, target);
            if (index < 0) {
                index = ~index;
            }
            if (index <= length) {
                min = Math.min(index-i, min);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

   /* public static void main(String[] args) {
        int sums[] = new int[]{2,3,1,2,4,3};
        int s = 7;
//        System.out.println(minSubArrayLen(s, sums));
        Arrays.sort(sums);
        System.out.println(sums[2]);
    }*/


}
