package core;

public class JoueurIA extends Joueur {
    private Strategy strat;
    
    public Strategy getStrat(){
    	return strat;
    }
    
    public void setStrat(Strategy s){
    	strat=s;
    }
    @Override
    public void deciderReaction(Partie part){
        if(strat.deciderReaction(this,part.joueurActif,part.getSaison())){
            jouerCarteAl(part.getJoueurActif(),part.getSaison());
        }
            
    }
    @Override
    public void jouerAllie(Partie part){
        if(carteAl instanceof CarteTaupe && strat.jouerTaupe(part))
            jouerCarteAl(part.getJoueurMaxMenhir(),part.getSaison());
    }
    @Override
    public boolean choixAllie(){
        if(strat.choixAllie())
            return true;
        else 
            return false;
            
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
    public void jouerTour(Partie part){
        strat.decider(part, this);
        }
    }
