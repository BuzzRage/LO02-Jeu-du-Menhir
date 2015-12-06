package core;

public abstract class Carte implements Jouable{

    protected int[][] effet;
    private boolean pose;
    private int nbr;

    public Carte() {
        this.pose = false;
    }
    public boolean isPose(){
        return this.pose;
    }
    public void setNbr(int n){
        this.nbr =n;
    }
    public int getNbr(){
        return this.nbr;
    }
    public int getEffet(TypeAction a,TypeSaison s){
        return this.effet[a.toInteger()][s.toInteger()];
    }
    public void setPose(boolean b)
    {
        this.pose=b;
    }
}
