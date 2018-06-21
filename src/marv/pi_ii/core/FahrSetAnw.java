package marv.pi_ii.core;

import java.io.*;
import java.util.TreeSet;

/**
 * Created by Marvin Kirsch on 29.05.2018.
 * Matrikelnr.: 11118687
 */
public class FahrSetAnw {

    private static final String fileName = "res/FAHR1.TXT";
    private static TreeSet<FahrSer> tmenge;

    public static void main(String[] args) throws IOException {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        //c1 c2 c3 c4 menu 1 2 3 4 5

        //c1
        FahrSet fs = new FahrSet();
        fs.tmenge = new TreeSet<FahrSer>();

        //c2
        BufferedReader br1 = new BufferedReader(new FileReader(fileName));
        fs.dat2tset(br1);
        tmenge = fs.tmenge;

        //c3
        for (String s : fs.set2Stringf()) {
            System.out.print(s + "\n");
        }

        //c4
        for (int xy = 0; xy < 100; xy++) {
            fs.tmenge = tmenge;
            System.out.println("Menü\n" +
                    "(1)Anlegen und Einfügen in der Menge\n" +
                    "(2)Anzeigen der Menge\n" +
                    "(3)Löschen eines Elementes aus der Menge\n" +
                    "(4)Ändern eines Elements\n" +
                    "(5)Schreiben der Elemente in eine Datei\n");
            switch (Integer.parseInt(brc.readLine())) {
                case 1:
                    me1();
                    break;
                case 2:
                    me2();
                    break;
                case 3:
                    me3();
                    break;
                case 4:
                    me4();
                    break;
                case 5:
                    me5();
                default:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void me1() {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        int nr = 0;
        FahrSer r = new FahrSer(1, 1);
        System.out.println("Enter csv for new entry: ");
        try {
            r.csv2Fahrrad(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        tmenge.add(r);
    }

    private static void me2() {

        for (FahrSer fs : tmenge) {
            System.out.print(fs.fahrAus());
        }
    }

    private static void me3() throws IOException {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter fnr from FahrSer you want to remove!\n");
        int fnr = Integer.parseInt(brc.readLine());

        FahrSer tfs = null;

        for(FahrSer fs : tmenge) {
            if(fs.getFnr() == fnr) {
                tfs = fs;
            }
        }

        try {
            tmenge.remove(tfs);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void me4() throws IOException {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter fnr from FahrSer you want to change!\n");
        int fnr = Integer.parseInt(brc.readLine());

        FahrSer tfs = null;

        for(FahrSer fs : tmenge) {
            if(fs.getFnr() == fnr) {
                tfs = fs;
            }
        }

        try {
            tmenge.remove(tfs);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        //fehler im doppel fall beheben
        System.out.print(tfs.fahrAus());
        System.out.print("Enter new csv for FahrSer!");

        tfs.csv2Fahrrad(brc.readLine());
        tmenge.add(tfs);
    }

    private static void me5() throws IOException {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter filename for new File: ");

        String fileNameSave = "";

        try {
            fileNameSave = "res/" + brc.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter bwc = new BufferedWriter(new FileWriter(fileNameSave));

        for(FahrSer fs : tmenge) {

            bwc.append(fs.getFnr() + ";" + fs.getPreis() + ";" + fs.getFbez() + ";" + fs.getTacho() + "\n");
        }
        bwc.flush();
        bwc.close();
    }
}

