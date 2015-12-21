package core;
public interface Strategy {
	/**
	 * Paramètre le ChoixJoueur du joueur IA j.
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
	 * Cette méthode est implémentée par la classe AggressiveStrat, qui définira ce choix à true,
	 * et par la classe SafeStrat qui définira ce choix à false. 
	 * 
	 * @return true si la Strategy est une AggressiveStrat, false si c'est une SafeStrat.
	 * 
	 */
	public boolean choixAllie(); 
	
    /**
     * Retourne un bool�en pour d�cider de r�agir en utilisant sa CarteChien en cas d'attaque de farfadets par un Joueur adverse.
     * @param cible 
     * 		Le Joueur cibl�.
     * @param attaquant
     * 		Le Joueur attaquant, pour avoir son ChoixJoueurs et autres getters.
     * @param s
     * 		La saison en cours.
     * @return true si le Joueur cible r�agit. false sinon.
     */
    public boolean deciderReaction(Joueur cible,Joueur attaquant,TypeSaison s);
    
    /**
     * Retourne un bool�en indiquant si le Joueur joue sa CarteTaupe ou non.
     * Renvoie false pour la classe SafeStrat.
     * @param part
     * 		La partie en cours.
     * @return true si le Joueur joue sa carte Taupe, false sinon.
     */
    public boolean jouerTaupe(Partie part);
}
