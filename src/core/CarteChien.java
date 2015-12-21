package core;

public class CarteChien extends CarteAllie {

    public CarteChien(int[] effet) {
        super(effet);
    }

    /**
     * @see core.Jouable#jouer(core.Joueur, core.Joueur, core.TypeAction, core.TypeSaison)
     */
    public void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s ) {
        
        lanceur.setProtecChien(effet[0][s.toInteger()]);
        this.setPose(true);
    }
    
    /**
     * @see core.CarteAllie#jouer(core.Joueur, core.Joueur, core.TypeSaison)
     */
    public void jouer(Joueur lanceur, Joueur cible, TypeSaison s ) {
        
        lanceur.setProtecChien(effet[0][s.toInteger()]);
        this.setPose(true);
    }
}
