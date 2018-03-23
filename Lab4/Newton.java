import java.util.ArrayList;

/**
 * Created by Maciek on 2016-04-06.
 */

public class Newton {
    ArrayList<Double> x;
    ArrayList<Double> y;
    int n;

    Newton(ArrayList<Double> x, ArrayList<Double> y) {
        this.x = x;
        this.y = y;
        this.n = x.size();
    }

    double valueOfInterpolation(double a) {
        //tablica ilorazow roznicowych
        double[][] diffs = new double[n][n+1];
        for(int i=0; i<n; ++i) {
            diffs[i][0] = x.get(i);
            diffs[i][1] = y.get(i);
        }
        for(int i=2; i<=n; ++i) {
            for(int j=0; j<n-i+1; ++j) {
                diffs[j][i] = (diffs[j+1][i-1]-diffs[j][i-1])/(diffs[i-1+j][0]-diffs[j][0]);
            }
        }

        double value = 0;
        for(int i=0; i<n; ++i) {
            double adder = 1;
            for(int j=0; j<=i-1; ++j) {
                adder *= (a-diffs[j][0]);
            }
            adder *= diffs[0][i+1];
            value += adder;
        }
        return value;
    }
}
