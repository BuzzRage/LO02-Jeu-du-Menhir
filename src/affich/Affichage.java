/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich;

import java.util.Observable;
import java.util.Observer;

import core.Partie;
import core.TypeSaison;

/**
 *
 * @author Apache
 */
public class Affichage implements Observer {
    protected TypeSaison saisonActuelle;
    private static Affichage instance = null;
    
    public static Affichage getInstance(){
        if(instance ==null){
            instance = new Affichage();
        }
        return instance;
    }
    public void update(Observable o, Object arg1) {
	if(o instanceof Partie){
	    saisonActuelle=((Partie) o).getSaison();
	}	
    }
    
    
}
