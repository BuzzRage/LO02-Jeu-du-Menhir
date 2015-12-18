package core;

public class CarteTaupe extends CarteAllie {

    public CarteTaupe(int[] effet) {
        super(effet);
    }
    @Override
    public void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s ) {
        cible.addMenhirs(-effet[0][s.toInteger()]);
        this.setPose(true);
    }
    @Override
    public void jouer(Joueur lanceur, Joueur cible,TypeSaison s ) {
        cible.addMenhirs(-effet[0][s.toInteger()]);
        this.setPose(true);
    }
}
