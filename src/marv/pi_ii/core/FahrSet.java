package marv.pi_ii.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Created by Marvin Kirsch on 29.05.2018.
 * Matrikelnr.: 11118687
 */
public class FahrSet {

    TreeSet<FahrradS> tmenge;

    public int dat2tset(BufferedReader br1) throws IOException {

        int countCO = 0;
        while (true) {
            String thisLine = br1.readLine();
            if (thisLine == null) {
                break;
            }
            FahrradS r = new FahrradS(1, 1);
            int err = r.csv2Fahrrad(thisLine);
            System.out.println(err == 1 ? r.fahrAus() + "\n" + "erfolgreich!\n" : (err > -5 ? r.fahrAus() + "\n" + "das " + err + "te element ist fehlerhaft!" : "\nFEHLERHAFTER DATENSATZ!"));
            if (err == 1) {
                try {
                    countCO++;
                    tmenge.add(r);
                } catch(Exception e) {
                    countCO--;
                    System.err.print(e + "\n");
                }
            }
        }

        return countCO;
    }

    public String[] set2Stringf() {

        String[] s = new String[tmenge.size()];
        int c = 0;
        for(FahrradS f : tmenge) {
            s[c] = (f.getFnr() + ";" + f.getPreis() + ";" + f.getFbez() + ";" + f.getTacho() + "\n");
            c++;
        }

        return s;
    }
}
