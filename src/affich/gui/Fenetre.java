package affich.gui;
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import core.*;

/**
 *
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
        this.setTitle("Jeu du Menhir");
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
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
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(content,BorderLayout.CENTER);
        this.getContentPane().add(saison,BorderLayout.NORTH);
        
        this.setVisible(true);
    }

    public void setMain(LinkedList<CarteIngredient> deck){
        
        Iterator<CarteIngredient> it = deck.iterator();
        for(Iterator<VueCarteIngredient> iter = vueMain.iterator();iter.hasNext();){
            VueCarteIngredient vueCarte = iter.next();
            vueCarte.setCarteIng(it.next());
        }
        revalidate();
    }
    public void setSaison(TypeSaison typeSaison){
        saison.setText("Saison actuelle : "+typeSaison.toString());
        revalidate();
        repaint();
    }
    public void setJoueurs(ArrayList<Joueur> listeJoueurs){
        getContentPane().remove(nord);
        getContentPane().remove(sud);
        getContentPane().remove(est);
        getContentPane().remove(ouest);
        est = new JPanel();
        ouest = new JPanel();
        nord = new JPanel();
        sud = new JPanel();
        
        vueJoueurs= new ArrayList<>();
        
        for(Iterator<Joueur> it = listeJoueurs.iterator();it.hasNext();){
            Joueur j = it.next();
            VueJoueur vueJoueur = new VueJoueur(j);
            
            this.vueJoueurs.add(vueJoueur);
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
        
        this.getContentPane().add(est,BorderLayout.EAST);
        this.getContentPane().add(ouest,BorderLayout.WEST);
        this.getContentPane().add(nord,BorderLayout.NORTH);
        this.getContentPane().add(sud,BorderLayout.SOUTH);
        
        
        
        revalidate();
    }
    
    public ArrayList<VueCarteIngredient> getVuesCarteIng(){
        return vueMain;
    }

}
