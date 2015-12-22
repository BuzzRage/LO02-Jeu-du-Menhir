/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 * La classe ChoixJoueur permet de stocker les informations concernant l'action à venir d'un Joueur.<br>
 * Elle possède les attributs suivants:<br>
 * <code>private Joueur cible</code> pour stocker le Joueur ciblé (peut être <code>null</code>)<br>
 * <code>private TypeAction action</code> pour garder en mémoire l'action sélectionnée<br>
 * <code>private Carte carte</code> pour stocker la Carte à jouer.<br>
 * 
 * @see core.TypeAction
 * 
 */
public class ChoixJoueur {
    private Joueur cible;
    private TypeAction action;
    private Carte carte;
    
    /**
     * @return Le joueur prit pour cible.
     */
    public Joueur getCible(){
        return cible;
    }
    
    /**
     * @param j 
     * 		Joueur à prendre pour cible.
     */
    public void setCible(Joueur j){
        cible=j;
    }
    
    /**
     * @return L'action choisi par le joueur. 
     */
    public TypeAction getAction(){
        return action;
    }
    
    /**
     * @param a
     * 		Action à parametrer.
     */
    public void setAction(TypeAction a){
        action=a;
    }
    
    /**
     * @return La Carte choisi par le joueur.
     */
    public Carte getCarte(){
        return carte;
    }
    
    /**
     * @param c
     * 		La Carte à parametrer.
     */
    public void setCarte(CarteIngredient c){
        carte = c;
    }
    
}
