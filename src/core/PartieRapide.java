package core;
import affich.Affichage;

public class PartieRapide extends Partie {

    public PartieRapide(int nbJH, int nbJIA,Affichage affich){ 
        
        super(nbJH,nbJIA,affich);
        super.setNbrManches(1);
        partAvancee = false;
        
    }
    @Override
    public void initManche(){
        super.initManche();
        for(Joueur j:listeJoueurs) 
    	{
            j.setNbrGraines(2);
    	}
    }
}
