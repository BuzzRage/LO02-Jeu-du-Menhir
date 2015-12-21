package core;

public class SafeStrat implements Strategy {
   /**
     * Cette classe est utilisée pour prendre une décision en fonction de l'état de la Partie.<br>
     * Elle implémente <code>public void decider(Partie p, Joueur jIA)</code> de l'interface Strategy. <br>
     * Cette stratégie est défensive, si le JoueurIA la possédant n'a pas de graines, il va chercher à en demander au Géant.<br>
     * La méthode decider paramètre le ChoixJoueur du JoueurIA, en définissant une cible, une carte et une action.<br>
     * 
     * @see core.Strategy#decider(core.Partie, core.Joueur)
     */
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
	

    /**
     * @see core.Strategy#choixAllie()
     */
    public boolean choixAllie(){
    	return false;
    }
    /**
     * @see core.Strategy#deciderReaction(core.Joueur, core.Joueur, core.TypeSaison)
     */
    public boolean deciderReaction(Joueur jIA,Joueur attaquand,TypeSaison s){
        return false;
    }
    
    /**
     * @see core.Strategy#jouerTaupe(core.Partie)
     */
    public boolean jouerTaupe(Partie part){
        return false;
    }
}
