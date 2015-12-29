package core;

import affich.Affichage;
import java.util.Observable;

/**
 * La classe JoueurHumain interagit avec l'affichage (qui peut être une affich ou une interface graphique).
 * L'utilisateur incarnant un JoueurHumain, cela lui permet d'avoir une action sur le programme.
 * 
 * @see core.Joueur
 */
public class JoueurHumain extends Joueur {
    	protected Affichage affich;
    
	public JoueurHumain(){
		super(true);
	}
	
        /**
         * @see core.Joueur#jouerTour()
         */
        public void jouerTour(){
            
            affich.displayTour();
            if(choixJoueur.getAction()==TypeAction.FARFADET)
                affich.displayJoueurCible();
        }
        
        /**
         * @see core.Joueur#jouerAllie()
         */
        public void jouerAllie(){
            if(hasAllie()&&carteAl instanceof CarteTaupe)
                if(affich.displayChoixCarteTaupe()){
                    affich.displayJoueurCible();
                    jouerCarteAl(choixJoueur);
                }
                
        }
        
        /**
         * @see core.Joueur#choixAllie()
         */
        public boolean choixAllie(){
            return affich.displayChoixAllie();
        }
        
        
        /**
         * @see core.Joueur#deciderReaction()
         */
        public void deciderReaction(){
            if(hasAllie()&&carteAl instanceof CarteChien){
                if(affich.displayReaction()){
                    ChoixJoueur choix = new ChoixJoueur();
                    choix.setCible(joueurActif);
                    jouerCarteAl(choix);
                }
            }
        }
        public void update(Observable obs,Object o){
            super.update(obs, o);
            if(obs instanceof Partie)
                affich = ((Partie)obs).getAffichage();
        }
       
	
}
