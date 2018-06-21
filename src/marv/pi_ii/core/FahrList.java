package marv.pi_ii.core;

import java.io.*;
import java.util.LinkedList;

public class FahrList {

    private LinkedList<FahrSer> radliste;


    //c1 einlesen der Knoten in die Liste aus der Datei die durch br1 (bufferedreader) bereitgestellt wird
    public int dat2Radliste(BufferedReader br1) throws IOException {
        int countCO = 0;
        radliste = new LinkedList<>();
        while (true) {
            String thisLine = br1.readLine();
            if (thisLine == null) {
                break;
            }
            FahrSer r = new FahrSer(1, 1);
            int err = r.csv2Fahrrad(thisLine);
            System.out.println(err == 1 ? r.fahrAus() + "\n" + "erfolgreich!\n" : (err > -5 ? r.fahrAus() + "\n" + "das " + err + "te element ist fehlerhaft!" : "\nFEHLERHAFTER DATENSATZ!"));
            if (err == 1) {
                try {
                    countCO++;
                    radliste.add(r);
                } catch(Exception e) {
                    countCO--;
                    System.err.print(e + "\n");
                }
            }
        }

        return countCO;
    }

    //c2
    public int radListeSerAus(ObjectOutputStream ow1) {

        int c = 0;
        for (FahrSer fs : radliste) {
            c++;
            try {
                ow1.writeObject(fs);
            } catch (IOException e) {
                e.printStackTrace();
                c--;
            }
        }

        return c;
    }

    public int radListeSerEin(ObjectInputStream or1) {
        int c = 0;
        while (true) {
            FahrSer fs = null;
            try {
                fs = (FahrSer) or1.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(fs == null) {
                break;
            }
            radliste.add(fs);
            c++;
        }
        return c;
    }

    public void radListeAus() {

        for (FahrSer fs : radliste) {
            String s = fs.fahrAus();
            s = s.replace("\n", "\t");
            s += "\n";
            System.out.print(s);
        }
    }
}
