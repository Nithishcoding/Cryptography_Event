import java.util.Scanner;
public class ExtendedEuclidean {
    public static int gcdExtended(int r1, int r2, int[] t) {
        if (r1 == 0) {
            t[0] = 0;
            t[1] = 1;
            return r2;
        }
        int[] temp = new int[2];
        int gcd = gcdExtended(r2 % r1, r1, temp);
        t[0] = temp[1] - (r2 / r1) * temp[0];
        t[1] = temp[0];
        return gcd;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter r1: ");
        int r1 = sc.nextInt();
        System.out.print("Enter r2: ");
        int r2 = sc.nextInt();        
        int[] t = new int[2];        
        int g = gcdExtended(r1, r2, t);       
        System.out.println("GCD: " + g + ", t1: " + t[0] + ", t2: " + t[1]);     
        sc.close();
    }
}

/*Enter r1: 35
Enter r2: 50
 */
/*output:GCD: 5, t1: 3, t2: -2
 */