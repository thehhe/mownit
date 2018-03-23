import java.util.ArrayList;

/**
 * Created by Maciek on 2016-04-17.
 */
public class Lagrange {
    ArrayList<Double> x;
    ArrayList<Double> y;
    ArrayList<Double> yder;
    int n;

    Lagrange(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> yder) {
        this.x = x;
        this.y = y;
        this.yder = yder;
        this.n = x.size();
    }

    double valueOfInterpolation(double a) {
        double value = 0;
        for(int i=0; i<n; ++i) {
            double adder = 1;
            for(int j=0; j<n; ++j) {
                if(i != j) adder *= ((a-x.get(j))/(x.get(i)-x.get(j)));
            }
            adder *= y.get(i);
            value += adder;
        }
        return value;
    }

    double valueHermit(double a) {
        double value = 0;
        for(int i=0; i<n; ++i) { //Wielomian Bi
            double tmp=makeLix(a, i);
            value += (a-x.get(i))*Math.pow(tmp,2)*yder.get(i);
        }
        for(int i=0; i<n; ++i) { //Wielomian Ai
            double Li = makeLix(a, i);
            double LiPrim = countDerivativeLix(x.get(i), i);
            value += (1-(2*(a-x.get(i))*LiPrim))*Math.pow(Li,2)*y.get(i);
        }
        return value;
    }

    double makeLix(double a, int i) {
        double result = 1;
        for(int j=0; j<n; ++j) {
            if(j != i) result *= (a-x.get(j))/(x.get(i)-x.get(j));
        }
        return result;
    }
    double countDerivativeLix(double a, int i) {
        double x = a+0.0001;
        return (makeLix(x,i)-makeLix(a,i))/(x-a);
    }
}
