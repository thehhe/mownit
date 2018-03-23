import java.util.ArrayList;

/**
 * Created by Maciek on 2016-05-05.
 */
public class Euler {

    double ode(double x, double y) { //mamy rownanie postaci y' = f(x,y)
        return 4*y*Math.sin(4*x) + 4*Math.sin(4*x)*Math.cos(4*x);
    }
    double getNextX(double prevX, double h) {
        return prevX + h;
    }
    double getNextY(double prevX, double prevY, double h) {
        return prevY + h* ode(prevX,prevY);
    }

    double analitycal_solution(double x) {
        return Math.exp(-Math.cos(4*x)) - Math.cos(4*x) + 1;
    }

    double count_max_norm(ArrayList<Double> xn, ArrayList<Double> yn) {
        double ro = 0;
        for (int i = 0; i < xn.size(); ++i) {
            double tmp = Math.abs(yn.get(i) - analitycal_solution(xn.get(i)));
            if (tmp > ro) ro = tmp;
        }
        return ro;
    }
}
