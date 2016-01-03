/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich.gui;
import java.awt.*; 
import javax.swing.*;

import core.Joueur;

import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Apache
 */
public class VueJoueur extends JPanel implements Observer{
    
    private Object[][] data;
    private JTable table;
    Joueur joueur;
    
    public void update(Observable o, Object arg) {
	if(o instanceof Joueur){
                Joueur j = (Joueur)o;
                data[0][0] = "Joueur "+j.getNbr();
                data[0][1] = "Points: "+j.getNbrPoints();
                data[1][0] = "Graines: "+j.getNbrGraines();
                data[1][1] = "Menhirs: "+j.getNbrMenhirs();
		repaint();
	    }
    }
   
    public VueJoueur(Joueur j){
        //joueur =j;
	//joueur.addObserver(this);
        data = new Object[2][2];
        //data[0][0] = "Joueur "+j.getNbr();
        //data[0][1] = "Points: "+j.getNbrPoints();
        //data[1][0] = "Graines: "+j.getNbrGraines();
        //data[1][1] = "Menhirs: "+j.getNbrMenhirs();
        String[] title = {"",""};
        
        table = new JTable(data,title);
        table.setEnabled(false);
        
        this.add(table);
        this.setSize(100, 20);
        this.setVisible(true);
        repaint();
    }
    public void setJoueur(Joueur j){
        try{
            joueur.deleteObserver(this);
        }
        catch(NullPointerException e){
            
        }
        joueur =j;
        joueur.addObserver(this);
        update(joueur,null);
    }
    
}
