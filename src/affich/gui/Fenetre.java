package affich.gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.CarteIngredient;
import core.Joueur;
import core.TypeSaison;

/**
 *
 *  La classe Fenetre est une classe qui hérite de Jframe.<br>
 *  Elle est munie des fonctions de bases nécessaires à l'affichage graphique des cartes ingrédients et des vues joueurs.<br>
 *  Elle possède les attributs suivants:<br>
 *  <code>private JPanel content,est,ouest,nord, sud;</code> sont les containers de la fenètre.<br>
 *  <code>private ArrayList&lsaquo;VueCarteIngredient&rsaquo; vueMain = new ArrayList&lsaquo;&rsaquo;();</code> est une collection de <code>VueCarteIngredient</code> qui représente la main d'un joueur.<br>
 *  <code>private ArrayList&lsaquo;VueJoueur&rsaquo; vueJoueurs = new ArrayList&lsaquo;&rsaquo;();</code> est une collection de <code>VueJoueurs</code> qui représente les  joueurs de la Partie.<br>
 *  <code>private JLabel saison;</code> est un label permettant d'afficher la saison en cours.
 *
 */
public class Fenetre extends JFrame{
    private JPanel content,est,ouest,nord, sud;
    private ArrayList<VueCarteIngredient> vueMain = new ArrayList<>();
    private ArrayList<VueJoueur> vueJoueurs = new ArrayList<>();
    private JLabel saison;

    /**
     * Règle la taille de la fenetre le titre et affiche 4 CarteIngredient face cachée.<br>
     * 
     */
    public Fenetre(){
        ouest = new JPanel();
        est = new JPanel();
        nord = new JPanel();
        sud = new JPanel();
        content = new JPanel();
        saison=new JLabel("Saison actuelle : "+TypeSaison.PRINTEMPS.toString());
        
        setTitle("Jeu du Menhir");
        setSize(1000, 600);
        setMinimumSize(new Dimension(1000,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        VueCarteIngredient card1 = new VueCarteIngredient();
        VueCarteIngredient card2 = new VueCarteIngredient();
        VueCarteIngredient card3 = new VueCarteIngredient();
        VueCarteIngredient card4 = new VueCarteIngredient();
        vueMain.add(card1);
        vueMain.add(card2);
        vueMain.add(card3);
        vueMain.add(card4);

        content.setLayout(new GridLayout(2,2,5,5));
        content.add(card1);
        content.add(card2);
        content.add(card3);
        content.add(card4);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(content,BorderLayout.CENTER);
        getContentPane().add(saison,BorderLayout.NORTH);
        
        setVisible(true);
    }

    
    /**
     * Paramètre vueMain en lui reparamètrant les cartes qu'elle représente.<br>   
     * @param deck
     * 		Le deck remplaçant les cartes représentées par la vue.
     */
    public void setMain(LinkedList<CarteIngredient> deck){
        Iterator<CarteIngredient> it = deck.iterator();
        for(Iterator<VueCarteIngredient> iter = vueMain.iterator();iter.hasNext();){
            VueCarteIngredient vueCarte = iter.next();
            vueCarte.setCarteIng(it.next());
        }
        revalidate();
    }
    /**
     * Paramètre la saison en cours.
     * @param typeSaison
     * 		La saison voulue.
     */
    public void setSaison(TypeSaison typeSaison){
        saison.setText("Saison actuelle : "+typeSaison.toString());
        revalidate();
        repaint();
    }
    
    /**
     * Paramètre et affiche la liste des VueJoueur en lui reparamètrant les joueurs qu'elle représente.<br> 
     * @param listeJoueurs
     * 		La liste de joueurs que l'on veut représenter.
     */
    public void setJoueurs(ArrayList<Joueur> listeJoueurs){
        getContentPane().remove(nord);
        getContentPane().remove(sud);
        getContentPane().remove(est);
        getContentPane().remove(ouest);
        est = new JPanel();
        ouest = new JPanel();
        nord = new JPanel();
        sud = new JPanel();
        
        vueJoueurs = new ArrayList<>();
        
        for(Iterator<Joueur> it = listeJoueurs.iterator();it.hasNext();){
            Joueur j = it.next();
            VueJoueur vueJoueur = new VueJoueur(j);
            
            vueJoueurs.add(vueJoueur);
            vueJoueur.setJoueur(j);
        }
        
        sud.add(vueJoueurs.get(0));
        GridLayout gl;
        
        switch(vueJoueurs.size()){
            case 2:
                nord.add(vueJoueurs.get(1));
                break;
            case 3:
                nord.add(vueJoueurs.get(1));
                nord.add(vueJoueurs.get(2));
                break;
            case 4:
                est.add(vueJoueurs.get(3));
                nord.add(vueJoueurs.get(2));
                ouest.add(vueJoueurs.get(1));
                break;
            case 5:
                gl=new GridLayout(1,2);
                nord.setLayout(gl);
                est.add(vueJoueurs.get(4));
                nord.add(vueJoueurs.get(2));
                nord.add(vueJoueurs.get(3));
                ouest.add(vueJoueurs.get(1));
                
                break;
            case 6:
                gl = new GridLayout(2,1,100,50);
                est.setLayout(gl);
                ouest.setLayout(gl);
                
                est.add(vueJoueurs.get(4));
                est.add(vueJoueurs.get(5));
                nord.add(vueJoueurs.get(3));
                ouest.add(vueJoueurs.get(2));
                ouest.add(vueJoueurs.get(1));
                break;
                       
        }
        sud.setVisible(true);
        nord.setVisible(true);
        if(listeJoueurs.size()>3){
            est.setVisible(true);
            ouest.setVisible(true);
        }
        
        getContentPane().add(est,BorderLayout.EAST);
        getContentPane().add(ouest,BorderLayout.WEST);
        getContentPane().add(nord,BorderLayout.NORTH);
        getContentPane().add(sud,BorderLayout.SOUTH);
        
        revalidate();
    }
    
    /**
     * 	Renvoie la vueMain de la fenêtre.
     * @return La liste des VueCartesIngredients.
     */
    public ArrayList<VueCarteIngredient> getVuesCarteIng(){
        return vueMain;
    }

}
