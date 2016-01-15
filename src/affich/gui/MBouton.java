package affich.gui;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import core.*;

/**
 *
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
