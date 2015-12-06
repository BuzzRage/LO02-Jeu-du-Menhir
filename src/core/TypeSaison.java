package core;

public enum TypeSaison {

    PRINTEMPS(0, "Printemps"),
    ETE(1,"Eté"),
    AUTOMNE(2,"Automne"),
    HIVER(3,"Hiver");
    
    private final int nbr;
    private String name="";
    private static final TypeSaison[] saison= TypeSaison.values();
    TypeSaison(int i, String s){
        this.nbr = i;
        this.name = s;
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
    @Override
    public String toString(){
        return this.name;
    }
}
    