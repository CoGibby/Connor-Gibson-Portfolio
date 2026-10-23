public class Lec6 {
    public static int min(int a, int b){
        return a < b ? a : b;
    }
    public static double min(double a, double b){
        return a < b ? a : b;
    }
    public static double min(double a, double b, double c){
        return min( min(a,b), c);
    }
    public static void main(String[] args){
        int x = 5; 
        int y = 6; 
        // Minimium value of x and y 
        // int minVal = x < y ? x : y; 
        int minVal = min(x,y);
        System.out.println("min(" + x + ", " + y + ") = " + minVal);

        x = 2143; 
        y = 75; 
        minVal = min(x,y);
        System.out.println("min(" + x + ", " + y + ") = " + minVal);

        x = -4325; 
        y = -23432; 
        minVal = min(x,y);
        System.out.println("min(" + x + ", " + y + ") = " + minVal);

        System.out.println("min( 5.2134, 8.34252) = " + 
            min( 5.2134, 8.34252)); 
            
        System.out.println("min( 5, 8.34252) = " + 
            min( 5, 8.34252)); 
        
        System.out.println("min( -43.0, 532.0, 7879.234532) = " + 
            min( -43.0, 532.0, 7879.234532));
    }
}