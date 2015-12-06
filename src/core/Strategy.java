package core;

import java.util.ArrayList;


public interface Strategy {
	// On check le deck de carte � disposition ainsi que l'�tat du joueur.
	public void decider(Partie p, Joueur j);
	public boolean choixAllie(); // retourne true pour alli� et false pour 2 graines
        public boolean deciderReaction(Joueur cible,Joueur attaquant,TypeSaison s);
        public boolean jouerAllie(Partie part);
}
