package marv.pi_ii.core;

import java.io.*;
import java.util.ArrayList;

public class FZDatAnw {

    private static ArrayList<Fahrrad> radlist = new ArrayList<>();

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/res/FAHR1.TXT")));
        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String thisLine = br.readLine();
            if(thisLine == null) {
                break;
            }
            Fahrrad r = new Fahrrad(1,1);
            int err = r.csv2Fahrrad(thisLine);
            System.out.println(err == 1 ? r.fahrAus() + "\n" + "erfolgreich!\n" : (err > -5 ? r.fahrAus() + "\n" + "das " + err + "te element ist fehlerhaft!" : "\nFEHLERHAFTER DATENSATZ!"));
            if(err == 1) {
                radlist.add(r);
            }
        }
        br.close();
        for(Fahrrad a : radlist) {
            System.out.print(a.fahrAus() + "\n");
        }

        //Menü

        System.out.println("Menü\n" +
                "(1)Anlegen und Einketteneines neuen Knotens zu einer gegebenen Position in die Liste\n" +
                "(2)Löschen eines Knotens in der Liste\n" +
                "(3)Ändern der Inhalte eines Knotens\n" +
                "(4)Schreiben der Listenknoten in eine Datei\n");
        switch(Integer.parseInt(brc.readLine())) {
            case 1:
                method1();
                break;
            case 2:
                method2();
                break;
            case 3:
                method3();
                break;
            case 4:
                method4();
                break;
        }
    }

    public static void method1() {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        int nr = 0;
        Fahrrad r = new Fahrrad(1, 1);
        System.out.println("");
        try {
            r.csv2Fahrrad(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
        try {
            nr = Integer.parseInt(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        radlist.add(nr, r);
    }

    public static void method2() {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        int nr = 0;
        System.out.println("");
        try {
            nr = Integer.parseInt(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        radlist.remove(nr);
    }

    public static void method3() {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        int nr = 0;
        System.out.println("");
        try {
            nr = Integer.parseInt(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Fahrrad r = radlist.get(nr);
        System.out.println("");
        try {
            r.csv2Fahrrad(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        radlist.set(nr, r);
    }

    public static void method4() {

        //todo datei mist noch erledigen siehe (4) auf blatt
    }
}