import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

//    public static void main(String[] args) {
//        MrsFront m = new MrsFront(40, 350);
//        m.fillPts();
//        m.makeUS();
//
//
//        File f = new File("solution.dat");
//
//        try {
//            f.createNewFile();
//            FileWriter fw = new FileWriter(f);
//            for(int i=0; i<m.xc; ++i) {
//                for (int j=0; j<m.tc; ++j) {
//                    fw.write(m.xs[i]+" "+m.ts[j]+" "+m.us[j][i]+"\n");
//                }
//            }
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% MRS BACK
public static void main(String[] args) {
    MrsBack m = new MrsBack(100, 10);
    m.fillPts();
    m.makeUS();


    File f = new File("solution.dat");

    try {
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        for(int i=0; i<m.xc; ++i) {
            for (int j=0; j<m.tc; ++j) {
                fw.write(m.xs[i]+" "+m.ts[j]+" "+m.us[j][i]+"\n");
            }
        }
        fw.flush();
        fw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
