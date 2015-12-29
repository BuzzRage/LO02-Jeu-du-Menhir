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
         * @see core.Joueur#jouerTour()
         */
        public void jouerTour(){
            console.displayJoueur(this);
            console.displayEtatJoueur(this,listeJoueursAdverses);
            console.displayChoixCarte(this);
            console.displayChoixAction(choixJoueur);
            if(choixJoueur.getAction()==TypeAction.FARFADET)
                console.displayJoueurCible(this, choixJoueur, listeJoueursAdverses);
        }
        
        /**
         * @see core.Joueur#jouerAllie()
         */
        public void jouerAllie(){
            if(hasAllie()&&carteAl instanceof CarteTaupe)
                if(console.displayChoixCarteTaupe(this,listeJoueursAdverses)){
                    console.displayJoueurCible(this, choixJoueur, listeJoueursAdverses);
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
         * @see core.Joueur#deciderReaction()
         */
        public void deciderReaction(){
            if(hasAllie()&&carteAl instanceof CarteChien){
                if(console.displayReaction(joueurActif,choixJoueur)){
                    ChoixJoueur choix = new ChoixJoueur();
                    choix.setCible(joueurActif);
                    jouerCarteAl(choix);
                }
            }
        }
       
	
}
