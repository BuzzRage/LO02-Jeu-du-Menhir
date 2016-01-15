package affich.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import affich.Affichage;
import core.CarteChien;
import core.ChoixFinPartie;
import core.Joueur;
import core.JoueurHumain;
import core.Partie;
import core.TypeAction;

/**
 * La classe GUI permet d'afficher du texte à l'écran dans une boite de dialogue, les Cartes du jeu, les Joueurs etc..<br>
 * Elle propose une vue graphique du jeu du menhir.<br>
 * Elle hérite de la classe Affichage.<br>
 * Elle n'est instanciable qu'une fois, grâce au patter singleton.<br>
 * Elle possède les attributs suivants:<br>
 *  <code>private Fenetre fen</code> la fenêtre sur laquelle on affichera les cartes.
 *  <code>private static Gui instance</code> l'instance de GUI (pattern singleton).
 *  <code>private String[] options = new String[NB_J_MAX]</code> un tableau de String générique pour gérer les textes sur les boutons.
 *  <code>private String title</code> pour titrer les boites de dialogues.
 *  <code>private String message</code> pour gérer le texte des boites de dialogues.
 *  <code>private boolean continuer</code> pour gérer les boucles.
 *  <code>private JoueurHumain utilisateur</code> pour avoir une référence sur le JoueurHumain de la Partie observée.
 */
public class Gui extends Affichage implements ActionListener{
    private Fenetre fen;
    private static Gui instance;
    private String[] options;
    private String title;
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
            
        }
        
    }
    
    /**
     * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance de GUI.
     * @return le GUI
     */
    public static Gui getInstance(){
        if(instance == null){
            instance = new Gui();
            
        }
        return instance;
    }
    
    /**
     * @see affich.Affichage#init()
     */
    public void init(){
        fen.setJoueurs(listeJoueurs);
    }
    
    private boolean getYesOrNo(Object message, 
            String title,Object[] options,Object initialSelected, Icon icon){
        
        while(true){
        int res = JOptionPane.showOptionDialog(fen, 
                message,title, 
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icon,
                options,initialSelected);
        if(res == JOptionPane.CLOSED_OPTION)
            JOptionPane.showMessageDialog(fen, "Vous devez faire un choix", "Erreur", JOptionPane.ERROR_MESSAGE);
        else
            return res==JOptionPane.YES_OPTION;
        }
    }
    
    
    private boolean getYesOrNo(Object message, 
            String title,Object[] options,Object initialSelected){
        return getYesOrNo(message,title,options,initialSelected,null);
        
    }
    private int getNumber(Object message,String title,Object[] options,Object initialSelected){
        while(true){
            Object result = JOptionPane.showInputDialog(fen, 
                     message, title,
                    JOptionPane.QUESTION_MESSAGE,null,options,initialSelected);
            try{
                String[] vals = ((String)result).split("\\s+");
                return Integer.parseInt(vals[0]);
            }
            catch(NullPointerException e){
                JOptionPane.showMessageDialog(fen, "Vous devez faire un choix", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void messageBox(Object message,String title){
        JOptionPane.showMessageDialog(fen, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Demande à l'utilisateur le choix de l'affichage.
     * @return
     * 		true si l'utilisateur choisi le GUI. false s'il choisi la Console.
     */
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
    
    /**
     * @see affich.Affichage#displayTour()
     */
    public void displayTour(){
        title ="Info";
        message = "C'est à toi de jouer!";
        messageBox(message,title);
        while(!continuer){
            try{
                Thread.sleep(30);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        continuer = false;
    }
    
    /** 
     * Permet de stocker l'action et la carte choisie. Fais le lien avec le core.
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event){
        if(event.getSource() instanceof MBouton){
            MBouton bouton = (MBouton)event.getSource();
            utilisateur.getChoixJoueur().setAction(
                    bouton.getTypeAction());
            utilisateur.getChoixJoueur().setCarte(
                    bouton.getCarte());
            
            utilisateur.notifyObservers();
            continuer = true;
        }
            
    }
    
    /**
     * @see affich.Affichage#displayFinManche()
     */
    public void displayFinManche(){
        super.displayFinManche();
        title = "Fin de manche";
        message = "Le meneur est le joueur "+meuneur.getNbr()+" avec "+meuneur.getNbrPoints()+" points!";
        messageBox(message,title);
    }
    
    /**
     * @see affich.Affichage#displayAction()
     */
    public void displayAction(){
        super.displayAction();
        fen.revalidate();
        fen.repaint();
        title = "Action effectuée";
        this.messageBox(message, title);
        
    }
    
    /**
     * @see affich.Affichage#displayChoixAllie()
     */
    public boolean displayChoixAllie(){
        options = new String[2];
        title = "Choix";
        message = "Veux tu obtenir 2 graines ou une carte Allié?";
        options[0]="Carte Allié";
        options[1]="2 Graines";
        return this.getYesOrNo(message, title, options, options[0]);
                
    }
    
    /**
     * @see affich.Affichage#displayTypeAllie()
     */
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
    
    /**
     * @see affich.Affichage#displayGagnant(java.util.ArrayList)
     */
    public void displayGagnant(ArrayList<Joueur> palmares){
        title="Palmarès";
        message="";
        for(Iterator<Joueur> it = palmares.iterator();it.hasNext();){
            Joueur j = it.next();
            message += "Joueur "+j.getNbr();
            message += j.getNbrPoints() + " points";
            message +="\n";
        }
        messageBox(message,title);
    }
    
    /**
     * @see affich.Affichage#displayNbManche()
     */
    public void displayNbManche(){
        title = "Numéro de la manche";
        message = "Début de la manche "+ this.nbMancheActuelle;
        this.messageBox(message, title);
        fen.setMain(utilisateur.getCartes());
        fen.revalidate();
        fen.repaint();
        
    }
    
    /**
     * @see affich.Affichage#displayReaction()
     */
    public boolean displayReaction(){
        options = new String[2];
        Icon icon = new ImageIcon(joueurActif.getChoixJoueur().getCible().getCarteAl().getTypeCarte().getImageUrl());
        message = "Le joueur "+joueurActif.getNbr()+" essaie de te voler ";
        message += joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.FARFADET)+" graines.";
        message += "Veux-tu utiliser ta carte Chien?\n";
        message += "Saison actuelle: "+saisonActuelle.toString();
        title = "Carte Allié";
        options[0]="Oui";
        options[1]="Non";
        return getYesOrNo(message, title, options, options[0], icon);
    }
    
    /**
     * @see affich.Affichage#displayChoixCarteTaupe()
     */
    public boolean displayChoixCarteTaupe(){
        Icon icon = new ImageIcon(joueurActif.getCarteAl().getTypeCarte().getImageUrl());
        message = "Voulez vous Joueur votre carte Taupe?\n";
        message += "Saison actuelle: "+saisonActuelle.toString();
        title = "Carte Allié";
        options[0]="Oui";
        options[1]="Non";
        return getYesOrNo(message, title, options, options[0], icon);
    }
    
    /**
     * @see affich.Affichage#displayChoixFinPartie()
     */
    public ChoixFinPartie displayChoixFinPartie(){
        options = new String[3];
        title = "Fin de partie";
        message = "Que veux tu faire?";
        options[0]="Revanche (mêmes paramètres)";
        options[1]="Nouvelle Partie";
        options[2]="Quitter";
        while(true){
            int res = JOptionPane.showOptionDialog(fen, message, title, 
                    JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            switch(res){
                case JOptionPane.YES_OPTION:
                    return ChoixFinPartie.REJOUER;
                case JOptionPane.NO_OPTION:
                    return ChoixFinPartie.NOUVELLE_PARTIE;
                case JOptionPane.CANCEL_OPTION:
                    fen.dispose();
                    return ChoixFinPartie.QUITTER;
                default:
                    JOptionPane.showMessageDialog(fen, "Vous devez faire un choix", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * @see affich.Affichage#getNbJoueurs()
     */
    public int getNbJoueurs(){
        options = new String[NB_J_MAX-1];
        title = "Choix du nombre de joueurs";
        message = "Combien de joueurs souhaites-tu?";
        for(int i = 0;i<NB_J_MAX-1;i++){
            options[i]= (i+2)+" Joueurs";
        }
        return this.getNumber(message, title, options, options[0]);
    }
    
    /**
     * @see affich.Affichage#getTypePartie()
     */
    public boolean getTypePartie(){
        options = new String[2];
        title = "Choix du type de partie";
        message = "Quel type de partie souhaites-tu?";
        options[0]="Avancee";
        options[1]="Rapide";
        return this.getYesOrNo(message, title, options, options[1]);
    }
    
    /**
     * @see affich.Affichage#displayJoueurCible()
     */
    public void displayJoueurCible(){
        Joueur[] items = new Joueur[NB_J_MAX-1];
        title = "Choix de la cible";
        message = "Qui souhaites-tu attaquer?";
        int i = 0;
        for(Iterator<Joueur> it = this.listeJoueurs.iterator();it.hasNext();){
            Joueur j = it.next();
            if(!joueurActif.equals(j)){
                items[i]=j;
                i++;
            }
        }
        while(true){
            Object result = JOptionPane.showInputDialog(fen, 
                     message, title,
                    JOptionPane.QUESTION_MESSAGE,null,items,null);
            if(result == null){
                JOptionPane.showMessageDialog(
                        fen, "Vous devez faire un choix", "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                Joueur vals = (Joueur)result;
                joueurActif.getChoixJoueur().setCible(vals);
                break;
            }
        }
    }

    /**
     * Met à jour la référence sur le JoueurHumain, en plus des autres informations.
     * @see affich.Affichage#update(java.util.Observable, java.lang.Object)
     */
    public void update(Observable obs,Object o){
        super.update(obs, o);
        if(obs instanceof Partie){
            utilisateur = ((Partie)obs).getJoueurHumain();
            fen.setSaison(saisonActuelle);
        }
    }
    
}
