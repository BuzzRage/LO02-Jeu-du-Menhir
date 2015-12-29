package core;

/**
 * Cette classe est utilisée pour prendre une décision en fonction de l'état de la Partie.<br>
 * Elle implémente <code>public void decider(Partie p, Joueur jIA)</code> de l'interface Strategy. <br>
 * Cette stratégie est agressive, si le JoueurIA la possédant n'a pas de graines, il va chercher à en voler aux Joueurs adverses.<br>
 * La méthode decider paramètre le ChoixJoueur du JoueurIA, en définissant une cible, une carte et une action.<br>
 *
 */
public class AggressiveStrat implements Strategy {
    /**
     * @see core.Strategy#decider(core.Partie, core.Joueur)
     */
    public void decider(Partie p,Joueur jIA){
        if(p.getSaison()==TypeSaison.PRINTEMPS)
        {
            
            jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.GEANT,p.getSaison()));
            jIA.getChoixJoueur().setAction(TypeAction.GEANT);
            
        }
        else if(jIA.getNbrGraines()==0) //Si on a pas de graines, on en prend (le plus possible)
        {		
            jIA.getChoixJoueur().setAction(TypeAction.FARFADET);
            jIA.getChoixJoueur().setCible(p.getJoueurMaxGraines());
            jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.FARFADET,p.getSaison(),jIA.getChoixJoueur().getCible().getNbrGraines()));
        }
        else if(jIA.getNbrGraines()>1) //Si on a deux graines, on les fait pousser.
        {
            jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
            jIA.getChoixJoueur().setCarte(jIA.getCarteMax(TypeAction.ENGRAIS,p.getSaison(),jIA.getNbrGraines()));            
        }
        else if(p.getSaison()==TypeSaison.HIVER){
            
        	jIA.getChoixJoueur().setAction(TypeAction.ENGRAIS);
        	jIA.getChoixJoueur().setCarte(jIA.getCarte(0));
        }
            
        
        
    }
    
    /**
     * @see core.Strategy#deciderReaction(core.Joueur, core.Joueur)
     */
    public boolean deciderReaction(Joueur jIA,Joueur attaquant){
    	if(jIA.getCarteAl() instanceof CarteChien){
            if(attaquant.getChoixJoueur().getCarte().getEffet(TypeAction.FARFADET)>0&&jIA.getCarteAl().getEffet()>0)
                return true;
            else
                return false;
        }
        else 
            return false;
    }
    
    /**
     * @see core.Strategy#jouerTaupe(core.Partie)
     */
    public boolean jouerTaupe(Partie part){
        if(part.getSaison()==TypeSaison.PRINTEMPS)
            return false;
        else if(part.getJoueurMaxMenhir()!=null && part.getSaison()==saisonMax(part.getJoueurActif().carteAl,TypeAction.ENGRAIS))
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
