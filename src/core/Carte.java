package core;

import javax.swing.ImageIcon;
import java.util.Observable;

/**
 * La classe Carte modélise les aspects invariants des cartes du jeu.<br>
 * Une Carte dispose d'un effet (<code>int[][] effet</code>) en fonction d'une saison et d'une action, elle possède également le booléen <code>pose</code> pour indiquer si la Carte est jouée ou non.<br>
 * L'attribut <code>int nbr</code> permet de référencer la Carte, chaque Carte étant unique.<br>
 */
public abstract class Carte extends Observable implements Jouable{

    protected int[][] effet;
    private boolean pose;
    private final TypeCarte type;

    public Carte(TypeCarte type) {
        this.pose = false;
        this.type=type;
    }
    /**
     * Renvoie un booléen indiquant si un carte est posée ou non. Cela permet de savoir quelles cartes ont déjà été jouées.
     * 
     * @return true si la carte a déjà été jouée. false sinon.
     */
    public boolean isPose(){
        return this.pose;
    }
    
    /**
     * Retourne le type de la carte
     * @return type
     */
    public TypeCarte getTypeCarte(){
        return type;
    }
    
    
    /**
     * Retourne l'effet de la carte, en fonction de la saison en cours et de l'action voulue.
     * @param a
     * 		L'action choisie par le Joueur.
     * @param s
     * 		La saison en cours.
     * @return L'effet de la carte.
     */
    public int getEffet(TypeAction a,TypeSaison s){
        return this.effet[a.toInteger()][s.toInteger()];
    }
    
    /**
     * Change l'état d'une carte.
     * @param b
     * 		Le booléen indiquant si une carte est posée ou non.
     */
    public void setPose(boolean b)
    {
        this.pose=b;
    }
    
    /**
     * @see core.Jouable#jouer(Joueur, Joueur, TypeAction, TypeSaison)
     */
    public abstract void jouer(Joueur lanceur, Joueur cible, TypeSaison s);
}
