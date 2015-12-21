package core;

public interface Jouable {

    /**
     * Joue une Carte.
     * @param lanceur
     * 		Le Joueur possèdant la CarteIngrédient.
     * @param cible
     * 		Le Joueur prit pour cible.
     * @param a
     * 		L'action choisie.
     * @param s
     * 		La saison en cours.
     */
    public void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s );
}
