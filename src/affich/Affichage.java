/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich;

import java.util.*;

import core.*;

/**
 *  La classe Affichage est une classe abstraite qui Observe la Partie.<br>
 *  Elle est munie des fonctions de bases nécessaires à l'affichage graphique des différents élèments et évènements du jeu.<br>
 *  Elle possède les attributs suivants:<br>
 *  <code>protected final int NB_J_MAX</code> pour fixer le nombre de Joueurs maximum.<br>
 *  Les attributs suivant sont les références sur ceux de la Partie:<br>
 *  <code>protected TypeSaison saisonActuelle</code>
 *  <code>protected Joueur joueurActif</code>
 *  <code>protected ArrayList&lsaquo;Joueur&rsaquo; listeJoueurs</code>
 *  <code>protected int nbMancheActuelle</code>
 * 
 */
public abstract class Affichage implements Observer {
    protected TypeSaison saisonActuelle;
    protected Joueur joueurActif;
    protected ArrayList<Joueur> listeJoueurs;
    protected int nbMancheActuelle;
    protected final int NB_J_MAX = 6;
    
    
    
    /**
     * Appelle les méthodes d'affichage nécessaire pour un tour de jeu.
     */
    public abstract void displayTour();
    
    
    /**
     * Affiche la fin de de la manche et son gagnant.
     */
    public abstract void displayFinManche();
    
    /**
     * Affiche l'action effectué par le joueur en cours.
     */
    public abstract void displayAction();
    
    
    /**
     * Affiche le choix entre prendre une carte allié ou prendre deux graines. 
     * @return true si le joueur choisi de prendre une carte allié. false si le joueur choisi de prendre 2 graines.
     * 
     */
    public abstract boolean displayChoixAllie();
    
    /**
     * Affiche la carte tirée par le joueur actif s'il est humain.
     */
    public abstract void displayTypeAllie();
    
    /**
     * Affiche le palmares.
     * @param palmares
     * 		Le classement des joueurs.
     */
    public abstract void displayGagnant(ArrayList<Joueur> palmares);
    
    /**
     * Affiche le numéro de la manche.
     */
    public abstract void displayNbManche();
    
    /**
     * Demande au joueur s'il veut réagir contre une attaque de farfadet.
     * @return
     * 		true si le joueur décide de réagir. false sinon.
     */
    public abstract boolean displayReaction();
    
    /**
     * Demande au joueurActif s'il veut jouer sa carte Taupe
     * @return true si le joueur joue sa carte taupe. false sinon.
     */
    public abstract boolean displayChoixCarteTaupe();
    
    /**
     * Demande à l'utilisateur s'il veut une revanche, faire une nouvelle partie ou quitter.
     * @return Le choix du joueur.
     */
    public abstract ChoixFinPartie displayChoixFinPartie();
    
    /**
     * Demande à l'utilisateur de rentrer le nombre de joueurs.
     * @return 
     * 		Le nombre de joueur
     * @throws InputMismatchException
     */
    public abstract int getNbJoueurs();
    
    /**
     * Demande à l'utilisateur de choisir le type de partie.
     * @return
     * 		true si l'utilisateur choisi une partie simple. false sinon.
     * @throws InputMismatchException
     */
    public abstract boolean getTypePartie();
    
    /**
     * Demande au joueur de choisir sa cible.
     */
    public abstract void displayJoueurCible();
    
    
    
    
    /**
     * Met à jour les informations qu'observe Affichage sur la Partie.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
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
