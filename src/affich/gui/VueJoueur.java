/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich.gui;
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Apache
 */
public class VueJoueur extends JPanel{
    private JLabel nom;
    private JLabel points;
    private JLabel menhirs;
    private JLabel graines;
    private JPanel pan;
    
    
    public VueJoueur(){
        this("Joueur X",0,0,0);
    }
    public VueJoueur(String nom,int points, int menhirs, int graines){
        this.nom.setText(nom);
        this.points.setText(Integer.toString(points));
        this.menhirs.setText(Integer.toString(menhirs));
        this.graines.setText(Integer.toString(graines));
        //pan = new 
    }
}
