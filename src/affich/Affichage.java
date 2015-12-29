/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Apache
 */
public class Affichage implements Observer {
    private static Affichage instance = null;
    
    public static Affichage getInstance(){
        if(instance ==null)
            instance = new Affichage();
        return instance;
    }
    public void update(Observable arg0, Object arg1) {
	// TODO Auto-generated method stub
	
    }
    
    
}
