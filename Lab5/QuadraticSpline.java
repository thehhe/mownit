import java.util.ArrayList;

/**
 * Created by Maciek on 2016-05-18.
 */
public class QuadraticSpline {
    double f(double x) {
        return 30 + Math.pow(x,2) - 30*Math.cos(x);
    }

    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    int nrOfNodes;
    double jump;

    double A[][];
    double b[];
    public  QuadraticSpline(int nrOfNodes) {
        this.nrOfNodes = nrOfNodes;
        this.jump = 8*Math.PI / ((double)nrOfNodes-1); //we wzorze oznaczone przez h
        //for(double i=-4*Math.PI; i<=4*Math.PI; i+=jump) {
        for(int i=0; i<nrOfNodes; i++) {
            this.x.add(-4*Math.PI+i*jump);
            this.y.add(f(x.get(x.size()-1)));
        }
        this.A = new double[3*(nrOfNodes-1)][3*(nrOfNodes-1)];
        this.b = new double[3*(nrOfNodes-1)];
    }



    void fillMatrices() {
        for(int i=0; i<nrOfNodes-1; ++i) {
            A[2*i][3*i] = Math.pow(x.get(i),2);
            A[2*i+1][3*i] = Math.pow(x.get(i+1),2);
            A[2*i][3*i+1] = x.get(i);
            A[2*i+1][3*i+1] = x.get(i+1);
            A[2*i][3*i+2] = 1;
            A[2*i+1][3*i+2] = 1;
            b[2*i] = y.get(i);
            b[2*i+1] = y.get(i+1);
        }
        int j=0;
        for(int i=2*nrOfNodes-2; i<3*(nrOfNodes-1)-1; ++i) {
            A[i][3*j] = 2*x.get(j);
            A[i][3*j+1] = 1;
            A[i][3*j+2] = 0;
            A[i][3*j+3] = -2*x.get(j);
            A[i][3*j+4] = -1;
            A[i][3*j+5] = 0;
            b[i] = 0;
            j++;
        }
        A[3*(nrOfNodes-1)-1][0] = 1;
        b[3*(nrOfNodes-1)-1] = 0;
    }

    double makePolynomial(double a, double b, double c,  double x) {
        return a*Math.pow(x,2) + b*x + c;
    }

}
