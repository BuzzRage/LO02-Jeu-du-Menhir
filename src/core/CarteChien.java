package core;

/**
 * La CarteChien hérite de CarteAllie.<br>
 * Elle implémente la méthode <code>void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s)</code> de l'interface Jouable.<br>
 * La CarteChien permet de réduire le nombre de graines volées en cas d'attaque de FARFADET.<br>
 *
 */
public class CarteChien extends CarteAllie {

    public CarteChien(int[] effet,TypeCarte type) {
        super(effet,type);
    }

    /**
     * @see core.Jouable#jouer(core.Joueur, core.Joueur, core.TypeAction)
     */
    public void jouer(Joueur lanceur, Joueur cible, TypeAction a) {
        
        this.jouer(lanceur, cible);
    }
    
    /**
     * @see core.CarteAllie#jouer(core.Joueur, core.Joueur)
     */
    public void jouer(Joueur lanceur, Joueur cible) {
        
        lanceur.setProtecChien(effet[0][saisonActuelle.toInteger()]);
        this.setPose(true);
    }
    
    /**
     * @see core.CarteAllie#toString()
     */
    public String toString(){
        String str = "Carte Chien\n";
        str += super.toString();
        return str;
    }
}
