package core;

public class PartieRapide extends Partie {

    public PartieRapide(int nbJH, int nbJIA){ 
        
        super(nbJH,nbJIA);
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
