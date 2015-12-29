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
    private JLabel nom;
    private JLabel points;
    private JLabel menhirs;
    private JLabel graines;
    private Joueur joueur;
    
    public VueJoueur (Joueur j){
	this.joueur=j;
	this.joueur.addObserver(this);
	
    }
    public void update(Observable o, Object arg) {
	if(o.hasChanged()){
		if(o instanceof Joueur){
		    
		   //Faire les changements necessaire (modifier l'affichage du nb de graines, de points etc.. 
		}
	}

    }
    
    
    
}
