package core;

/**
 * TypeAction est une énumération pour augmenter la lisibilité du code.<br>
 * Elle peut s'interpréter comme un int ou comme une String.<br>
 *
 */
public enum TypeAction {

    GEANT(0,"Géant","geant.wav"), 
    ENGRAIS(1,"Engrais","engrais.wav"), 
    FARFADET(2,"Farfadet","farfadet.wav");
    
    
    private int nbr;
    private String name="";
    private String url;
    
    TypeAction(int i, String s,String url){
        this.nbr = i;
        this.name = s;
        this.url = url;
    }
    
    public int toInteger(){
        return this.nbr;
    }
    
    public String toString(){
        return this.name;
    }
    public String getUrl(){
        return url;
    }
}
