package core;

/**
 * La classe JoueurIA possède un attribut de type Strategy permettant de définir le JoueurIA comme aggressif ou défensif.<br>
 * Son comportement est ainsi impacté par le type de Strategy qu'il possède. Ce type est défini dans le constructeur de manière aléatoire mais équiprobable.
 * Le JoueurIA aggressif volera des graines et prendra une carte alliée tandis que le défensif en demandera au Géant et prendra deux graines dans les partie avancées.
 */
public class JoueurIA extends Joueur {
    
    private Strategy strat;
    
    public Strategy getStrat(){
    	return strat;
    }
    
    private void setStrat(Strategy s){
    	strat=s;
    }
    
    /**
     * @see core.Joueur#deciderReaction()
     */
    public void deciderReaction(){
	if(hasAllie()&&carteAl instanceof CarteChien){
            if(strat.deciderReaction()){
                jouerCarteAl(joueurActif);
            }
	}
    }
    
    
    
    /**
     * @see core.Joueur#jouerAllie()
     */
    public void jouerAllie(){
        if(carteAl instanceof CarteTaupe && strat.jouerTaupe())
            jouerCarteAl(strat.getMaxMenhirs());
    }
    
    
    /**
     * @see core.Joueur#choixAllie()
     */
    public boolean choixAllie(){
        return strat.choixAllie();
            
    }
    
    public JoueurIA(){
            super(false);
            double rand = 100*Math.random();
            if(rand<=50)
            {
                setStrat(new SafeStrat());
            }
            else
            {
            	setStrat(new AggressiveStrat());
            }
    }
    
    
    /**
     * @see core.Joueur#jouerTour()
     */
    public void jouerTour(){
        strat.decider(this);
        }
    
}
