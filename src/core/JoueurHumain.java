package core;

public class JoueurHumain extends Joueur {
	public JoueurHumain(){
		super(true);
	}
        @Override
        public void jouerTour(Partie part){
            console.displayJoueur(this);
            console.displayEtatJoueur(this,part.getListeJoueurs(),part.getSaison());
            console.displayChoixCarte(this, choixJoueur);
            console.displayChoixAction(choixJoueur);
            if(choixJoueur.getAction()==TypeAction.FARFADET)
                console.displayJoueurCible(this, choixJoueur, part.getListeJoueurs());
        }
        @Override
        public void jouerAllie(Partie part){
        if(hasAllie()&&carteAl instanceof CarteTaupe){
                    if(console.displayChoixCarteTaupe(this,part.getListeJoueurs())){
                        console.displayJoueurCible(this, choixJoueur, part.getListeJoueurs());
                        jouerCarteAl(choixJoueur, part.getSaison());
                    }
                }
        }
        public boolean choixAllie(){
            if(console.displayChoixAllie()){
                console.displayTypeAllie(this);
                return true;
            }
            else
                return false;
            
                
        
        }
        @Override
        public void deciderReaction(Partie part){
            if(hasAllie()&&carteAl instanceof CarteChien){
                if(console.displayReaction(part.getJoueurActif(),choixJoueur,part.getSaison())){
                    ChoixJoueur choix = new ChoixJoueur();
                    choix.setCible(part.joueurActif);
                    jouerCarteAl(choix,part.getSaison());
                }
            }
        }
        @Override
        public void notifier(){
            
        }
	
}
