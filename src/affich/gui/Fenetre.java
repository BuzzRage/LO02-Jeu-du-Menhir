/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich.gui;
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import core.*;
/**
 *
 * @author Apache
 */
public class Fenetre extends JFrame{
    JPanel content = new JPanel();
    LinkedList<CarteIngredient> main = new LinkedList<>();
    ArrayList<VueCarteIngredient> vueMain = new ArrayList<>();

    /**
     * Règle la taille de la fenetre le titre et affiche 4 CarteIngredient face cachée.<br>
     * 
     */
    public Fenetre(){
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

        this.getContentPane().add(content,BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    public void setMain(LinkedList<CarteIngredient> deck){
        this.main = deck;
        updateCartes();
    }
    
    public ArrayList<VueCarteIngredient> getVuesCarteIng(){
        return vueMain;
    }
    
    private void updateCartes(){
        Iterator<CarteIngredient> it = main.iterator();
        for(Iterator<VueCarteIngredient> iter = vueMain.iterator();iter.hasNext();){
            VueCarteIngredient vueCarte = iter.next();
            vueCarte.setCarteIng(it.next());
        }
            
        
    }

}
