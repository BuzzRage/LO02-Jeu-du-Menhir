package core;

import affich.console.Console;

/**
 * La classe JoueurHumain interagit avec l'affichage (qui peut être une console ou une interface graphique).
 * L'utilisateur incarnant un JoueurHumain, cela lui permet d'avoir une action sur le programme.
 * 
 * @see core.Joueur
 */
public class JoueurHumain extends Joueur {
    	protected Console console;
    
	public JoueurHumain(){
		super(true);
		console = Console.getInstance();
	}
	
        /**
         * @see core.Joueur#jouerTour(core.Partie)
         */
        public void jouerTour(Partie part){
            console.displayJoueur(this);
            console.displayEtatJoueur(this,part.getListeJoueurs(),part.getSaison());
            console.displayChoixCarte(this);
            console.displayChoixAction(choixJoueur);
            if(choixJoueur.getAction()==TypeAction.FARFADET)
                console.displayJoueurCible(this, choixJoueur, part.getListeJoueurs());
        }
        
        /**
         * @see core.Joueur#jouerAllie(core.Partie)
         */
        public void jouerAllie(Partie part){
            if(hasAllie()&&carteAl instanceof CarteTaupe)
                if(console.displayChoixCarteTaupe(this,part.getListeJoueurs())){
                    console.displayJoueurCible(this, choixJoueur, part.getListeJoueurs());
                    jouerCarteAl(choixJoueur);
                }
                
        }
        
        /**
         * @see core.Joueur#choixAllie()
         */
        public boolean choixAllie(){
            return console.displayChoixAllie();
        }
        
        
        /**
         * @see core.Joueur#deciderReaction(core.Partie)
         */
        public void deciderReaction(Partie part){
            if(hasAllie()&&carteAl instanceof CarteChien){
                if(console.displayReaction(part.getJoueurActif(),choixJoueur,part.getSaison())){
                    ChoixJoueur choix = new ChoixJoueur();
                    choix.setCible(part.joueurActif);
                    jouerCarteAl(choix);
                }
            }
        }
       
	
}
