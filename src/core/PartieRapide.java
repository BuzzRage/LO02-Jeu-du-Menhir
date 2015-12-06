package core;
import console.*;

public class PartieRapide extends Partie {

    public PartieRapide(int nbJH, int nbJIA){ 
        
        super(nbJH,nbJIA);
        super.setNbrManches(1);
        
    }
    @Override
    public void initManche(){
        super.initManche();
        for(Joueur j:listeJoueurs) 
    	{
            j.setNbrGraines(2);
    	}
    }
    public void lancerPartie(){
        initPartie();
        do{
            if(!tourRunning)
                this.initManche();
            for(Joueur j:listeJoueurs){
                joueurActif = j;
                joueurActif.jouerTour(this);
                joueurActif.jouerCarte(saison);
                console.displayAction(joueurActif,saison);
            }
            nextTour();
            if(!tourRunning)
                recupCartes();
            
        }while(running);
        listeJoueurs.add(listeJoueurs.remove(0));
        console.displayGagnant(this.getPalmares());
    }
    
}
