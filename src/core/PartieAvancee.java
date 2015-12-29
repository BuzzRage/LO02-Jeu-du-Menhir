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
}
