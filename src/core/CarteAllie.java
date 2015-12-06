package core;

public abstract class CarteAllie extends Carte{

    public CarteAllie(int[] effet) {
        super();
        this.effet = new int[1][4];
        this.effet[0] = effet;
    }
    
    public int getEffet(TypeSaison s){
        return this.effet[0][s.toInteger()];
    }

    @Override
    public String toString(){
        
        
        String str;
        if(this instanceof CarteTaupe)
            str="Carte Taupe\n";
        else
            str="Carte Chien\n";
            
        str+="p   e   a   h\n";
        for(int j=0;j<this.effet[0].length;j++)
            {
                str+= effet[0][j] +"   ";
            }
        return str;
    }
    public int getEffet(TypeAction a,TypeSaison s){
        return this.effet[0][s.toInteger()];
    }
    public abstract void jouer(Joueur lanceur, Joueur cible, TypeSaison s);
}
