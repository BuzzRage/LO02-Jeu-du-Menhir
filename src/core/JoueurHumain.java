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
            console.displayJoueur();
            console.displayEtatJoueur();
            console.displayChoixCarte();
            console.displayChoixAction();
            if(choixJoueur.getAction()==TypeAction.FARFADET)
                console.displayJoueurCible();
        }
        
        /**
         * @see core.Joueur#jouerAllie()
         */
        public void jouerAllie(){
            if(hasAllie()&&carteAl instanceof CarteTaupe)
                if(console.displayChoixCarteTaupe()){
                    console.displayJoueurCible();
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
                if(console.displayReaction()){
                    ChoixJoueur choix = new ChoixJoueur();
                    choix.setCible(joueurActif);
                    jouerCarteAl(choix);
                }
            }
        }
       
	
}
