package core;

import affich.Affichage;

public class PartieAvancee extends Partie {


    
    public PartieAvancee(int nbJH,int nbJIA,Affichage affich){
        super(nbJH,nbJIA,affich);
        super.setNbrManches(nbJH+nbJIA);
        partAvancee = true;
    }
   
    
    /**
     * @see core.Partie#recupCartes()
     */
    public void recupCartes() {
        super.recupCartes();
        for(Joueur j:listeJoueurs){
            if(j.getCarteAl() instanceof CarteAllie)
                this.listeCarteAl.add(j.rendreCarteAl());
        }
    }
}
