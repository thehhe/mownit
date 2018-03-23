import java.util.ArrayList;

/**
 * Created by Maciek on 2016-05-22.
 */
public class BoundaryValue {

    double sol(double x) { return 5*x - 5*Math.sin(x); }
    ArrayList<Double> ai;
    ArrayList<Double> bi;
    ArrayList<Double> ci;
    ArrayList<Double> di;

    ArrayList<Double> xs;
    double h; //h-skok

    BoundaryValue(double h) {
        this.h = h;
        this.ai = new ArrayList<>();
        this.bi = new ArrayList<>();
        this.ci = new ArrayList<>();
        this.di = new ArrayList<>();
        this.xs = new ArrayList<>();

        for(double i=0; i<2*Math.PI+5; i+=h) {
            this.xs.add(i);
            //System.out.println(xs.get(xs.size()-1));
        }
        this.xs.add(2*Math.PI+5);
        this.ai.add(0.0);
        for(int i=1; i<xs.size()-2; ++i) this.ai.add(1.0);
        for(int i=1; i<xs.size()-1; ++i) this.di.add(Math.pow(h,2)-2);
        for(int i=1; i<xs.size()-2; ++i) this.ci.add(1.0);
        this.ci.add(0.0);
        for(int i=1; i<xs.size()-1; ++i) this.bi.add(5.0*Math.pow(h,2)*xs.get(i));
        double tmp = this.bi.get(0);
        this.bi.set(0, tmp-1);
        tmp = this.bi.get(bi.size()-1);
        this.bi.set(bi.size()-1, tmp-sol(2.0*Math.PI+5.0));
        //System.out.println(sol(2.0*Math.PI+5.0));
    }

    ArrayList<Double> getYs() {
        ArrayList<Double> result = new ArrayList<>();

        for(int i=1; i<di.size(); ++i) {
            double m = ai.get(i)/di.get(i-1);
            double tmp = di.get(i);
            di.set(i, tmp-(m*ci.get(i-1)));
            tmp = bi.get(i);
            bi.set(i, tmp-(m*bi.get(i-1)));
        }
        int N = di.size();
        for(int i=0; i<N; ++i) result.add(0.0);
        result.set(N-1, bi.get(N-1)/di.get(N-1));
        for(int i=N-2; i>=0; --i) result.set(i, (bi.get(i) - (ci.get(i)*result.get(i+1)))/di.get(i));

        return result;
    }

    double count_max_norm(ArrayList<Double> xn, ArrayList<Double> yn) {
        double ro = 0;
        for (int i = 0; i < xn.size(); ++i) {
            double tmp = Math.abs(yn.get(i) - sol(xn.get(i)));
            if (tmp > ro) ro = tmp;
        }
        return ro;
    }
}
