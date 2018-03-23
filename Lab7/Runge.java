import java.util.ArrayList;

/**
 * Created by Maciek on 2016-05-05.
 */
public class Runge {
    double ode(double x, double y) { //mamy rownanie postaci y' = f(x,y)
        return 4*y*Math.sin(4*x) + 4*Math.sin(4*x)*Math.cos(4*x);
    }
    double f1(double x, double y, double h) {
        return h*ode(x,y);
    }
    double f2(double x, double y, double h) {
        return h*ode(x+0.5*h, y+0.5*f1(x,y,h)*h);
    }
    double f3(double x, double y, double h) {
        return h*ode(x+0.5*h, y+0.5*f2(x,y,h)*h);
    }
    double f4(double x, double y, double h) {
        return h*ode(x+h, y+f3(x,y,h)*h);
    }

    double getNextX(double prevX, double h) {
        return prevX + h;
    }
    double getNextY(double prevX, double prevY, double h) {
        return prevY + (f1(prevX,prevY,h) + 2*f2(prevX,prevY,h) + 2*f3(prevX,prevY,h) + f4(prevX,prevY,h))/6;
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
