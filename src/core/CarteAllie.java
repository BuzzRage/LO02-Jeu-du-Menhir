package core;

/**
 * La CarteAllie hérite de Carte.<br>
 * Elle redéfinit une méthode <code>String toString()</code> pour un affichage textuel de la Carte en fonction de son type le plus spécifique.<br>
 * 
 * @see core.Carte
 */
public abstract class CarteAllie extends Carte{

    public CarteAllie(int[] effet, TypeCarte type) {
        super(type);
        this.effet = new int[1][4];
        this.effet[0] = effet;
    }
    
    
    /**
     * Renvoie la valeur de l'effet de la carte en fonction de la saison.
     * @param s
     * 		La saison en cours.
     * @return L'effet de la carte.
     */
    public int getEffet(TypeSaison s){
        return this.effet[0][s.toInteger()];
    }
    
    /**
     * @see core.Carte#getEffet(core.TypeAction, core.TypeSaison)
     */
    public int getEffet(TypeAction a,TypeSaison s){
        return this.effet[0][s.toInteger()];
    }

    /**
     * Renvoie une String affichant les informations de la carte.<br>
     * Les classes filles s'en servent après avoir indiqué leur type de CarteAllie.
     *  @return L'affichage textuelle de la carte.
     */    
    public String toString(){
        String str;
        str="p   e   a   h\n";
        for(int j=0;j<this.effet[0].length;j++)
            {
                str+= effet[0][j] +"   ";
            }
        return str;
    }
    
    
    
    /**
     * Joue la CarteAlliée. <br>
     * Cette méthode a une implémentation spécifique dans les classes filles CarteTaupe et CarteChien.
     * @param lanceur
     * 		Le Joueur lançant l'action.
     * @param cible
     * 		Le Joueur pris pour cible.
     * @param s
     * 		La saison en cours.
     */
    public abstract void jouer(Joueur lanceur, Joueur cible, TypeSaison s);
}
