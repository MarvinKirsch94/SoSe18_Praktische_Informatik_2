package marv.pi_ii.core;

import java.io.Serializable;

public class FahrSer implements Comparable<FahrSer>, Serializable {

    private int fnr; //Fahrradnummer
    private double preis; //Anschaffungspreis des Fahrrads
    private String fbez; //Bezeichung des Fahrrads
    private double tacho; //Tachometerstand
    private int crt; //Kontrollwert

    public FahrSer(int efnr, double etacho) {

        this.fnr = efnr;
        this.tacho = etacho;
        this.preis = 1.0;
        this.crt = 1;
    }

    public boolean setPreis(double epreis) {

        if(epreis >= 1.0) {
            this.preis = epreis;
            return true;
        }
        return false;
    }

    public double getPreis() {

        return this.preis;
    }

    public int csv2Fahrrad(String ecsv) {
        System.out.println("Starting csv2Fahrrad");
        String csv[] = ecsv.split(";");
        int err = 0;
        boolean wasTrue = false;
        try {
            this.fnr = Integer.parseInt(csv[0]);
        } catch(Exception e) {
            err = 1;
        }
        try {
            wasTrue = this.setPreis(Double.parseDouble(csv[1]));
        } catch(Exception e) {
            err = 2;
        }
        try {
            this.fbez = csv[2];
        } catch(Exception e) {
            err = 3;
        }
        try {
            this.tacho = Double.parseDouble(csv[3]);
        } catch(Exception e) {
            err = 4;
        }

        if(err == 0 && wasTrue && csv.length == 4) {
            this.crt = 3;
            return 1;
        } else if(err > 0) {
            this.crt = -1;
            return -err;
        } else if(csv.length != 4) {
            this.crt = -1;
            return -5;
        }
        //NNN;PPP.PP;BBBB;TTT.T
        return -5;
    }

    public String fahrAus() {

        String re = "";
        re += "fnr: " + this.fnr + "\n";
        re += "preis: " + this.preis + "\n";
        re += "fbez: " + this.fbez + "\n";
        re += "tacho: " + this.tacho + "\n";
        re += "crt: " + this.crt + "\n";

        return re;
    }

    public int getFnr() {
        return fnr;
    }

    public String getFbez() {
        return fbez;
    }

    public double getTacho() {
        return tacho;
    }

    public int getCrt() {
        return crt;
    }

    @Override
    public int compareTo(FahrSer f) {
        int frnResult = Integer.compare(this.fnr, f.fnr);
        if(frnResult==0) {
            return this.fbez.compareTo(f.fbez);
        } else {
            return frnResult;
        }
    }
}
