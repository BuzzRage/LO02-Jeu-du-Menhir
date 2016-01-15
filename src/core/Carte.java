package core;

import java.util.Observer;
import java.util.Observable;


/**
 * La classe Carte modélise les aspects invariants des cartes du jeu.<br>
 * Une Carte dispose d'un effet (<code>int[][] effet</code>) en fonction d'une saison et d'une action, elle possède également le booléen <code>pose</code> pour indiquer si la Carte est jouée ou non.<br>
 * <code>static TypeSaison saisonActuelle</code> contient la saison en cours de la partie via le pattern Observer-Observable. Elle est commune à toute les cartes donc static.
 */
public abstract class Carte extends Observable implements Jouable,Observer{


    protected int[][] effet;
    private boolean pose;
    private final TypeCarte type;
    protected static TypeSaison saisonActuelle;

    public Carte(TypeCarte t) {
        pose = false;
        type=t;
        Carte.saisonActuelle=TypeSaison.PRINTEMPS;
    }
    
    /**
     * Renvoie un booléen indiquant si un carte est posée ou non. Cela permet de savoir quelles cartes ont déjà été jouées.
     * 
     * @return true si la carte a déjà été jouée. false sinon.
     */
    public boolean isPose(){
        return pose;
    }
    
    /**
     * Retourne le type de la carte
     * @return type
     */
    public TypeCarte getTypeCarte(){
        return type;
    }
    
    public TypeSaison getSaison(){
        return saisonActuelle;
    }
    
    
    /**
     * Retourne l'effet de la carte, en fonction de la saison en cours et de l'action voulue.
     * @param a
     * 		L'action choisie par le Joueur.
     * @return L'effet de la carte.
     */
    public int getEffet(TypeAction a){
        return effet[a.toInteger()][saisonActuelle.toInteger()];
    }
    
    /**
     * Retourne l'effet de la carte, en fonction de la saison voulue et de l'action voulue.
     * @param a
     * 		L'action choisie par le Joueur.
     * @param s
     * 		La saison voulue.
     * @return L'effet de la carte.
     */
    public int getEffet(TypeAction a,TypeSaison s){
        return effet[a.toInteger()][s.toInteger()];
    }
    
    /**
     * Change l'état d'une carte.
     * @param b
     * 		Le booléen indiquant si une carte est posée ou non.
     */
    public void setPose(boolean b)
    {
        pose=b;
    }
    
    public void update(Observable o,Object arg){
	if(o instanceof Partie){
	    saisonActuelle=((Partie) o).getSaison();
            setChanged();
            notifyObservers();
	}
    }
    
    /**
     * @see core.Jouable#jouer(Joueur, Joueur, TypeAction)
     */
    public abstract void jouer(Joueur lanceur, Joueur cible);
}
