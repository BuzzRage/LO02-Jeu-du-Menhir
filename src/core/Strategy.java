package core;
public interface Strategy {
    
    /**
     * Paramètre le ChoixJoueur du joueur j.
     * @param j
     * 		Le joueur dont on veut modifier le ChoixJoueur.
     * 
     * @see core.ChoixJoueur
     */
    public void decider(Joueur j);
    
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
     * Retourne un booléen pour décider de réagir en utilisant la <code>CarteChien</code> du JoueurIA en cas d'attaque de farfadets par un Joueur adverse.
     * @param cible 
     * 		Le Joueur ciblé.
     * @param attaquant
     * 		Le Joueur attaquant, pour avoir son ChoixJoueur et autres getters.
     * @return true si le Joueur cible réagit. false sinon.
     */
    public boolean deciderReaction(Joueur cible,Joueur attaquant);
    
    /**
     * Retourne un booléen indiquant si le Joueur joue sa CarteTaupe ou non.
     * Renvoie false pour la classe SafeStrat.
     * @return true si le Joueur joue sa carte Taupe, false sinon.
     */
    public boolean jouerTaupe();
}
