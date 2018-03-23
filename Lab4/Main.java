import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static double functionValue(double x) {
        return 30+Math.pow(x,2)-30*Math.cos(x);
    }
    static double df(double x) {
        return 2*x + 30*Math.sin(x);
    }

    public static void main(String[] args) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> yder = new ArrayList<>();

        int n = 13;
        //wezly rownoodlegle
        double dx = 8*Math.PI/(n-1);
        for(double i = -4*Math.PI; i<=4*Math.PI; i+=dx) {
            x.add(i);
            y.add(functionValue(i));
            yder.add(df(i));
        }
        //zera Czebyszewa
//        double mul = 4*Math.PI;
//        for(double j=0; j<n; ++j) {
//            x.add(Math.cos(Math.PI*((2*(j+1)-1)/(2*(double)n)))*mul);
//            y.add(functionValue(x.get((int)j)));
//            yder.add(df(x.get((int)j)));
//        }


        Lagrange l = new Lagrange(x, y, yder);
        //Newton newton = new Newton(x, y);

        ArrayList<Double> interpolatedx = new ArrayList<>();
        ArrayList<Double> interpolatedy = new ArrayList<>();

        double normSum=0;
        double maxDiff=0;

        for(double i = -4*Math.PI; i<=4*Math.PI; i += 0.01) {
            interpolatedx.add(i);
            interpolatedy.add(l.valueHermit(i));

            normSum += (Math.abs(functionValue(i)-l.valueHermit(i)));
            if(Math.abs(functionValue(i)-l.valueHermit(i)) > maxDiff) maxDiff = Math.abs(functionValue(i)-l.valueHermit(i));
        }
        System.out.println("Suma roznic: " + normSum);
        System.out.println("Max roznica: " + maxDiff);

        File f = new File("lagr.dat");
        //File f = new File("newt.dat");
        File f1 = new File("func.dat");
        File f2 = new File("pts.dat");
        try {
            f.createNewFile();
            f1.createNewFile();
            f2.createNewFile();
            FileWriter fw = new FileWriter(f);
            FileWriter fw1 = new FileWriter(f1);
            FileWriter fw2 = new FileWriter(f2);
            for(int i=0; i<interpolatedx.size(); ++i) {
                fw.write(interpolatedx.get(i)+" "+interpolatedy.get(i)+"\n");
                fw1.write(interpolatedx.get(i)+" "+functionValue(interpolatedx.get(i))+"\n");
            }
            for(int i=0; i<l.x.size(); ++i) {
                fw2.write(l.x.get(i)+" "+l.y.get(i)+"\n");
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
