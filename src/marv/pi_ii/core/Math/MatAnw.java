package marv.pi_ii.core.Math;

import java.io.*;

/**
 * Created by Marvin Kirsch on 29.05.2018.
 * Matrikelnr.: 11118687
 */
public class MatAnw {

    //ergebnis wird in EMAT... gespeichert
    private static BufferedReader br1;
    private static BufferedReader br2;
    private static BufferedReader br3;
    private static FileWriter fx1;
    private static FileWriter fx2;

    public static void main(String[] args) throws IOException {

        //dateien sind in res gespeichert daher in der abgabe der ordner "res"
        //br1 = new BufferedReader(new FileReader("res/MAT1.txt"));
        //br2 = new BufferedReader(new FileReader("res/MAT2.txt"));
        //br1 = new BufferedReader(new FileReader("res/MAT3.txt"));
        br3 = new BufferedReader(new FileReader("res/MAT4.txt"));
        //br1 = new BufferedReader(new FileReader("res/MAT5.txt"));
        //br2 = new BufferedReader(new FileReader("res/MAT6.txt"));
        br1 = new BufferedReader(new FileReader("res/MAT7.txt"));
        br2 = new BufferedReader(new FileReader("res/MAT8.txt"));

        fx1 = new FileWriter("res/EMAT4.txt");
        fx2 = new FileWriter("res/MATSUM.txt");

        Mat m1 = new Mat(br1);
        Mat m2 = new Mat(br2);
        Mat m3 = new Mat(br3);
        Mat e1 = m1.matMul(m2);
        e1.matAus(fx1);
        System.out.println("Ergebnissmatrix e1 wurde in EMAT4.txt geschrieben");
        double[][] x = new double[][]{{2,3,4,9},{4,5,4,9},{3,6,4,9},{7,12,4,9}};
        x = m3.matadd(x);
        for(double a[] : x) {
            for(double b : a) {
                System.out.print(b + " ");
            }
            System.out.print("\n");
        }

        for(double a[] : x) {
            String nl = "";
            for(double b : a) {
                nl += (b + ";");
            }
            nl = nl.substring(0, nl.length()-1);
            nl += ("\n");
            fx2.write(nl);
        }

        fx2.flush();
        fx2.close();
        fx1.flush();
        fx1.close();
    }
}
