package marv.pi_ii.core;

import java.io.*;
import java.util.LinkedList;
import java.util.TreeSet;

public class FahrSerAnw {

    private static final String fileName = "res/FAHR1.TXT";
    private static LinkedList<FahrSer> radliste;

    public static void main(String[] args) throws IOException {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br1 = new BufferedReader(new FileReader(fileName));

        //d1 d2 d3 c4 menu 1 2 3 4 5

        System.out.println("System starting ... ");
        //d1
        FahrList fl = new FahrList();
        fl.radliste = radliste;

        System.out.println("d1 completed");
        //c1
        int reval = fl.dat2Radliste(br1);
        System.out.println("Anzahl der korrekten Instanzen: " + reval);
        radliste = fl.radliste;

        System.out.println("c1 completed");
        //c2
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("res/radliste.ser");
        } catch(FileNotFoundException fnfe1) {
            fnfe1.printStackTrace();
            File f = new File("res/radliste.ser");
            fos = new FileOutputStream("res/radliste.ser");
        }
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        fl.radListeSerAus(oos);
        oos.flush();
        oos.close();
        fos.close();

        System.out.println("c2 completed");
        //c3
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("res/radliste.ser");
        } catch(FileNotFoundException fnfe2) {
            fnfe2.printStackTrace();
            System.err.println("Something went terrible wrong............................!!!");
            System.exit(0);
        }
        ObjectInputStream ois = new ObjectInputStream(fis);
        fl.radListeSerEin(ois);
        ois.close();
        fis.close();

        System.out.println("c3 completed");
        //c4
        fl.radListeAus();

        System.out.println("c4 completed");
        //d2
        System.out.println("starting d2 or Menu!..................");
        for (int xy = 0; xy < 100; xy++) {
            fl.radliste = radliste;
            fl.radListeAus();
            System.out.println("Menü\n" +
                    "(1)Anlegen und Einfügen in der Liste\n" +
                    "(2)Löschen eines Elementes aus der Liste\n" +
                    "(3)Ändern eines Elements\n" +
                    "(4)Serilazable und so\n" +
                    "(5)Ende\n");
            switch (Integer.parseInt(brc.readLine())) {
                case 1:
                    anlegenUndEinketten();
                    System.out.println("completed!\n");
                    break;
                case 2:
                    loeschen();
                    System.out.println("completed!\n");
                    break;
                case 3:
                    aendern();
                    System.out.println("completed!\n");
                    break;
                case 4:
                    outputUndInputStream(fl);
                    System.out.println("completed!\n");
                    break;
                case 5:
                    ende();
                    System.out.println("completed!\n");
                default:
                    System.out.println("Falsche eingabe System wird heruntergefahren");
                    System.exit(0);
                    break;
            }
        }
    }

    private static void anlegenUndEinketten() {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        int nr = 0;
        System.out.println("Enter position: ");
        try {
            nr = Integer.parseInt(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        FahrSer r = new FahrSer(1, 1);
        System.out.println("Enter csv for new entry: ");
        try {
            r.csv2Fahrrad(brc.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        radliste.add(nr, r);
        System.out.println("Added Element!");
    }

    private static void loeschen() throws IOException {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter fnr from FahrSer you want to remove!\n");
        int fnr = Integer.parseInt(brc.readLine());

        FahrSer tfs = null;

        for(FahrSer fs : radliste) {
            if(fs.getFnr() == fnr) {
                tfs = fs;
            }
        }

        try {
            radliste.remove(tfs);
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("Deleted..");
    }

    private static void aendern() throws IOException {

        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter fnr from FahrSer you want to change!\n");
        int fnr = Integer.parseInt(brc.readLine());

        FahrSer tfs = null;

        for(FahrSer fs : radliste) {
            if(fs.getFnr() == fnr) {
                tfs = fs;
            }
        }

        try {
            radliste.remove(tfs);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        //fehler im doppel fall beheben
        System.out.print(tfs.fahrAus());
        System.out.print("Enter new csv for FahrSer!");

        tfs.csv2Fahrrad(brc.readLine());
        radliste.add(tfs);
    }

    private static void outputUndInputStream(FahrList fl) throws IOException {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("res/radliste.ser");
        } catch(FileNotFoundException fnfe1) {
            fnfe1.printStackTrace();
            File f = new File("res/radliste.ser");
            try {
                fos = new FileOutputStream("res/radliste.ser");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fl.radListeSerAus(oos);
        try {
            oos.flush();
            oos.close();
            fos.close();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("res/radliste.ser");
        } catch(FileNotFoundException fnfe2) {
            fnfe2.printStackTrace();
            System.err.println("Something went terrible wrong............................!!!");
            System.exit(0);
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fl.radListeSerEin(ois);
        try {
            ois.close();
            fis.close();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        fl.radListeAus();
    }

    private static void ende() throws IOException {

        System.out.println("Goodbye..");
        System.exit(0);
    }
}
