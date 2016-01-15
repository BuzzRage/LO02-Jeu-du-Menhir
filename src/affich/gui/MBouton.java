package affich.gui;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;

import core.CarteIngredient;
import core.TypeAction;

/**
 * La classe MBouton hérite de JButton et implémente l'interface MouseListener.<br>
 * Elle permet de creer un bouton cliquable représentant l'action du joueur humain.<br>
 * Un son dépendant de l'action choisie est joué lorsque l'on clique.<br>
 * Cette classe possède les attributs suivants:<br>
 *  <code>private static String location="sons/"; </code> le répertoire contenant les sons.<br>
 *  <code>private File file; </code> le fichier contenant le son.<br>
 *  <code>private final TypeAction action; </code> l'action choisie.<br>
 *  <code>private CarteIngredient carte; </code> la carte liée.<br>
 */
public class MBouton extends JButton implements MouseListener{
    private static String location="sons/";
    private File file;
    private final TypeAction action;
    private CarteIngredient carte; 
    
    public MBouton(TypeAction action){
        this("",action);
    }
    
    public MBouton(String str, TypeAction act){
        super(str);
        setBorderPainted(false);
        action=act;
        file = new File(location+action.getUrl());
        addMouseListener(this);
        setOpaque(false);
        setContentAreaFilled(false);
        
    }
    
    /**
     * Permet de jouer un son en récupérant l'AudioInputStream du fichier file. 
     */
    public synchronized void playSound() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                    clip.open(inputStream);
                    clip.start(); 
                } 
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
    
    public CarteIngredient getCarte(){
        return carte;
    }
    
    public TypeAction getTypeAction(){
        return action;
    }
    public void setCarte(CarteIngredient c){
        carte = c;
    }
    
   
    @Override
    public void mouseExited(MouseEvent event){
        setBorderPainted(false);
    }
    @Override
    public void mouseEntered(MouseEvent event){
        setBorderPainted(true);
    }
    @Override
    public void mouseReleased(MouseEvent event){}
    @Override
    public void mousePressed(MouseEvent event){
        playSound();
    }
    @Override
    public void mouseClicked(MouseEvent event){
       
    }
}
