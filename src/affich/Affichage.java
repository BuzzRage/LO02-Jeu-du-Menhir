/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich;

import java.util.*;

import core.*;

/**
 *
 * @author Apache
 */
public abstract class Affichage implements Observer {
    protected TypeSaison saisonActuelle;
    protected Joueur joueurActif;
    protected ArrayList<Joueur> listeJoueurs;
    protected int nbMancheActuelle;
    protected final int NB_J_MAX = 6;
    
    
    
    public abstract void displayTour();
    public abstract void displayFinManche();
    public abstract void displayAction();
    public abstract boolean displayChoixAllie();
    public abstract void displayTypeAllie();
    public abstract void displayGagnant(ArrayList<Joueur> palmares);
    public abstract void displayNbManche();
    public abstract boolean displayReaction();
    public abstract boolean displayChoixCarteTaupe();
    public abstract ChoixFinPartie displayChoixFinPartie();
    public abstract int getNbJoueurs();
    public abstract boolean getTypePartie();
    public abstract void displayJoueurCible();
    
    
    
    
    public void update(Observable o, Object arg1) {
	if(o instanceof Partie){
            Partie part = (Partie)o;
	    saisonActuelle=(part.getSaison());
            joueurActif = part.getJoueurActif();
            listeJoueurs = part.getListeJoueurs();
            nbMancheActuelle = part.getNbrManche();
	}	
    }
    
    
}
