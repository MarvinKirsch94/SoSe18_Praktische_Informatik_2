package marv.pi_ii.core.Math;

import java.io.*;

/**
 * Created by Marvin Kirsch on 29.05.2018.
 * Matrikelnr.: 11118687
 */
public class MatAnw {

    private static BufferedReader br1;
    private static BufferedReader br2;
    private static FileWriter fx;

    public static void main(String[] args) throws IOException {

        br1 = new BufferedReader(new FileReader("res/MAT1.TXT"));
        br2 = new BufferedReader(new FileReader("res/MAT2.TXT"));


        fx = new FileWriter("res/EMAT1.TXT");

        Mat m1 = new Mat(br1);
        Mat m2 = new Mat(br2);
        Mat e1 = m1.matMul(m2);
        e1.matAus(fx);
    }
}
