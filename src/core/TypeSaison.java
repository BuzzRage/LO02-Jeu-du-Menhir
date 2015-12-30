package core;

/**
 * TypeSaison est une énumération pour augmenter la lisibilité du code.<br>
 * Elle peut s'interpréter comme un int ou comme une String.<br>
 *
 */
public enum TypeSaison {

    PRINTEMPS(0, "Printemps",-32),
    ETE(1,"Eté",74),
    AUTOMNE(2,"Automne",174),
    HIVER(3,"Hiver",274);
    
    private final int nbr;
    private String name="";
    private static final TypeSaison[] saison= TypeSaison.values();
    private int pixel;
    TypeSaison(int i, String s,int pix){
        nbr = i;
        name = s;
        pixel = pix;
    }
    public int toInteger(){
        return this.nbr;
    }
    public TypeSaison next(){
        return this.saison[(this.ordinal()+1)%saison.length];
    }
    public TypeSaison initSaison(){
        return this.saison[0];
    }    
    public int getPixel(){
        return pixel;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
    