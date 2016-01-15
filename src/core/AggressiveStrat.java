package core;

/**
 * Cette classe est utilisée pour prendre une décision en fonction de l'état de la Partie.<br>
 * Elle implémente <code>public void decider(Partie p, Joueur jIA)</code> de l'interface Strategy. <br>
 * Cette stratégie est agressive, si le JoueurIA la possédant n'a pas de graines, il va chercher à en voler aux Joueurs adverses.<br>
 * La méthode decider paramètre le ChoixJoueur du JoueurIA, en définissant une cible, une carte et une action.<br>
 *
 */
public class AggressiveStrat extends Strat{
    /**
     * @see core.Strategy#decider(core.Joueur)
     */
    public void decider(Joueur jIA){
        if(saisonActuelle==TypeSaison.PRINTEMPS)
        {
            jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.GEANT));
            jIA.getChoixJoueur().setAction(TypeAction.GEANT);
        }
        else if(jIA.getNbrGraines()==0)
        {		
            jIA.getChoixJoueur().setAction(TypeAction.FARFADET);
            jIA.getChoixJoueur().setCible(maxGraines);
            jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.FARFADET,jIA.getChoixJoueur().getCible().getNbrGraines()));
        }
        else if(jIA.getNbrGraines()>=1)
        {
            jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
            jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.ENGRAIS,jIA.getNbrGraines()));            
        }
        else{
        	jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
        	jIA.getChoixJoueur().setCarte(jIA.getCarte(0));
        }
    }
    
    /**
     * @see core.Strategy#deciderReaction()
     */
    public boolean deciderReaction(){
	Joueur cible=joueurActuel.getChoixJoueur().getCible();
    	if(cible.getCarteAl() instanceof CarteChien){
            if(joueurActuel.getChoixJoueur().getCarte().getEffet(TypeAction.FARFADET)>0&&cible.getCarteAl().getEffet()>0)
                return true;
            else
                return false;
        }
        else 
            return false;
    }
    
    /**
     * @see core.Strategy#jouerTaupe()
     */
    public boolean jouerTaupe(){
        if(saisonActuelle==TypeSaison.PRINTEMPS)
            return false;
        else if(maxMenhirs!=null && saisonActuelle==saisonMax(joueurActuel.carteAl,TypeAction.ENGRAIS))
            return true;
        else
            return false;
    }
    
    /**
     * Renvoie la saison pour laquelle l'effet de la carte est maximum pour une action donnée.
     * @param c
     * 		La carte à analyser.
     * @param a
     * 		L'action choisie à analyser.
     * @return La saison optimum
     */
    public TypeSaison saisonMax(Carte c, TypeAction a){ 
        int nbMax=0;
        boolean continuer=true;
        TypeSaison s=TypeSaison.PRINTEMPS,sMax=TypeSaison.PRINTEMPS;
    	while(continuer){
            if(c.getEffet(a,s)>nbMax)
            {
                    nbMax=c.getEffet(a,s);
                    sMax=s;
            }
            if(s.toInteger()==3)
            	break;
            s=s.next();
    	}
        return sMax;
    }
    
    /**
     * @see core.Strategy#choixAllie()
     */
    public boolean choixAllie(){
    	return true;
    }
}
