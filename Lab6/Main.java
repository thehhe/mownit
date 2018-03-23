import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        ApproxAlgebraicPolynomials a = new ApproxAlgebraicPolynomials(10, 50);
//
//        ArrayList<Double> xs = new ArrayList<>();
//        ArrayList<Double> fys = new ArrayList<>();
//
//        ArrayList<Double> pys = new ArrayList<>();
//
//        for(double i=-4*Math.PI; i<= 4*Math.PI; i+=0.01) {
//            xs.add(i);
//            fys.add(a.f(i));
//            pys.add(a.polyValue(a.coeffs, i));
//        }
//
//        File f = new File("appx.dat");
//        File f1 = new File("func.dat");
//        File f2 = new File("pts.dat");
//        try {
//            f.createNewFile();
//            f1.createNewFile();
//            f2.createNewFile();
//            FileWriter fw = new FileWriter(f);
//            FileWriter fw1 = new FileWriter(f1);
//            FileWriter fw2 = new FileWriter(f2);
//            for(int i=0; i<xs.size(); ++i) {
//                fw.write(xs.get(i)+" "+pys.get(i)+"\n");
//                fw1.write(xs.get(i)+" "+fys.get(i)+"\n");
//            }
//            for(int i=0; i<a.xs.size(); ++i) {
//                fw2.write(a.xs.get(i)+" "+a.ys.get(i)+"\n");
//            }
//
//            fw.flush();
//            fw1.flush();
//            fw2.flush();
//            fw.close();
//            fw1.close();
//            fw2.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  TRIGONOMETRIC
        ApproxTrignonometricPolynomial a = new ApproxTrignonometricPolynomial(15, 10); //stopien, liczba wezlow

        ArrayList<Double> xs = new ArrayList<>();
        ArrayList<Double> fys = new ArrayList<>();
        ArrayList<Double> pys = new ArrayList<>();

        for(double i=-4*Math.PI; i<= 4*Math.PI; i+=0.01) {
            xs.add(i);
            fys.add(a.f(i));
            pys.add(a.polyValueEvenFunction(i, a.as, a.bs));
        }

        File f = new File("appxtr.dat");
        File f1 = new File("func.dat");
        File f2 = new File("pts.dat");
        try {
            f.createNewFile();
            f1.createNewFile();
            f2.createNewFile();
            FileWriter fw = new FileWriter(f);
            FileWriter fw1 = new FileWriter(f1);
            FileWriter fw2 = new FileWriter(f2);
            for(int i=0; i<xs.size(); ++i) {
                fw.write(xs.get(i)+" "+pys.get(i)+"\n");
                fw1.write(xs.get(i)+" "+fys.get(i)+"\n");
            }
            for(int i=0; i<a.xs.size(); ++i) {
                fw2.write(a.xs.get(i)+" "+a.ys.get(i)+"\n");
            }

            fw.flush();
            fw1.flush();
            fw2.flush();
            fw.close();
            fw1.close();
            fw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
