

public class Main {

    public static void main(String[] args) {
            SecantMethod s = new SecantMethod();

            double a = -0.6;
            double b = 2.4;
            double e = 1e-20;
            int n = -1;
            double dx = 1;
            System.out.println("DRUGIE KRYTERIUM STOPU\nRO = "+e);
                while(b >= a) {
                    double root = s.find(a, b, e, n, dx);
                    System.out.print("Dla a = "+a+" b = "+b+" root = ");
                    System.out.println(root);
                    b -= 0.1;
                }
                b = 2.4;
                System.out.println("\n\n");
                while(a <= b) {
                    double root = s.find(a, b, e, n, dx);
                    System.out.print("Dla a = "+a+" b = "+b+" root = ");
                    System.out.println(root);
                    a += 0.1;
                }
//            double root = s.find(a, b, e, n, dx);
//            System.out.println(root);

//            NewtonMethod n = new NewtonMethod();
//            double a = -0.6;
//            double b = 2.4;
//            double e = 1e-20;
//            System.out.println("PIERWSZE KRYTERIUM STOPU\n\n");
//            System.out.println("RO = "+e);
//            while(a <= b) {
//                double root = n.find(a, e);
//                System.out.print("Dla a = "+a+" root = ");
//                System.out.println(root);
//                a += 0.1;
//            }
    }
}
