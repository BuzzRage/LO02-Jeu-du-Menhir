package core;

public class SafeStrat implements Strategy {
    @Override
    public void decider(Partie p,Joueur jIA){	
    	
        if(p.getSaison()==TypeSaison.HIVER){
            jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
            jIA.getChoixJoueur().setCarte(jIA.getCarte(0));
        }
        else
        {
            if(jIA.getNbrGraines()==0) //Si on a pas de graines, on en prend (le plus possible)
            {
                jIA.getChoixJoueur().setAction(TypeAction.GEANT);
                jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.GEANT,p.getSaison()));
            }
            else if(jIA.getNbrGraines()>0) //Si on a des graines, on les fait pousser.
            {
                jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
                jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.ENGRAIS,p.getSaison(),jIA.getNbrGraines()));
            }
        }
        
    }
	

    @Override
    public boolean choixAllie(){
    	return false;
    }
    @Override
    public boolean deciderReaction(Joueur jIA,Joueur attaquand,TypeSaison s){
        return false;
    }
    @Override
    public boolean jouerAllie(Partie part){
        return false;
    }
}
