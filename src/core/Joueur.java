package core;
import console.*;

import java.util.LinkedList;
public abstract class Joueur implements Observer{

    private int nbr;
    private int nbrPoints;
    private int protecChien;
    private int nbrGraines;
    private int nbrMenhirs;
    private final boolean humain;
    private LinkedList<CarteIngredient> deck = new LinkedList<>();
    protected CarteAllie carteAl;
    private static int nbrJoueurs = 0;
    
    protected ChoixJoueur choixJoueur;
    
    protected Console console;
    
    public Joueur(boolean humain) {
    	nbrPoints=0;
    	nbrGraines=0;
    	nbrMenhirs=0;
        protecChien = 0;
        choixJoueur = new ChoixJoueur();
        console = Console.getInstance();
    	this.humain=humain;
        Joueur.nbrJoueurs++;
        this.nbr = Joueur.nbrJoueurs;
        
    }
    /* Getter et setters */
    public int getNbrPoints(){
    	return nbrPoints;
    }
    public int getNbrGraines(){
    	return nbrGraines;
    }
    public int getNbrMenhirs(){
    	return nbrMenhirs;
    }
    public int getNbr(){
        return nbr;
    }
    public int getProtecChien(){
        return protecChien;
    }
    public static void initNbrJoueurs(){
        nbrJoueurs=0;
    }
    public boolean hasAllie(){
        boolean res =false;
        if(this.carteAl!=null)
            res= true;
        return res;
    }
    public boolean isHuman(){
    	return humain;
    }
    
    public LinkedList<CarteIngredient> getCartes(){
        LinkedList<CarteIngredient> liste=new LinkedList<>();
        for(CarteIngredient c:this.deck){
            if(!c.isPose())
                liste.add(c);
        }
        
        return liste;
    }
    public CarteIngredient getCarte(int index){
        return getCartes().get(index);
    }
    public void jouerCarte(TypeSaison s){
        
        choixJoueur.getCarte().jouer(this,choixJoueur.getCible(),choixJoueur.getAction(),s);
    }    
    public void jouerCarteAl(ChoixJoueur choix,TypeSaison s){
        carteAl.jouer(this, choix.getCible(), choix.getAction(), s);
    }
    public void jouerCarteAl(Joueur cible,TypeSaison s){
        carteAl.jouer(this,cible,s);
    }
    public abstract void jouerTour(Partie part);
    public abstract void deciderReaction(Partie part);
    public abstract void jouerAllie(Partie part);
    public abstract boolean choixAllie();
        
    
    public void setNbrPoints(int nbPoints){
    	nbrPoints=nbPoints;
    }   
    public void setNbrGraines(int nbGraines){
    	nbrGraines=nbGraines;
    }
    public void setNbrMenhir(int nbMenhirs){
    	nbrMenhirs=nbMenhirs;
    }
    public void setProtecChien(int protec){
        protecChien = protec;
    }
    public void setCarteAllie(CarteAllie c){
        this.carteAl =c;
    }
    public void addGraines(int nbGraines){
        this.nbrGraines+=nbGraines;
        if (this.nbrGraines<0)
            this.nbrGraines=0;
    }
    public void addMenhirs(int nbMenhirs){
        //int n = this.nbrMenhirs;
        this.nbrMenhirs+=nbMenhirs;
        
        if (this.nbrMenhirs<0){
            this.nbrMenhirs=0;
            //this.nbrPoints-=n;
        }
        //else
            //this.nbrPoints+=nbMenhirs;
            
    }
    public void addPoints(int nbPoints){
        this.nbrPoints+=nbPoints;
    }
    public CarteIngredient getCarteMax(TypeAction a, TypeSaison s){
        int nbMax=-1;
        CarteIngredient carte = null;
        for(CarteIngredient c:deck)
        {
            if(!c.isPose())
                if(c.getEffet(a,s)>nbMax)
                {
                        nbMax=c.getEffet(a,s);
                        carte=c;
                }
        }
        return carte;
    }
    public CarteIngredient getCarteMax(TypeAction a, TypeSaison s, int condition){
        int nbMax=0;
        CarteIngredient carte=null;
        for(CarteIngredient c:deck)
        {
            if(!c.isPose())
                if((c.getEffet(a,s)<=condition || (c.getEffet(a,s)>condition && Math.abs(c.getEffet(a,s)-condition) <= 1)) && c.getEffet(a,s)>nbMax)
                {
                        nbMax=c.getEffet(a,s);
                        carte= c;
                }
        }
        if (carte == null)
            carte=getCarteMax(a,s);
        return carte;
    }
    public void addCarteIng(CarteIngredient c){
        c.setNbr(deck.size()+1);
        this.deck.add(c);
    }
    public LinkedList<CarteIngredient> rendreCarteIng(){
        LinkedList<CarteIngredient> liste = new LinkedList<>();
        for(Carte c:deck){
            if(c instanceof CarteIngredient){
                c.setPose(false);
                liste.add((CarteIngredient)c);
            }
                
        }
        
        this.deck.removeAll(liste);
        return liste;
        
    }
   public CarteAllie getCarteAl(){
       if(carteAl.isPose())
           return null;
       else
        return this.carteAl;
   }
   public CarteAllie rendreCarteAl(){
       carteAl.setPose(false);
       return carteAl;
   }
   public ChoixJoueur getChoixJoueur(){
       return choixJoueur;
   }
    @Override
    public String toString(){
        String str = "";
        
        str+="Joueur " + this.nbr+"\n";
        str+="Graines : "+this.nbrGraines+"\n";
        str+="Menhirs : " +this.nbrMenhirs+"\n";
        
        return str;
    }

}
