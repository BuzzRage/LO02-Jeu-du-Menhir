package core;

import java.util.*;
import console.*;


public class PartieAvancee extends Partie {

    private LinkedList<CarteAllie> listeCarteAl;
    
    public PartieAvancee(int nbJH,int nbJIA){
        
        super(nbJH,nbJIA);
        listeCarteAl  = new LinkedList<>();
        super.setNbrManches(nbJH+nbJIA);
        
        
        
    }
    public void lancerPartie(){
        initPartie();
        do{
            if(!tourRunning){
                initManche();
                console.displayNbManche(this);
            }
            for(Joueur j:listeJoueurs){
                joueurActif = j;
                if(nbrTourActuel==1){
                        if(joueurActif.choixAllie())
                                distribCarteAl(j);
                        else
                            joueurActif.setNbrGraines(2);
                }
                if(joueurActif.hasAllie()&&joueurActif.getCarteAl() instanceof CarteTaupe)
                    joueurActif.jouerAllie(this);
                joueurActif.jouerTour(this);
                if(joueurActif.getChoixJoueur().getAction()==TypeAction.FARFADET)
                    if(joueurActif.getChoixJoueur().getCible().hasAllie())
                        if(joueurActif.getChoixJoueur().getCible().getCarteAl() instanceof CarteChien)
                            joueurActif.getChoixJoueur().getCible().deciderReaction(this);
                console.displayAction(joueurActif,saison);
                joueurActif.jouerCarte(saison);
            }
            nextTour();
            if(!tourRunning){
                this.recupCartes();
                console.displayFinManche(listeJoueurs);
            } 
        }while(running);
        listeJoueurs.add(listeJoueurs.remove(0));
        console.displayGagnant(this.getPalmares());
    }
    
    @Override
    public void recupCartes() {
        super.recupCartes();
        for(Joueur j:listeJoueurs){
            if(j.hasAllie())
                this.listeCarteAl.add(j.rendreCarteAl());
            
            
        }
    }
    
    public void distribCarteAl(Joueur j){
        Collections.shuffle(this.listeCarteAl);
        j.setCarteAllie(this.listeCarteAl.pop());
        
    }
    @Override
    public void creerCartes(){
        super.creerCartes();
        int[] taupe1Effet       = {1, 1, 1, 1};
        int[] taupe2Effet       = {0, 2, 2, 0};
        int[] taupe3Effet       = {0, 1, 2, 1};
        int[] chien1Effet       = {2, 0, 2, 0};
        int[] chien2Effet       = {1, 2, 0, 1};
        int[] chien3Effet       = {0, 1, 3, 0};
        
        
        this.listeCarteAl.add(new CarteTaupe(taupe1Effet));
        this.listeCarteAl.add(new CarteTaupe(taupe2Effet));
        this.listeCarteAl.add(new CarteTaupe(taupe3Effet));
        this.listeCarteAl.add(new CarteChien(chien1Effet));
        this.listeCarteAl.add(new CarteChien(chien2Effet));
        this.listeCarteAl.add(new CarteChien(chien3Effet));
    }
    
    
}
