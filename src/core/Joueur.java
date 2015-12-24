package core;
import affich.Console;

//import java.util.LinkedList;
import java.util.*;
/**
 * La classe Joueur modélise les aspects invariants du JoueurHumain et du JoueurIA.<br>
 * A l'instanciation, on définit l'attribut <code>humain</code> par un booléen à <code>true</code> si le Joueur est humain, et <code>false</code> sinon.<br>
 * Elle possède les attributs suivants:<br>
 * <code>private final int nbr</code> pour référencer le Joueur parmis les autres.<br>
 * <code>private int nbrPoints</code> pour stocker le nombre de points.<br>
 * <code>private int nbrGraines</code> pour stocker le nombre de graines.<br>
 * <code>private int nbrMenhirs</code> pour stocker le nombre de menhirs.<br>
 * <code>private int protecChien</code> pour stocker l'effet de la CarteChien (si elle existe). Cet attribut sert lors d'une attaque de farfadet.<br>
 * <code>private final boolean humain</code> pour différencier le Joueur humain d'un Joueur IA en augmentant la lisibilité du code.<br>
 * <code>private LinkedList&lsaquo;CarteIngredient&rsaquo; deck</code> pour stocker les CarteIngredients du Joueur.<br>
 * <code>protected CarteAllie carteAl</code> pour stocker la CarteAllie du Joueur s'il a choisi d'en prendre une.<br>
 * <code>private static int nbrJoueurs</code> est un attribut de classe pour compter le nombre de Joueur total et s'assurer d'en instancier au moins 2 et au plus 6 lors d'une Partie.<br>
 * <code>protected ChoixJoueur choixJoueur</code> pour stocker les informations sur la prochaine action du Joueur.<br>
 * 
 * 
 *@see core.JoueurHumain
 *@see core.JoueurIA
 *@see core.ChoixJoueur
 */
public abstract class Joueur implements Observer{

    private final int nbr;
    private int nbrPoints;
    private int protecChien;
    private int nbrGraines;
    private int nbrMenhirs;
    private final boolean humain;
    private LinkedList<CarteIngredient> deck = new LinkedList<>();
    protected CarteAllie carteAl;
    private static int nbrJoueurs = 0;
    
    protected ChoixJoueur choixJoueur;
    

    
    public Joueur(boolean humain) {
    	nbrPoints=0;
    	nbrGraines=0;
    	nbrMenhirs=0;
        protecChien = 0;
        choixJoueur = new ChoixJoueur();
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
    
    /**
     * Initialise l'attribut de classe <code>nbrJoueurs</code> à 0.
     */
    public static void initNbrJoueurs(){
        nbrJoueurs=0;
    }
    
    /**
     * Indique si le Joueur possède une CarteAllie.
     * 
     * @return true si le Joueur possède une CarteAllie. false sinon.
     */
    public boolean hasAllie(){
        boolean res =false;
        if(this.carteAl!=null)
            res= true;
        return res;
    }
    public boolean isHuman(){
    	return humain;
    }
    public void setChoixJoueur(ChoixJoueur choix){
        choixJoueur = choix;
    }
    
    /**
     * Retourne la liste de <code>CarteIngredient</code> de la main du Joueur (<code>private LinkedList&lsaquo;CarteIngredient&rsaquo; deck</code>) qui ne sont pas encore jouée.
     * @return La liste de <code>CarteIngredient</code>
     */
    public LinkedList<CarteIngredient> getCartes(){
        LinkedList<CarteIngredient> liste=new LinkedList<>();
        for(CarteIngredient c:this.deck){
            if(!c.isPose())
                liste.add(c);
        }
        
        return liste;
    }
    
    /**
     * Retourne la <code>CarteIngredient</code> de l'indice <code>index</code> parmis celles qui ne sont pas encore jouée. 
     * @param index
     * 		L'indice de la <code>CarteIngredient</code> à récupérer.
     * @return La <code>CarteIngredient</code> voulue.
     */
    public CarteIngredient getCarte(int index){
        return getCartes().get(index);
    }
    
    /**
     *  Joue la <code>Carte</code> du <code>ChoixJoueur</code> du Joueur. 
     * @param s
     * 		La saison en cours.
     */
    public void jouerCarte(TypeSaison s){
        
        choixJoueur.getCarte().jouer(this,choixJoueur.getCible(),choixJoueur.getAction(),s);
    }    
    
    /**
     * Joue la <code>CarteAllie</code> du Joueur.
     * @param choix
     * 		Le ChoixJoueur contenant la cible, la carte et l'action désirée. Ce paramètre peut être différent du ChoixJoueur du Joueur en cours.
     * @param s
     * 		La saison en cours.
     * 
     * @see core.ChoixJoueur
     */
    public void jouerCarteAl(ChoixJoueur choix,TypeSaison s){
        carteAl.jouer(this, choix.getCible(), choix.getAction(), s);
    }
    
    /**
     * Joue la <code>CarteAllie</code> du Joueur.
     * @param cible
     * 		Le Joueur ciblé. Peut être différent de <code>choixJoueur.getCible()</code>.
     * @param s
     * 		La saison en cours.
     * 
     * @see core.ChoixJoueur
     */
    public void jouerCarteAl(Joueur cible,TypeSaison s){
        carteAl.jouer(this,cible,s);
    }
    
    /**
     * Joue le tour d'un Joueur.<br>
     * Pour un JoueurHumain, cette méthode affiche les informations nécessaire à la prise de décision de l'utilisateur, puis elle lui propose de choisir ce qu'il veut faire.<br>
     * Pour un JoueurIA, cette méthode paramètre le ChoixJoueur du JoueurIA.
     * @param part
     * 		La Partie en cours.
     */
    public abstract void jouerTour(Partie part);
    
    /**
     * Décide de réagir ou non face à une attaque de farfadet si le Joueur possède une CarteChien.
     * @param part
     * 		La Partie en cours.
     */
    public abstract void deciderReaction(Partie part);
    
    /**
     * Décide de jouer la <code>CarteAllie</code> ou non.
     * @param part
     * 		La Partie en cours.
     */
    public abstract void jouerAllie(Partie part);
    
    /**
     * Renvoie un booléen indiquant le choix du joueur. <br>
     * Cette méthode est implémenté par la classe JoueurHumain, qui choisira via la console, ou l'interface graphique,
     * et par un JoueurIA qui définira ce choix en fonction de sa stratégie. 
     * @return <code>true</code> si le joueur a choisi une <code>CarteAllie</code>. <code>false</code> s'il a choisi de prendre 2 graines.
     */
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
    
    /**
     * Ajoute <code>nbGraines</code> graines au nombre de graines du Joueur.
     * @param nbGraines
     * 		Le nombre de graines à ajouter.
     */
    public void addGraines(int nbGraines){
        this.nbrGraines+=nbGraines;
        if (this.nbrGraines<0)
            this.nbrGraines=0;
    }
    
    /**
     * Ajoute <code>nbMenhirs</code> menhirs au nombre de menhirs du Joueur.
     * @param nbMenhirs
     * 		Le nombre de menhirs à ajouter.
     */
    public void addMenhirs(int nbMenhirs){
        this.nbrMenhirs+=nbMenhirs;
        if (this.nbrMenhirs<0){
            this.nbrMenhirs=0;
        }
    }
    
    /**
     * Ajoute <code>nbPoints</code> points au nombre de points du Joueur.
     * @param nbPoints
     * 		Le nombre de points à ajouter.
     */
    public void addPoints(int nbPoints){
        this.nbrPoints+=nbPoints;
    }
    
    /**
     * Retourne la <code>CarteIngredient</code> la plus efficace en fonction de l'action et de la saison en paramètre.
     * @param a
     * 		L'action voulue.
     * @param s
     * 		La saison voulue.
     * @return La <code>CarteIngredient</code> la plus efficace.
     */
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
    
    /**
     * Retourne la <code>CarteIngredient</code> la plus pertinente en fonction de l'action et de la saison en paramètre. L'effet de la carte retournée est bornée par la condition en paramètre + 1
     * @param a
     * 		L'action voulue.
     * @param s
     * 		La saison voulue.
     * @param condition
     * 		La borne à ne pas dépasser pour éviter de renvoyer une <code>CarteIngredient</code> plus efficace que nécessaire.
     * @return La <code>CarteIngredient</code> la plus pertinente.
     */
    public CarteIngredient getCarteMax(TypeAction a, TypeSaison s, int condition){
        int nbMax=-1;
        CarteIngredient carte=null;
        for(CarteIngredient c:deck)
        {
            if(!c.isPose())
            	// TODO: rendre le if moins compact
                if((c.getEffet(a,s)<=condition || (c.getEffet(a,s)>condition && Math.abs(c.getEffet(a,s)-condition) <= 1)) && c.getEffet(a,s)>nbMax)
                {
                        nbMax=c.getEffet(a,s);
                        carte= c;
                }
        }
        //if(carte == null)
            //carte = getCarteMax(a,s);
        return carte;
    }
    
    /**
     * Ajoute une <code>CarteIngredient</code> au deck du Joueur.
     * @param c
     * 		La <code>CarteIngredient</code> à ajouter.
     */
    public void addCarteIng(CarteIngredient c){
        c.setNbr(deck.size()+1);
        this.deck.add(c);
    }
    
    /**
     * Supprime les <code>CarteIngredient</code> du deck du Joueur et les renvoies.
     * @return Le deck du Joueur.
     */
    public LinkedList<CarteIngredient> rendreCarteIng(){
        LinkedList<CarteIngredient> liste = new LinkedList<>();
        for(CarteIngredient c:deck){
                c.setPose(false);
                liste.add((CarteIngredient)c);               
        }
        
        this.deck.removeAll(liste);
        return liste;
        
    }
    
   /**
    * Retourne la <code>CarteAllie</code> du Joueur s'il en a une et si elle n'est pas posée.<br>
    * Retourne <code>null</code> si elle est posée.
    * @return La <code>CarteAllie</code> si elle n'est pas déjà posée. <code>null</code> sinon.
    */
    public CarteAllie getCarteAl(){
       if(carteAl.isPose())
           return null;
       else
        return this.carteAl;
   }
   
   /**
    * Retourne la <code>CarteAllie</code> du Joueur et la supprime.
    * @return La <code>CarteAllie</code> du Joueur.
    */
    public CarteAllie rendreCarteAl(){
        carteAl.setPose(false);
	CarteAllie cAl = carteAl;
	carteAl=null;
        return cAl;
   }
   public ChoixJoueur getChoixJoueur(){
       return choixJoueur;
   }
    
   
    /**
     * Retourne une String affichant le numéro du Joueur, son nombre de graines et son nombre de menhirs.
     * @return L'affichage textuelle du Joueur.
     */
    public String toString(){
        String str = "";
        
        str+="Joueur " + this.nbr+"\n";
        str+="Graines : "+this.nbrGraines+"\n";
        str+="Menhirs : " +this.nbrMenhirs+"\n";
        
        return str;
    }
    
    public void update(Observable o, Object arg){
        
    }

}
