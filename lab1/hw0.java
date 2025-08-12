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

    public static void windowPosSum(int[] a, int n) {
        int l = a.length;
        for(int i=0; i<l; i++){
            if(a[i] > 0){
                int sum = a[i];
                for(int j=1; j<=n && (i+j) < l; j++){
                    sum += a[i+j];
                }
                a[i] = sum;
            }
        }
        
    }

    public static void main(String[]args){
        drawTriangle(5);
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};      
        System.out.println(max(numbers));
        
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));

    }
}
