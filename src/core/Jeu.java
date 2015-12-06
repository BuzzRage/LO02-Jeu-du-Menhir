/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;
import MenhirExceptions.WrongNumberException;
import console.*;
import java.util.InputMismatchException;

/**
 *
 * @author Apache
 */
public class Jeu {
    
    private ChoixFinPartie choixFinPartie;
    private Console console;
    private Partie part;
    private boolean continuer;
    
    public Jeu(){
        console = Console.getInstance();
        continuer = false;
        choixFinPartie = ChoixFinPartie.NOUVELLE_PARTIE;
    }
    
    public void lancer(){
        while(choixFinPartie!=ChoixFinPartie.QUITTER){
            if(choixFinPartie==ChoixFinPartie.NOUVELLE_PARTIE){
                initJeu();
            }
                
            part.lancerPartie();
            choixFinPartie =console.displayChoixFinPartie();

        }
    }
    private void initJeu(){
        int nbJoueurs = 0;
        int nbJH = 1;
        while(!continuer){
            try{
                nbJoueurs = console.getNbJoueurs();
                continuer = true;
            }
            catch(WrongNumberException | InputMismatchException e){
                System.out.println(e.getMessage());
            }

        }
        continuer=false;
        while(!continuer){
            try{
                if(console.getTypePartie(nbJH, nbJoueurs))
                    part = new PartieAvancee(nbJH,nbJoueurs-nbJH);
                else
                    part = new PartieRapide(nbJH,nbJoueurs-nbJH);
                continuer = true;
            }
            catch(WrongNumberException e){
                System.out.println(e.getMessage());
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        continuer=false;
    }
}
