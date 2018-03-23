/**
 * Created by Maciek on 2016-03-30.
 */
public class SecantMethod {
    //    double a,b;
//    double epsilon;
//
//    SecantMethod(double a, double b, double e) {
//        this.a = a;
//        this.b = b;
//        this.epsilon = e;
//    }
    double function(double x) {
        return Math.pow(x, 15) + Math.pow(x, 16); //tutaj bedziemy wklepywac funkcje z lapy
    }

    double find(double a, double b, double e, int n, double dx) {

        if (function(a) * function(b) > 0) {
            System.out.println("W tym przedziale nie ma przeslanek do znajdowania pierwiastka.;[");
            return -100;
        }

        double fn = 100;
        int counter = 0;

        if (n < 0) {
            while (Math.abs(dx) > e && function(fn) != 0) {
                fn = b - ((function(b) * (b - a)) / (function(b) - function(a)));
                a = b;
                b = fn;
                dx = b - a;
                counter++;
            }
        } else {
            while (/*Math.abs(dx)*/ Math.abs(function(b)) > e && function(fn) != 0 && counter < n) {
                fn = b - ((function(b) * (b - a)) / (function(b) - function(a)));
                a = b;
                b = fn;
                dx = b - a;
                counter++;
            }
        }
        if (counter == n) System.out.println("Nie znaleziono");
        else System.out.println("Po "+counter+" iteracjach znaleziono");
        return fn;
    }
}
