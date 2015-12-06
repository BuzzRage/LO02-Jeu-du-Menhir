/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Apache
 */
public class ChoixJoueur {
    private Joueur cible;
    private TypeAction action;
    private Carte carte;
    
    public Joueur getCible(){
        return cible;
    }
    public void setCible(Joueur j){
        cible=j;
    }
    public TypeAction getAction(){
        return action;
    }
    public void setAction(TypeAction a){
        action=a;
    }
    public Carte getCarte(){
        return carte;
    }
    public void setCarte(CarteIngredient c){
        carte = c;
    }
    
}
