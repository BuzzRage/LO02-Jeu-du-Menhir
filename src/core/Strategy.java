package core;

import java.util.Observable;
import java.util.Observer;

public abstract class Strat implements Strategy,Observer{
    protected TypeSaison saisonActuelle;
    protected Joueur joueurActuel,maxMenhirs,maxGraines;
    
    public Joueur getMaxMenhirs() {
        return maxMenhirs;
    }

    public void setMaxMenhirs(Joueur maxMenhirs) {
        this.maxMenhirs = maxMenhirs;
    }

    public Joueur getMaxGraines() {
        return maxGraines;
    }

    public void setMaxGraines(Joueur maxGraines) {
        this.maxGraines = maxGraines;
    }

    public void update(Observable o, Object arg) {
	if(o instanceof Partie){
	    saisonActuelle=((Partie) o).getSaison();
	    joueurActuel=((Partie) o).getJoueurActif();
	    maxMenhirs=((Partie) o).getJoueurMaxMenhir();
	    maxGraines=((Partie) o).getJoueurMaxGraines();
	}
    }
}
