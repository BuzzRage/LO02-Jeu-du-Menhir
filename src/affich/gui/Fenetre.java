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
public class Fenetre extends JFrame{
    CardLayout cl = new CardLayout();
    JPanel content = new JPanel();

    public Fenetre(){
        this.setTitle("Jeu du Menhir");
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        VueCarteIngredient card1 = new VueCarteIngredient("RayonLune1.png");
        VueCarteIngredient card2 = new VueCarteIngredient("EspritDolmen1.png");
        VueCarteIngredient card3 = new VueCarteIngredient("FontainePure1.png");
        VueCarteIngredient card4 = new VueCarteIngredient("LarmeDriade1.png");
        VueCarteIngredient card5 = new VueCarteIngredient("lol.png");
        VueCarteIngredient card6 = new VueCarteIngredient("lol.png");

        content.setLayout(new GridLayout(2,2,5,5));
        content.add(card1);
        content.add(card2);
        content.add(card3);
        content.add(card4);

        this.getContentPane().add(card5,BorderLayout.EAST);
        this.getContentPane().add(card6,BorderLayout.WEST);
        this.getContentPane().add(content,BorderLayout.CENTER);
        this.setVisible(true);
    }	

}
