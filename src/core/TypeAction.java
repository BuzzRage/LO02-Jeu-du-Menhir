package core;

public enum TypeAction {

    GEANT(0,"Géant"), 
    ENGRAIS(1,"Engrais"), 
    FARFADET(2,"Farfadet");
    
    
    private int nbr;
    private String name="";
    
    TypeAction(int i, String s){
        this.nbr = i;
        this.name = s;
    }
    public int toInteger(){
        return this.nbr;
    }
    
    public String toString(){
        return this.name;
    }
}
