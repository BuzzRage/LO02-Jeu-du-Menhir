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
import java.util.*;
import java.awt.event.*;

/**
 *
 * @author Apache
 */
public class Gui extends Affichage implements ActionListener{
    private Fenetre fen;
    private static Gui instance;
    private String[] options = new String[NB_J_MAX];
    private String title;
    private String message;
    private boolean continuer;
    private JoueurHumain utilisateur;
    
    private Gui(){
        continuer = false;
        fen = new Fenetre();
        for(Iterator<VueCarteIngredient> it = fen.getVuesCarteIng().iterator();it.hasNext();){
            VueCarteIngredient vueCarte = it.next();
            vueCarte.getBoutonEngrais().addActionListener(this);
            vueCarte.getBoutonGeant().addActionListener(this);
            vueCarte.getBoutonFarfadet().addActionListener(this);
            vueCarte.setBoutonsEnabled(false);
            
        }
        
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
        return res==JOptionPane.YES_OPTION;
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
        while(!continuer){
            try{
                Thread.sleep(30);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
    }
    
    public void actionPerformed(ActionEvent event){
        
    }
    
    public void displayFinManche(){
        Joueur meuneur=listeJoueurs.get(0);
        int menMax=-1;
        for(Joueur j:listeJoueurs){
            if(j.getNbrMenhirs()>menMax){
                menMax = j.getNbrPoints();
                meuneur = j;
            }
        }
        title = "Fin de manche";
        message = "Le meneur est le joueur "+meuneur.getNbr()+" avec "+menMax+" points!";
        messageBox(message,title);
    }
    
    public void displayAction(){
        title = "Action effectuée";
        switch(this.joueurActif.getChoixJoueur().getAction()){
            case GEANT:
                message = "Le joueur " + joueurActif.getNbr(); 
                message +=" obtient ";
                message +=joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.GEANT)
                        +"Graines";
                break;
            case ENGRAIS:
                message = "Le joueur " + joueurActif.getNbr() + "transforme "
                        +joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.ENGRAIS)
                        +" graines en menhirs";
                break;
            case FARFADET:
                message = "Le joueur " + joueurActif.getNbr() + " vole ";
                message+= joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.FARFADET)
                       + " graines ";
                message+= "au joueur "+joueurActif.getChoixJoueur().getCible().getNbr();
                break;
        }
        this.messageBox(message, title);
        
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
        fen.setMain(utilisateur.getCartes());
        
    }
    
    public boolean displayReaction(){
        
        return false;
    }
    
    public boolean displayChoixCarteTaupe(){
        
        return false;
    }
    
    public ChoixFinPartie displayChoixFinPartie(){
        options = new String[3];
        title = "Fin de partie";
        message = "Que veux tu faire?";
        options[0]="Revanche (mêmes paramètres)";
        options[1]="Nouvelle Partie";
        options[2]="Quitter";
        
        //TODO JOptionPane avec 3 choix
        
        return ChoixFinPartie.QUITTER;
    }
    
    public int getNbJoueurs(){
        options = new String[NB_J_MAX-1];
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

    public void update(Observable obs,Object o){
        super.update(obs, o);
        if(obs instanceof Partie){
            utilisateur = ((Partie)obs).getJoueurHumain();
        }
    }
    
}
