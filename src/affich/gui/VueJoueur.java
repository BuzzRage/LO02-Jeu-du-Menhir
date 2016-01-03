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
    
    public void update(Observable o, Object arg) {
	if(o instanceof Joueur){
                Joueur j = (Joueur)o;
		   points.setText("Points: "+j.getNbrPoints());
		   menhirs.setText("Menhirs: "+j.getNbrMenhirs());
		   graines.setText("Graines: "+j.getNbrGraines());
		   repaint();
	    }
    }
   
    public VueJoueur(Joueur j){
	joueur=j;
	joueur.addObserver(this);
        nom = new JLabel("Joueur "+j.getNbr());
        points = new JLabel("Points: "+j.getNbrPoints());
        menhirs = new JLabel("Menhirs: "+j.getNbrMenhirs());
        graines = new JLabel("Graines: "+j.getNbrGraines());
        GridLayout gl = new GridLayout(2,2,10,10);
        
        this.setLayout(gl);
        this.add(nom);
        this.add(points);
        this.add(graines);
        this.add(menhirs);
        this.setSize(100, 20);
        this.setVisible(true);
        repaint();
    }
}
