import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


    static double countNorm(ArrayList<Double> y1, ArrayList<Double> y2) {
        double result = 0;
        for(int i=0; i<y1.size(); ++i) {
            //result += Math.pow(Math.pow((y1.get(i)-y2.get(i)),2), 0.5);
            result += Math.abs(y1.get(i)-y2.get(i));

        }
        return result/y1.size();
    }

//-----------------------------------------------EULER
//    public static void main(String[] args) {
////        final int N = 10000;
//        //double pts[][] = new double[N][2]; //pts[][0] - xn, pts[][1] - yn
//        ArrayList<Double> xn = new ArrayList<Double>();
//        ArrayList<Double> yn = new ArrayList<Double>();
//        Euler e = new Euler();
//
//        double x0 = -Math.PI/2; //dane
//        double y0 = e.analitycal_solution(-Math.PI/2); //dane
//        double h = 1e-2; //krok
//        xn.add(x0);
//        yn.add(y0);
//
//
//        //pts[0][0] = x0;
//        //pts[0][1] = y0;
//
////        for(int i=1; i<N; ++i) {
////            pts[i][0] = e.getNextX(pts[i-1][0], h);
////            pts[i][1] = e.getNextY(pts[i-1][0], pts[i-1][1], h);
////        }
//        boolean fl=false;
//        while(x0 <= Math.PI && !fl) {
//            xn.add(e.getNextX(xn.get(xn.size()-1), h));
//            yn.add(e.getNextY(xn.get(xn.size()-1), yn.get(yn.size()-1), h));
//            x0 += h;
//            if(x0>Math.PI) {
//                x0=Math.PI;
//                fl=true;
//            }
//        }
//
//        ArrayList<Double> fyn = new ArrayList<>();
//        for(int i=0; i<yn.size(); ++i) {
//            fyn.add(e.analitycal_solution(xn.get(i)));
//        }
//
//        File f = new File("euler.dat");
//        //File f1 = new File("func.dat");
//        try {
//            f.createNewFile();
//            //f1.createNewFile();
//            FileWriter fw = new FileWriter(f);
//            //FileWriter fw1 = new FileWriter(f1);
//            for(int i=0; i<xn.size(); ++i) {
//                //fw.write(pts[i][0]+" "+pts[i][1]+"\n");
//                fw.write(xn.get(i)+" "+yn.get(i)+"\n");
//                //fw1.write(xn.get(i)+" "+e.analitycal_solution(xn.get(i))+"\n");
//            }
//            fw.flush();
//            //fw1.flush();
//        } catch (IOException eq) {
//            eq.printStackTrace();
//        }
//
//        double norm = e.count_max_norm(xn, yn);
//        System.out.println("Max Norm: "+norm);
//        double n2 = countNorm(yn, fyn);
//        System.out.println("Xing Norm: "+n2);
//    }
//-------------------------------------------------------RUNGE-KUTTA
//    public static void main(String[] args) {
////        final int N = 10000;
////        double pts[][] = new double[N][2]; //pts[][0] - xn, pts[][1] - yn
//        Runge r = new Runge();
//
//        double x0 = -Math.PI/2; //dane
//        double y0 = r.analitycal_solution(-Math.PI/2); //dane
//        double h = 1e-5; //krok
////        pts[0][0] = x0;
////        pts[0][1] = y0;
//        ArrayList<Double> xn = new ArrayList<Double>();
//        ArrayList<Double> yn = new ArrayList<Double>();
//
//        xn.add(x0);
//        yn.add(y0);
//
////        for(int i=1; i<N; ++i) {
////            pts[i][0] = r.getNextX(pts[i-1][0], h);
////            pts[i][1] = r.getNextY(pts[i-1][0], pts[i-1][1], h);
////        }
//        while(x0 < Math.PI) {
//            xn.add(r.getNextX(xn.get(xn.size()-1), h));
//            yn.add(r.getNextY(xn.get(xn.size()-1), yn.get(yn.size()-1), h));
//            x0 += h;
//        }
//
//        File f = new File("rk.dat");
//        //File f1 = new File("func.dat");
//        try {
//            f.createNewFile();
//            //f1.createNewFile();
//            FileWriter fw = new FileWriter(f);
//            //FileWriter fw1 = new FileWriter(f1);
//            for(int i=0; i<xn.size(); ++i) {
//                //fw.write(pts[i][0]+" "+pts[i][1]+"\n");
//                fw.write(xn.get(i)+" "+yn.get(i)+"\n");
//                //fw1.write(xn.get(i)+" "+r.analitycal_solution(xn.get(i))+"\n");
//            }
//            fw.flush();
//            //fw1.flush();
//        } catch (IOException eq) {
//            eq.printStackTrace();
//        }
//
//        ArrayList<Double> fyn = new ArrayList<>();
//        for(int i=0; i<yn.size(); ++i) {
//            fyn.add(r.analitycal_solution(xn.get(i)));
//        }
//
//        double norm = r.count_max_norm(xn, yn);
//        System.out.println("Max Norm: "+norm);
//        double n2 = countNorm(yn, fyn);
//        System.out.println("Xing Norm: "+n2);
//    }
//---------------------------------------------------------------ZADANIE 2 ZAGADNIENIE BRZEGOWE
public static void main(String[] args) {
        final int N = 10000;
        double pts[][] = new double[N][2]; //pts[][0] - xn, pts[][1] - yn
        BoundaryValue b = new BoundaryValue(1e-3); //dlugosc kroku w konstruktorze



        ArrayList<Double> xn = b.xs;
        ArrayList<Double> yn = b.getYs();
        yn.add(0,0.0);
        yn.add(yn.size(), b.sol(2*Math.PI+5));

        ArrayList<Double> fyn = new ArrayList<>();

        for(double i : xn) {
            fyn.add(b.sol(i));
        }

        File f = new File("bound.dat");
        //File f1 = new File("func.dat");
        try {
            f.createNewFile();
            //f1.createNewFile();
            FileWriter fw = new FileWriter(f);
            //FileWriter fw1 = new FileWriter(f1);
            for(int i=0; i<xn.size(); ++i) {
                fw.write(xn.get(i)+" "+yn.get(i)+"\n");
                ////fw1.write(xn.get(i)+" "+fyn.get(i)+"\n");
            }
            fw.flush();
            //fw1.flush();
        } catch (IOException eq) {
            eq.printStackTrace();
        }

        double norm = b.count_max_norm(xn, yn);
        System.out.println("Max Norm: "+norm);
        double n2 = countNorm(yn, fyn);
        System.out.println("Xing Norm: "+n2);
    }
}
