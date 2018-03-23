import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Maciek on 2016-05-19.
 */
public class MrsFront { //te(0,4), x e (0,2pi)
    //int k, h;
    double[] xs;
    double[] ts;
    double[][] us;
    double jumph;
    double jumpk;
    int xc, tc;
    public MrsFront(int nrOfXNodes, int nrOfTNodes) {
        this.tc = nrOfTNodes;
        this.xc = nrOfXNodes;
        xs = new double[nrOfXNodes];
        ts = new double[nrOfTNodes];
        us = new double[nrOfTNodes][nrOfXNodes];
        jumph = 2*Math.PI/((double)nrOfXNodes-1);
        jumpk = 4/((double)nrOfTNodes-1);
        if(jumpk/Math.pow(jumph,2)>=0.5) System.out.println("BOBOBOBOBOBOOBOBOBOBOBO");
    }

    void fillPts() {
        for(int i=0; i<xc; ++i) xs[i] = i*jumph;
        for(int i=0; i<tc; ++i) ts[i] = i*jumpk;
        xs[xc-1] = 2*Math.PI;
        ts[tc-1] = 4;
    }

    void makeUS() {
        for(int i=0; i<xc; ++i) us[0][i] = Math.pow(xs[i],2)*Math.sin(2*xs[i]);
        for(int i=1; i<tc; ++i) us[i][0] = 0;
        for(int i=1; i<tc; ++i) us[i][xc-1] = 0;

        for(int i=1; i<tc; ++i) {
            for(int j=1; j<xc-1; ++j) {
                us[i][j] = (jumpk/Math.pow(jumph,2))*(us[i-1][j+1]-2*us[i-1][j]+us[i-1][j-1])+us[i-1][j];
            }
        }
    }





}
