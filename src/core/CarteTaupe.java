package core;

public class CarteTaupe extends CarteAllie {

    public CarteTaupe(int[] effet) {
        super(effet);
    }
    
    /**
     * Joue la CarteTaupe du Joueur lanceur.
     * Détruit des menhirs chez le Joueur cible.
     * 
     * @param lanceur
     * 		Le Joueur possèdant la CarteTaupe.
     * @param cible
     * 		Le Joueur prit pour cible.
     * @param a
     * 		L'action choisie.
     * @param s
     * 		La saison en cours.
     * @see core.Jouable#jouer(core.Joueur, core.Joueur, core.TypeAction, core.TypeSaison)
     */
    public void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s ) {
        cible.addMenhirs(-effet[0][s.toInteger()]);
        this.setPose(true);
    }
    
    /**
     * @see core.CarteAllie#jouer(core.Joueur, core.Joueur, core.TypeSaison)
     */
    public void jouer(Joueur lanceur, Joueur cible,TypeSaison s ) {
        cible.addMenhirs(-effet[0][s.toInteger()]);
        this.setPose(true);
    }
}
