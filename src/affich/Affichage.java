/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich;

/**
 *
 * @author Apache
 */
public class Affichage {
    private static Affichage instance = null;
    public static Affichage getInstance(){
        if(instance ==null)
            instance = new Affichage();
        return instance;
    }
    
    
}
