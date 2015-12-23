package core;

import java.util.*;


public class PartieAvancee extends Partie {


    
    public PartieAvancee(int nbJH,int nbJIA){
        super(nbJH,nbJIA);
        super.setNbrManches(nbJH+nbJIA);
        partAvancee = true;
    }
   
    
    /**
     * @see core.Partie#recupCartes()
     */
    public void recupCartes() {
        super.recupCartes();
        for(Joueur j:listeJoueurs){
            if(j.hasAllie())
                this.listeCarteAl.add(j.rendreCarteAl());
        }
    }
    
    

    /**
     * @see core.Partie#creerCartes()
     */
    public void creerCartes(){
        super.creerCartes();
        listeCarteAl  = new LinkedList<>();

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
