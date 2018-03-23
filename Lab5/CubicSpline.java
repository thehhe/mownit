import java.util.ArrayList;

/**
 * Created by Maciek on 2016-05-18.
 */
public class CubicSpline {
    double f(double x) {
        return 30 + Math.pow(x,2) - 30*Math.cos(x);
    }

    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    int nrOfNodes;
    double jump;

    double z[];

    public CubicSpline(int nrOfNodes) {
        this.nrOfNodes = nrOfNodes;
        this.jump = 8*Math.PI / ((double)nrOfNodes-1); //we wzorze oznaczone przez h
        //for(double i=-4*Math.PI; i<=4*Math.PI; i+=jump) {
        for(int i=0; i<nrOfNodes; i++) {
            this.x.add(-4*Math.PI+i*jump);
            this.y.add(f(x.get(x.size()-1)));
        }
        this.pol = new double[nrOfNodes-1][4];
        this.z = new double[nrOfNodes];


    }

    public void countZ(boolean mode) { //true==spline naturalny, false==spline paraboliczny
        if(mode) {
            z[0] = 0;
            z[nrOfNodes-1] = 0;

            double A[][] = new double[nrOfNodes-2][3];
            double b[] = new double[nrOfNodes-2];
            double x[] = new double[nrOfNodes-2];

            for(int i=0; i<nrOfNodes-2; ++i) {
                    A[i][0] = 1;
                    A[i][1] = 4;
                    A[i][2] = 1;
                    b[i] = (y.get(i) - 2*y.get(i+1) + y.get(i+2))*6/Math.pow(jump,2);
            }
            A[0][0] = 0;
            A[nrOfNodes-3][2] = 0;

            for(int i=1; i<nrOfNodes-2; ++i) {
                double m = A[i][0]/A[i-1][1];
                A[i][1] -= m*A[i-1][2];
                b[i] -= m*b[i-1];
            }
            x[nrOfNodes-3] = b[nrOfNodes-3]/A[nrOfNodes-3][1];
            for(int i=nrOfNodes-4; i>=0; --i) x[i] = (b[i] - A[i][2]*x[i+1])/A[i][1];
            for(int i=1; i<nrOfNodes-1; ++i) z[i] = x[i-1];
        }
        else {
            //z[0] = 0;
            //z[nrOfNodes-1] = 0;

            double A[][] = new double[nrOfNodes-2][3];
            double b[] = new double[nrOfNodes-2];
            double x[] = new double[nrOfNodes-2];

            for(int i=0; i<nrOfNodes-2; ++i) {
                A[i][0] = 1;
                A[i][1] = 4;
                A[i][2] = 1;
                b[i] = (y.get(i) - 2*y.get(i+1) + y.get(i+2))*6/Math.pow(jump,2);
            }
            A[0][0] = 0;
            A[0][1] = 5;
            A[nrOfNodes-3][2] = 0;
            A[nrOfNodes-3][1] = 5;


            for(int i=1; i<nrOfNodes-2; ++i) {
                double m = A[i][0]/A[i-1][1];
                A[i][1] -= m*A[i-1][2];
                b[i] -= m*b[i-1];
            }
            x[nrOfNodes-3] = b[nrOfNodes-3]/A[nrOfNodes-3][1];
            for(int i=nrOfNodes-4; i>=0; --i) x[i] = (b[i] - A[i][2]*x[i+1])/A[i][1];
            for(int i=1; i<nrOfNodes-1; ++i) z[i] = x[i-1];

            z[0]=z[1];
            z[nrOfNodes-1]=z[nrOfNodes-2];
        }
    }

    double pol[][];// = new double[nrOfNodes-1][4]; //[0]-a, [1]-b, [2]-c, [3]-d
    void fillPol() {
        for(int i=0; i<nrOfNodes-1; ++i) {
            this.pol[i][0] = (z[i+1]-z[i])/(6*jump);
            this.pol[i][1] = z[i]/2;
            this.pol[i][2] = ((y.get(i+1)-y.get(i))/jump) - (2*z[i] + z[i+1])*jump/6;
            this.pol[i][3] = y.get(i);
        }
    }

    double makePolynomial(double a, double b, double c, double d, double x) {
        return a*Math.pow(x,3) + b*Math.pow(x,2) + c*x + d;
    }
}
