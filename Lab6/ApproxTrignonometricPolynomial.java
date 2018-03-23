import java.util.ArrayList;

/**
 * Created by Maciek on 2016-05-21.
 */
public class ApproxTrignonometricPolynomial {

    ArrayList<Double> xs; //punkterki
    ArrayList<Double> ys;

    ArrayList<Double> as; //a = 0,1,2,...
    ArrayList<Double> bs; //b - 1,2,...

    int n; //stopien wielomianu
    int k; //liczba wezlow
    double jump;

    double a0;

    double f(double x) {
        return 30 + Math.pow(x,2) - 30*Math.cos(x);
    }

    public ApproxTrignonometricPolynomial(int n, int k) {
        this.n = n;
        this.k = k;

        this.xs = new ArrayList<>();
        this.ys = new ArrayList<>();
        this.jump = 8*Math.PI / ((double)k-1);

        for(int i=0; i<k; ++i) {
            this.xs.add(-4*Math.PI + i*jump);
            this.ys.add(f(xs.get(xs.size()-1)));
        }

        this.a0 = a0*2/(n+1);

        this.as = countAs();
        this.bs = countBs();
    }

    ArrayList<Double> countAs() {
        ArrayList<Double> result = new ArrayList<>();
        for(int i=0; i<n+1; ++i) {
            double tmp = 0;
            for(int j=0; j<k; ++j) tmp += ys.get(j)*Math.cos(i*j*2*Math.PI/(double)k);
            result.add(2*tmp/(double)k);
        }
        return result;
    }

    ArrayList<Double> countBs() {
        ArrayList<Double> result = new ArrayList<>();
        result.add(0, 0.0);
        for(int i=1; i<n+1; ++i) {
            double tmp = 0;
            for(int j=0; j<k; ++j) tmp += ys.get(j)*Math.sin(i*xs.get(j));
            result.add(2*tmp/(double)k);
        }
        return result;
    }

    double polyValue(double x, ArrayList<Double> a, ArrayList<Double> b) {
        double val=0;
        for(int i=1; i<a.size(); ++i) val += a.get(i)*Math.cos(i*(x/4 + Math.PI)) + b.get(i)*Math.sin(i*(x/4 + Math.PI));
        val = val + (a.get(0)/2.0);
        return val;
    }
    double polyValueEvenFunction(double x, ArrayList<Double> a, ArrayList<Double> b) { //parzysta liczba pkt !
        double val=0;
        for(int i=1; i<a.size()-1; ++i) val += a.get(i)*Math.cos(i*(x/4 + Math.PI));
        val = val + a.get(a.size()-1)*(Math.cos((a.size()-1)*(x/4 + Math.PI)));
        val = val + a.get(0)/2;
        return val;
    }
}
