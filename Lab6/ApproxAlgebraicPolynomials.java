import java.util.ArrayList;

/**
 * Created by Maciek on 2016-05-21.
 */
public class ApproxAlgebraicPolynomials {

    int n; //stopien wielomianu
    ArrayList<Double> coeffs;

    ArrayList<Double> xs;
    ArrayList<Double> ys;
    int k; //liczba wezlow
    double jump;


    double f(double x) {
        return 30 + Math.pow(x,2) - 30*Math.cos(x);
    }

    public ApproxAlgebraicPolynomials(int n, int k) {
        this.n = n;

        this.xs = new ArrayList<>();
        this.ys = new ArrayList<>();

        this.k = k;
        this.jump = 8*Math.PI / ((double)k-1);

        for(int i=0; i<k; ++i) {
            this.xs.add(-4*Math.PI + i*jump);
            this.ys.add(f(xs.get(xs.size()-1)));
        }
        this.coeffs = fillCoeffs();
    }

    private ArrayList<Double> fillCoeffs() {
        double A[][] = new double[n + 1][n + 1];
        double b[] = new double[n + 1];

        for (int i = 0; i < n + 1; ++i) {
            double ti = 0;
            for (int j = 0; j < xs.size(); ++j) ti += ys.get(j) * Math.pow(xs.get(j), i);
            b[i] = ti;
        }

        for(int i=0; i<n+1; ++i) {
            for(int j=0; j<n+1; ++j) {
                A[i][j] = 0;
            }
        }
        for(int i=0; i<n+1; ++i) {
            for(int j=0; j<n+1; ++j) {
                for(int k=0; k<xs.size(); ++k) A[i][j] += Math.pow(xs.get(k),i+j);
            }
        }

        GaussianElimination g = new GaussianElimination();
        double coeffs[] = g.lsolve(A,b);
        ArrayList<Double> solution = new ArrayList<>();
        for(int i=0; i<n+1; ++i) solution.add(coeffs[i]);

        return solution;
    }

    double polyValue(ArrayList<Double> a, double x) {
        double val = 0;
        for(int i=a.size()-1; i>=0; --i) val += Math.pow(x, i)*a.get(i);
        return val;
    }
}
