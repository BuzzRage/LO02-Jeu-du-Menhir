package core;

/**
 * Cette classe est utilisée pour prendre une décision en fonction de l'état de la Partie.<br>
 * Elle implémente <code>public void decider(Partie p, Joueur jIA)</code> de l'interface Strategy. <br>
 * Cette stratégie est défensive, si le JoueurIA la possédant n'a pas de graines, il va chercher à en demander au Géant.<br>
 * La méthode decider paramètre le ChoixJoueur du JoueurIA, en définissant une cible, une carte et une action.<br>
 *
 */
public class SafeStrat extends Strat{
   /**
     * @see core.Strategy#decider(core.Joueur)
     */
    public void decider(Joueur jIA){	
    	
        if(saisonActuelle==TypeSaison.HIVER){
            jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
            jIA.getChoixJoueur().setCarte(jIA.getCarte(0));
        }
        else
        {
            if(jIA.getNbrGraines()==0)
            {
                jIA.getChoixJoueur().setAction(TypeAction.GEANT);
                jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.GEANT));
            }
            else if(jIA.getNbrGraines()>0)
            {
                jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
                jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.ENGRAIS,jIA.getNbrGraines()));
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
     * @see core.Strategy#deciderReaction()
     */
    public boolean deciderReaction(){
        return false;
    }
    
    /**
     * @see core.Strategy#jouerTaupe()
     */
    public boolean jouerTaupe(){
        return false;
    }
}
