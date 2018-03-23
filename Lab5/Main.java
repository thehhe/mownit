import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static double countNorm(ArrayList<Double> y1, ArrayList<Double> y2) {
        double result = 0;
        for(int i=0; i<y1.size(); ++i) {
            result += Math.pow(Math.pow((y1.get(i)-y2.get(i)),2), 0.5);
        }
        return result;
    }

    public static void main(String[] args) {
//        CubicSpline c = new CubicSpline(15);
//        c.countZ(true); //true==spline naturalny, false==spline paraboliczny
//        //c.countZ(false); //true==spline naturalny, false==spline paraboliczny
//        c.fillPol();
//
//        ArrayList<Double> xs = new ArrayList<>();
//        ArrayList<Double> ys = new ArrayList<>();
//        ArrayList<Double> fys = new ArrayList<>();
//
//        double start = -4*Math.PI;
//        for(int i=0; i<c.nrOfNodes-1; ++i) {
//            for(double j=start+i*c.jump; j<=start+(i+1)*c.jump; j+=0.01) {
//                xs.add(j);
//                //ys.add(c.makePolynomial(c.pol[i][0], c.pol[i][1], c.pol[i][2], c.pol[i][3], j));
//                ys.add(c.pol[i][0]*Math.pow((j-c.x.get(i)),3) + c.pol[i][1]*Math.pow((j-c.x.get(i)),2) + c.pol[i][2]*(j-c.x.get(i)) + c.pol[i][3]);
//                fys.add(c.f(j));
//            }
//        }
//
//        File f = new File("spline.dat");
//        //File f = new File("newt.dat");
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
//                fw.write(xs.get(i)+" "+ys.get(i)+"\n");
//                fw1.write(xs.get(i)+" "+fys.get(i)+"\n");
//            }
//            for(int i=0; i<c.nrOfNodes; ++i) {
//                fw2.write(c.x.get(i)+" "+c.y.get(i)+"\n");
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
//
//        c.countZ(false); //true==spline naturalny, false==spline paraboliczny
//        c.fillPol();
//
//        ArrayList<Double> xs1 = new ArrayList<>();
//        ArrayList<Double> ys1 = new ArrayList<>();
//        ArrayList<Double> fys1 = new ArrayList<>();
//
//        for(int i=0; i<c.nrOfNodes-1; ++i) {
//            for(double j=start+i*c.jump; j<=start+(i+1)*c.jump; j+=0.01) {
//                xs1.add(j);
//                //ys.add(c.makePolynomial(c.pol[i][0], c.pol[i][1], c.pol[i][2], c.pol[i][3], j));
//                ys1.add(c.pol[i][0]*Math.pow((j-c.x.get(i)),3) + c.pol[i][1]*Math.pow((j-c.x.get(i)),2) + c.pol[i][2]*(j-c.x.get(i)) + c.pol[i][3]);
//                fys1.add(c.f(j));
//            }
//        }
//        File fs = new File("spline1.dat");
//        try {
//            fs.createNewFile();
//            FileWriter fws = new FileWriter(fs);
//            for(int i=0; i<xs.size(); ++i) {
//                fws.write(xs1.get(i)+" "+ys1.get(i)+"\n");
//            }
//            fws.flush();
//            fws.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//**********************QUADRATIC
        QuadraticSpline q = new QuadraticSpline(100);
        q.fillMatrices();
        GaussianElimination g = new GaussianElimination();

        double[] coeffs = g.lsolve(q.A, q.b);

        double[][] wspl = new double[q.nrOfNodes-1][3];
        for(int i=0; i<q.nrOfNodes-1; ++i) {
            wspl[i][0] = coeffs[3*i];
            wspl[i][1] = coeffs[3*i+1];
            wspl[i][2] = coeffs[3*i+2];
        }
        ArrayList<Double> xs = new ArrayList<>();
        ArrayList<Double> ys = new ArrayList<>();
        ArrayList<Double> fys = new ArrayList<>();

        double start = -4*Math.PI;
        for(int i=0; i<q.nrOfNodes-1; ++i) {
            for(double j=start+i*q.jump; j<=start+(i+1)*q.jump; j+=0.01) {
                xs.add(j);
                ys.add(q.makePolynomial(wspl[i][0], wspl[i][1], wspl[i][2], j));
                //ys.add(q.pol[i][0]*Math.pow((j-q.x.get(i)),3) + q.pol[i][1]*Math.pow((j-q.x.get(i)),2) + q.pol[i][2]*(j-q.x.get(i)) + q.pol[i][3]);
                fys.add(q.f(j));
            }
        }
        File f = new File("splineq.dat");
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
            for(int i=0; i<xs.size(); ++i) {
                fw.write(xs.get(i)+" "+ys.get(i)+"\n");
                fw1.write(xs.get(i)+" "+fys.get(i)+"\n");
            }
            for(int i=0; i<q.nrOfNodes; ++i) {
                fw2.write(q.x.get(i)+" "+q.y.get(i)+"\n");
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

        double norm = countNorm(ys, fys);
        System.out.println(norm);

    }
}
