package core;

public class CarteChien extends CarteAllie {

    public CarteChien(int[] effet) {
        super(effet);
    }

    public void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s ) {
        
        lanceur.setProtecChien(effet[0][s.toInteger()]);
        this.setPose(true);
    }
    public void jouer(Joueur lanceur, Joueur cible, TypeSaison s ) {
        
        lanceur.setProtecChien(effet[0][s.toInteger()]);
        this.setPose(true);
    }
}
