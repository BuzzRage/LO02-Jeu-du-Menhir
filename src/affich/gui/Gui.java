/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich.gui;
import affich.*;
import core.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Apache
 */
public class Gui extends Affichage{
    private Fenetre fen;
    private static Gui instance;
    private String[] options = new String[NB_J_MAX];
    private String title = "Choix du nombre de joueurs";
    private String message = "Combien de joueurs souhaites-tu?";
    
    private Gui(){
        fen = new Fenetre();
    }
    public static Gui getInstance(){
        if(instance == null)
            instance = new Gui();
        return instance;
    }
    
    private boolean getYesOrNo(Object message, 
            String title,Object[] options,Object initialSelected){
        
        
        int res = JOptionPane.showOptionDialog(fen, 
                message,title, 
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,
                options,initialSelected);
        if(res==JOptionPane.YES_OPTION)
            return true;
        else 
            return false;
    }
    private int getNumber(Object message,String title,Object[] options,Object initialSelected){
        String[] vals = ((String)JOptionPane.showInputDialog(fen, 
                 message, title,
                JOptionPane.QUESTION_MESSAGE,null,options,initialSelected)).split("\\s+");
        return Integer.parseInt(vals[0]);
    }
    private void messageBox(Object message,String title){
        JOptionPane.showMessageDialog(fen, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean GuiOrConsole(){
        String[] options = new String[2];
        String title = "Choix du type d'interface";
        String message = "Quel type d'interface souhaites-tu?";
        options[0]="Graphique";
        options[1]="Console";
        int res = JOptionPane.showOptionDialog(null, 
                message,title, 
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,
                options,null);
        return res==JOptionPane.YES_OPTION;
        
    }
    
    public void displayTour(){
        
    }
    
    public void displayFinManche(){
        
    }
    
    public void displayAction(){
        
    }
    
    public boolean displayChoixAllie(){
        options = new String[2];
        title = "Choix";
        message = "Veux tu obtenir 2 graines ou une carte Allié?";
        options[0]="Carte Allié";
        options[1]="2 Graines";
        return this.getYesOrNo(message, title, options, options[0]);
                
    }
    
    public void displayTypeAllie(){
        String typeCarteAl="";
        if(joueurActif.getCarteAl() instanceof CarteChien)
            typeCarteAl="Chien";
        else
            typeCarteAl="Taupe";
        title = "Type de carte Allé";
        
        message = "Tu as pioché une carte "+typeCarteAl;
        if(joueurActif.isHuman())
            messageBox(message, title);
    }
    
    public void displayGagnant(ArrayList<Joueur> palmares){
        
    }
    
    public void displayNbManche(){
        title = "Numéro de la manche";
        message = "Début de la manche "+ this.nbMancheActuelle;
        this.messageBox(message, title);
        
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
        options = new String[NB_J_MAX];
        title = "Choix du nombre de joueurs";
        message = "Combien de joueurs souhaites-tu?";
        for(int i = 0;i<NB_J_MAX-1;i++){
            options[i]= (i+2)+" Joueurs";
        }
        return this.getNumber(message, title, options, options[0]);
    }
    
    public boolean getTypePartie(){
        options = new String[2];
        title = "Choix du type de partie";
        message = "Quel type de partie souhaites-tu?";
        options[0]="Avancee";
        options[1]="Rapide";
        return this.getYesOrNo(message, title, options, options[1]);
    }
    
    public void displayJoueurCible(){
        
    }
    
}
