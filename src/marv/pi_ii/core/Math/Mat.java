package marv.pi_ii.core.Math;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Marvin Kirsch on 29.05.2018.
 * Matrikelnr.: 11118687
 */
public class Mat {

    private int a[][];
    private int n, m, crt = 0;

    public Mat() {}

    public Mat(BufferedReader br1) {

        ArrayList<ArrayList<Integer>> rl = new ArrayList<>();
        while(true) {
            try {
                String thisLine = br1.readLine();

                if(thisLine == null) {
                    break;
                }
                String[] temp = thisLine.split(";");
                ArrayList<Integer> ints = new ArrayList<>();

                for (String s : temp) {
                    ints.add(Integer.parseInt(s));
                }
                /*for(int i : ints) {
                    System.out.println(i);
                }
                System.out.println("next");*/
                rl.add(ints);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(ArrayList<Integer> tes : rl) {
            if(tes.size() != rl.get(0).size())
                this.crt = -1;
        }

        this.n = rl.size();
        this.m = rl.get(0).size();

        if(crt != -1) {
            //a[n][m]
            a = new int[this.n][this.m];

            int n = 0, m = 0;
            for (ArrayList<Integer> intis : rl) {

                for (int i : intis) {

                    //System.out.println("n" + n + "m" + m);
                    a[n][m] = i;
                    m++;
                }
                m=0;
                n++;
            }
            this.crt = 1;
        }
    }

    public boolean matAus(FileWriter fx) {

        try {
            for (int[] i : a) {
                for (int x = 0; x < i.length; x++) {

                    fx.append(String.valueOf(i[x]));
                    if (x != i.length - 1) {
                        fx.append(";");
                    }
                }
                fx.append("\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public Mat matMul(Mat b) {

        Mat end = new Mat();

        if(this.m != b.n) {
            end.crt = -3;
        }
        else {

            end.n = this.n;
            end.m = b.m;
            end.a = new int[end.n][end.m];
            for(int y = 0; y < end.n; y++) {
                for(int x = 0; x < end.m; x++) {
                    ArrayList<Integer> ma = new ArrayList<>();//m
                    ArrayList<Integer> mb = new ArrayList<>();//n
                    for(int m = 0; m < this.m; m++) {
                        ma.add(this.a[y][m]);
                    }
                    for(int n = 0; n < b.n; n++) {
                        mb.add(b.a[n][x]);
                    }
                    int ev = 0;
                    for(int t = 0; t < ma.size(); t++) {
                        ev += ma.get(t) * mb.get(t);
                    }
                    end.a[y][x] = ev;
                }
            }
            end.crt = 1;
        }
        return end;
    }
}
