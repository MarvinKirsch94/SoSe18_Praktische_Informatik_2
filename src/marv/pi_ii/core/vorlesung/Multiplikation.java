package marv.pi_ii.core.vorlesung;

import marv.pi_ii.core.Math.Mat;

public interface Multiplikation {

    public abstract Gruppe multipliziere(Gruppe b);
    public abstract Gruppe neutralEle();
    public String ausEle();
}
