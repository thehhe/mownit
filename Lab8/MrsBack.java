/**
 * Created by Maciek on 2016-06-07.
 */
public class MrsBack {
    double[] xs;
    double[] ts;
    double[][] us;
    double jumph;
    double jumpk;
    int xc, tc;

    public MrsBack(int nrOfXNodes, int nrOfTNodes) {
        this.tc = nrOfTNodes;
        this.xc = nrOfXNodes;
        xs = new double[nrOfXNodes];
        ts = new double[nrOfTNodes];
        us = new double[nrOfTNodes][nrOfXNodes];
        jumph = 2*Math.PI/((double)nrOfXNodes-1);
        jumpk = 4/((double)nrOfTNodes-1);
    }

    void fillPts() {
        for(int i=0; i<xc; ++i) xs[i] = i*jumph;
        for(int i=0; i<tc; ++i) ts[i] = i*jumpk;
        xs[xc-1] = 2*Math.PI;
        ts[tc-1] = 4;
    }

    void makeUS() {
        int wiersze = tc-1,
            kolumny = xc-2;
        double[][] A = new double[wiersze*kolumny][wiersze*kolumny];
        double[] d = new double[wiersze*kolumny];

        //fill A
        double a = -jumpk/Math.pow(jumph,2);
        double b = 1.0 + 2*jumpk/Math.pow(jumph,2);
        double c = -jumpk/Math.pow(jumph,2);

        for(int i=0; i<wiersze*kolumny; ++i) {
            A[i][i] = b;
            if(i < (wiersze*kolumny - 1) && i%kolumny != 3) A[i][i+1] = c;
            if(i != 0 && i%kolumny != 0) A[i][i-1] = a;
        }
        for(int i=0; i<(wiersze-1)*(kolumny); ++i) {
            A[i][i+kolumny] = -1;
        }
        int tmp = xc-2;
        for(int i=wiersze*kolumny-1; i>=(wiersze-1)*kolumny; --i) {
            d[i] = Math.pow(xs[tmp],2)*Math.sin(2*xs[tmp]);
            tmp--;
        }

        GaussianElimination g = new GaussianElimination();
        double[] solution = new double[wiersze*kolumny];

        solution = g.lsolve(A, d);

        for(int i=0; i<xc; ++i) us[0][i] = Math.pow(xs[i],2)*Math.sin(2*xs[i]);
        for(int i=1; i<tc; ++i) us[i][0] = 0;
        for(int i=1; i<tc; ++i) us[i][xc-1] = 0;

        double[][] srodek = new double[wiersze][kolumny];
        for(int i=0; i<wiersze; ++i) {
            for(int j=0; j<kolumny; ++j) {
                srodek[i][j] = solution[i*kolumny+j];
            }
        }
        int q=0;
        for(int i=tc-1; i>0; --i) {
            for(int j=1; j<xc-1; ++j) {
                us[i][j] = solution[q*kolumny+j-1];
            }
            ++q;
        }
    }


}
