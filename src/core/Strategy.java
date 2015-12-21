package core;
public interface Strategy {
	/**
<<<<<<< HEAD
	 * Paramétre le ChoixJoueur du joueur IA j.
=======
	 * Paramètre le ChoixJoueur du joueur IA j.
>>>>>>> 40d5e7855c998c94fb522ee410689d9d99403a96
	 * @param p
	 * 		La partie dont on veut connaitre les informations (liste de joueurs, saison etc..).
	 * @param j
	 * 		Le joueur dont on veut modifier le ChoixJoueur.
	 * 
	 * @see core.ChoixJoueur
	 */
	public void decider(Partie p, Joueur j);
	
	/**
	 * Renvoie un booléen indiquant le choix du joueur. 
<<<<<<< HEAD
	 * Cette méthode est implémenté par la classe AggressiveStrat, qui définira ce choix à true,
=======
	 * Cette méthode est implémentée par la classe AggressiveStrat, qui définira ce choix à true,
>>>>>>> 40d5e7855c998c94fb522ee410689d9d99403a96
	 * et par la classe SafeStrat qui définira ce choix à false. 
	 * 
	 * @return true si la Strategy est une AggressiveStrat, false si c'est une SafeStrat.
	 * 
	 */
	public boolean choixAllie(); 
	
    /**
<<<<<<< HEAD
     * Retourne un booléen pour décider de réagir en utilisant sa CarteChien en cas d'attaque de farfadets par un Joueur adverse.
     * @param cible 
     * 		Le Joueur ciblé.
=======
     * Retourne un bool�en pour d�cider de r�agir en utilisant sa CarteChien en cas d'attaque de farfadets par un Joueur adverse.
     * @param cible 
     * 		Le Joueur cibl�.
>>>>>>> 40d5e7855c998c94fb522ee410689d9d99403a96
     * @param attaquant
     * 		Le Joueur attaquant, pour avoir son ChoixJoueurs et autres getters.
     * @param s
     * 		La saison en cours.
<<<<<<< HEAD
     * @return true si le Joueur cible réagit. false sinon.
=======
     * @return true si le Joueur cible r�agit. false sinon.
>>>>>>> 40d5e7855c998c94fb522ee410689d9d99403a96
     */
    public boolean deciderReaction(Joueur cible,Joueur attaquant,TypeSaison s);
    
    /**
<<<<<<< HEAD
     * Retourne un booléen indiquant si le Joueur joue sa CarteTaupe ou non.
=======
     * Retourne un bool�en indiquant si le Joueur joue sa CarteTaupe ou non.
>>>>>>> 40d5e7855c998c94fb522ee410689d9d99403a96
     * Renvoie false pour la classe SafeStrat.
     * @param part
     * 		La partie en cours.
     * @return true si le Joueur joue sa carte Taupe, false sinon.
     */
    public boolean jouerTaupe(Partie part);
}
