package marv.pi_ii.core.Math;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by Marvin Kirsch on 29.05.2018.
 * Matrikelnr.: 11118687
 */
public class Mat implements addierbar{

    private int a[][];
    private int n, m, crt = 0;

    public Mat() {}

    public Mat(BufferedReader br1) {

        //einlesen der werte in arraylist pro zeile dann jeweils zeile(als arraylist) in arraylist speichen
        ArrayList<ArrayList<Integer>> rl = new ArrayList<>();
        while(true) {
            try {
                String thisLine = br1.readLine();

                if(thisLine == null) {
                    break;
                }
                String[] temp = thisLine.split(";");
                ArrayList<Integer> ints = new ArrayList<>(); //erzeugen der arraylist für die neue zeile

                for (String s : temp) { //durchlaufen des String[] und in zeilen arraylist einfügen
                    ints.add(Integer.parseInt(s));
                }
                rl.add(ints); //zeile zu arraylist hinzufügen
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(ArrayList<Integer> tes : rl) { //test ob alle zeilen gleich lang sind
            if(tes.size() != rl.get(0).size())
                this.crt = -1; //falls nicht crt auf -1 setzen
        }

        this.n = rl.size();//n setzen
        this.m = rl.get(0).size();//m setzen

        if(crt != -1) {//wenn falsch wird die matrix nicht befüllt
            //a[n][m]
            a = new int[this.n][this.m];

            int n = 0, m = 0;
            for (ArrayList<Integer> intis : rl) {//befüllen der matrix mit doppelter foreach schleife hier könnte man eventuell for i schleife verwenden...

                for (int i : intis) {

                    a[n][m] = i;
                    m++;
                }
                m=0;
                n++;
            }
            this.crt = 1; //bei abschluss crt auf 1 setzen
        }
    }

    public boolean matAus(FileWriter fx) {
        //einfaches druchlaufen von a per for each schleife schreiben der variablen
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
            try {
                //setze eigenschaften der ergebnismatrix
                end.n = this.n;
                end.m = b.m;
                end.a = new int[end.n][end.m];
                //for - schleife zum durchlaufen der matrix
                for (int y = 0; y < end.n; y++) {
                    for (int x = 0; x < end.m; x++) {
                        ArrayList<Integer> ma = new ArrayList<>();//m //arraylist zum schreiben der variablen der Matrix A
                        ArrayList<Integer> mb = new ArrayList<>();//n //arraylist zum schreiben der variablen der Matrix B
                        //beschreiben der arraylist A
                        for (int m = 0; m < this.m; m++) {
                            ma.add(this.a[y][m]);
                        }
                        //beschreiben der arraylist B
                        for (int n = 0; n < b.n; n++) {
                            mb.add(b.a[n][x]);
                        }
                        //variable für das ergebnis und wert für die zelle der ergebnismatrix
                        int ev = 0;
                        for (int t = 0; t < ma.size(); t++) {
                            ev += ma.get(t) * mb.get(t); //rechnung der eigentlichen multiplikation
                        } //die in der for schleife nun addiert wird
                        end.a[y][x] = ev; //wert wird eingetragen
                    }
                }
                end.crt = 1; //crt wird auf 1 gesetzt
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return end;
    }

    @Override
    public double[][] matadd(double[][] x) {
        double[][] er = x;

        int xc = 0;
        int yc = 0;
        try {
            for (int[] i: this.a) {
                for (int y: i) {
                    er[yc][xc] = x[yc][xc] + (double)this.a[yc][xc];
                    xc++;
                }
                yc++;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return er;
    }
}
