package core;

/**
 * La CarteTaupe hérite de CarteAllie.<br>
 * Elle implémente la méthode <code>void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s)</code> de l'interface Jouable.<br>
 * Elle permet de détruire les menhirs d'un adversaire.<br>
 *
 */
public class CarteTaupe extends CarteAllie {

    public CarteTaupe(int[] effet,TypeCarte type) {
        super(effet,type);
    }
    
    /**
     * Joue la CarteTaupe du Joueur lanceur.<br>
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
    
    /**
     * @see core.CarteAllie#toString()
     */
    public String toString(){
        String str = "Carte Taupe\n";
        str += super.toString();
        return str;
    }
}
