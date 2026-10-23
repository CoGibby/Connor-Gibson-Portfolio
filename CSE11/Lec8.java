public class Lec8{
    public static void swap(int a, int b){
        // TODO
        System.out.println("Start of swap method");
        System.out.println("a = " + a + ", b = b" + b);
        int temp = a;
        a = b; 
        b = temp; 
        System.out.println("a = " + a + ", b = b" + b);
        System.out.println("End of swap method");
    }

    public static void main(String[] args){
        int x = 20;
        System.out.println("x = " + x);

        System.out.println();
        int[] arr = {43215, 7634, 90134, 432, 4325, 97866}; 
        for (int i = 0; i < arr.length; ++i){
            System.out.println("arr[" + i + "] = " + arr[i]);
        }
        System.out.println("arr = " + arr);
        
        System.out.println();
        int[] arr1 = null;
        System.out.println("arr1 = " + arr1);
        for (int i = 0; i < arr1.length; ++i){
            System.out.println(arr[i]);
        }
        int y = 43; 

        System.out.println("x = " + x + ", y = " + y);

    }
}