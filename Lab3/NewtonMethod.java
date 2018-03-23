/**
 * Created by Maciek on 2016-03-30.
 */
public class NewtonMethod {

    double function(double x) {
        return Math.pow(x, 15) + Math.pow(x, 16);
    }
    double derivative(double x) {
        return 16*Math.pow(x, 15) + 15*Math.pow(x, 14);
    }

    double find(double a, double e) {
        double a1 = 0;
        int counter = 0;
        while(/*Math.abs(a1-a)>e*/ Math.abs(function(a))>e && function(a)!=0) {
            a1 = a;
            a = a - function(a)/derivative(a);
            counter++;
        }
        System.out.println("Po "+counter+" iteracjach znaleziono");
        return a;
    }
}
