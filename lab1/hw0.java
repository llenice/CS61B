public class hw0 {
    public static void drawTriangle(int n){
        int rows = n;
        for(int i=0; i<rows; i++){
            for(int j=0; j<=i; j++){
                System.out.print("*");

            }
            System.out.println();
        }        
    }

    public static int max(int []m){
        int max_v = m[0];
        for(int i=1; i< m.length; i++){
            if(m[i] > max_v){
                max_v = m[i];
            }
        }
        return max_v;
    }

    public static void main(String[]args){
        drawTriangle(5);
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};      
        System.out.println(max(numbers));

    }
}
