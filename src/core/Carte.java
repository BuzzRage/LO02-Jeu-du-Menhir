package core;

public abstract class Carte implements Jouable{

    protected int[][] effet;
    private boolean pose;
    private int nbr;

    public Carte() {
        this.pose = false;
    }
    /**
     * Renvoie un booléen indiquant si un carte est posée ou non. Cela permet de savoir quelles cartes ont déjà été jouées.
     * 
     * @return true si la carte a déjà été jouée. false sinon.
     */
    public boolean isPose(){
        return this.pose;
    }
    
    /**
     * Paramètre le numéro de la carte, pour la repérer parmis les autres.
     * @param n
     * 		Le numéro de la carte.
     */
    public void setNbr(int n){
        this.nbr =n;
    }
    
    /**
     * Retourne le numéro de la carte.
     * @return Le numéro de la carte.
     */
    public int getNbr(){
       return this.nbr;
    }
    
    /**
     * Retourne l'effet de la carte, en fonction de la saison en cours et de l'action voulue.
     * @param a
     * 		L'action choisie par le Joueur.
     * @param s
     * 		La saison en cours.
     * @return L'effet de la carte.
     */
    public int getEffet(TypeAction a,TypeSaison s){
        return this.effet[a.toInteger()][s.toInteger()];
    }
    
    /**
     * Change l'état d'une carte.
     * @param b
     * 		Le booléen indiquant si une carte est posée ou non.
     */
    public void setPose(boolean b)
    {
        this.pose=b;
    }
}
