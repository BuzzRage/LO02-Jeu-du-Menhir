/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich.gui;
import affich.*;
import core.ChoixFinPartie;
import core.Joueur;
import java.util.ArrayList;

/**
 *
 * @author Apache
 */
public class Gui extends Affichage{
    private Fenetre fen;
    public Gui(){
        fen = new Fenetre();
    }
    
    public void displayTour(){
        
    }
    
    public void displayFinManche(){
        
    }
    
    public void displayAction(){
        
    }
    
    public boolean displayChoixAllie(){
        
        return false;
                
    }
    
    public void displayTypeAllie(){
        
    }
    
    public void displayGagnant(ArrayList<Joueur> palmares){
        
    }
    
    public void displayNbManche(){
        
    }
    
    public boolean displayReaction(){
        
        return false;
    }
    
    public boolean displayChoixCarteTaupe(){
        
        return false;
    }
    
    public ChoixFinPartie displayChoixFinPartie(){
        
        return ChoixFinPartie.QUITTER;
    }
    
    public int getNbJoueurs(){
        
        return 0;
    }
    
    public boolean getTypePartie(){
        return false;
    }

    @Override
    public void displayJoueurCible() {
	// TODO Auto-generated method stub
	
    }
    
}
