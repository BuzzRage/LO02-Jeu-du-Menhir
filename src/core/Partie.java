package core;

import affich.console.Console;
import affich.Affichage;
import java.util.*;


/**
 * La classe Partie modélise les aspects invariants des deux types de parties <code>PartieRapide</code> et <code>PartieAvancee</code><br>
 * Elle gère la création des Joueurs, la création des Cartes, le déroulement d'une partie, son initialisation, et le palmarès.<br>
 * Elle possède les attributs suivants:<br>
 *  <code>protected int nbrManches</code> valant 1 pour une <code>PartieRapide</code> et est égal au nombre de Joueurs pour une <code>PartieAvancée</code>.
 *  <code>protected int nbrMancheActuelle</code> pour indentifier la manche en cours.
 *  <code>protected int nbrTourActuel</code> pour indentifier le tour en cours.
 *  <code>protected Joueur joueurActif</code> pour identifier le Joueur en cours.
 *  <code>protected TypeSaison saison</code> pour identifier la saison en cours.
 *  <code>protected ArrayList&lsaquo;Joueur&rsaquo; listeJoueurs</code> pour stocker les Joueurs, parcourir la liste de Joueurs et pour faire un tour en conservant l'ordre.
 *  <code>protected LinkedList&lsaquo;CarteIngredient&rsaquo; listeCarteIng</code> pour stocker les <code>CarteIngredient</code> du jeu et pouvoir les distribuer par la suite.
 *  <code>protected LinkedList&lsaquo;CarteAllie&rsaquo; listeCarteAl</code> pour stocker les <code>CarteAllie</code> du jeu et pouvoir les distribuer par la suite.
 *  <code>protected boolean running</code> pour indiquer qu'une Partie est en cours.
 *  <code>protected boolean tourRunning</code> pour indiquer qu'un tour est en cours.
 *  <code>protected boolean partAvancee</code> pour indiquer s'il s'agit d'une <code>PartieAvancee</code> ou non.
 */
public abstract class Partie extends Observable implements Observer{

    protected int nbrManches;
    protected int nbrMancheActuelle;
    protected int nbrTourActuel;
    protected Affichage affich;
    protected Joueur joueurActif;
    protected TypeSaison saison;
    protected ArrayList<Joueur> listeJoueurs;
    protected LinkedList<CarteIngredient> listeCarteIng;
    protected LinkedList<CarteAllie> listeCarteAl;
    protected boolean running;
    protected boolean tourRunning;
    protected boolean partAvancee;
    
    public Partie(int nbJH,int nbJIA, Affichage affich){
        this.affich = affich;
        this.addObserver(this.affich);
        
        listeJoueurs = new ArrayList<>();
        listeCarteIng = new LinkedList<>();
        listeCarteAl = new LinkedList<>();
        running =false;
        tourRunning = false;
        nbrTourActuel = 1;
        saison = TypeSaison.PRINTEMPS;
        creerJoueur(nbJH,nbJIA);
        addJoueurObservers();
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * C'est la boucle principale de la Partie.<br>
     * Après initialisation de la partie et de la manche, les Joueurs jouent chacun leur tour la saison en cours, pour chaque saison, et pour chaque manche.<br>
     * A la fin, les cartes sont récupérées et le palmarès est affiché.
     */
    public void lancerPartie(){
        initPartie();
        do{
            if(!tourRunning){
                initManche();
                affich.displayNbManche();
            }
            if(partAvancee){
                if(saison==TypeSaison.PRINTEMPS)
                    for(Joueur j:listeJoueurs){
                        joueurActif = j;
                        this.setChanged();
                        this.notifyObservers();
                        if(joueurActif.choixAllie()){
                            distribCarteAl(j);
                            affich.displayTypeAllie();
                        }
                        else
                            joueurActif.setNbrGraines(2);
                    }
            }
            
                    
            for(Joueur j:listeJoueurs){
                joueurActif = j;
                this.setChanged();
                this.notifyObservers();
                if(partAvancee&&saison!=TypeSaison.PRINTEMPS)
                    if(joueurActif.hasAllie()&&joueurActif.getCarteAl() instanceof CarteTaupe)
                        joueurActif.jouerAllie();
                joueurActif.jouerTour();
                if(partAvancee)
                    if(joueurActif.getChoixJoueur().getAction()==TypeAction.FARFADET)
                        if(joueurActif.getChoixJoueur().getCible().hasAllie())
                            if(joueurActif.getChoixJoueur().getCible().getCarteAl() instanceof CarteChien)
                                joueurActif.getChoixJoueur().getCible().deciderReaction();
                
                affich.displayAction();
                joueurActif.jouerCarte();
                joueurActif.setChoixJoueur(new ChoixJoueur());

            }
            nextTour();
            if(!tourRunning){
                this.recupCartes();
                if(partAvancee)
                    affich.displayFinManche();
            } 
        }while(running);
        listeJoueurs.add(listeJoueurs.remove(0));
        affich.displayGagnant(this.getPalmares());
        
    }
    

    /**
     * Initialise le nombre de points, de graines et de menhir de chaque joueur à 0.
     */
    protected void initPartie(){
        nbrMancheActuelle = 1;
        for(Joueur j:listeJoueurs){
            j.setNbrPoints(0);
            j.setNbrGraines(0);
            j.setNbrMenhir(0);
        }
    }
    
   private void addJoueurObservers(){
       for(Joueur j:listeJoueurs){
	   this.addObserver(j);
	   if(j instanceof JoueurIA){
	       this.addObserver(((JoueurIA) j).getStrat());
	   }
       }
   }
    
    /**
     * Initialise la manche en mélangeant et distribuant les <code>CarteIngredients</code> aux Joueurs.<br> 
     * Réinitialise leurs nombres de graines et de menhirs.<br>
     * Pour une PartieRapide, cette méthode initialise le nombre de graines de chaque Joueurs à 2.
     */
    public void initManche(){
        this.tourRunning = true;
        this.running =true;
        saison=saison.initSaison();
        Collections.shuffle(this.listeCarteIng);
        Collections.shuffle(this.listeCarteAl);
        distribCartes();
        for(Joueur j:listeJoueurs){
            j.setNbrGraines(0);
            j.setNbrMenhir(0);
        }
    }
    
    
    // Getters et Setters

    
    public int getNbrManche(){
    	return this.nbrMancheActuelle;
    }
    public int getNbrTour(){
        return this.nbrTourActuel;
    }
    public TypeSaison getSaison(){
        return this.saison;
    }
    public Joueur getJoueurActif(){
        return joueurActif;
    }
    public ArrayList<Joueur> getListeJoueurs(){
        return listeJoueurs;
    }
    
    /**
     * Réordonne la liste de Joueurs en fonction du nombre de points, et renvoie la liste.
     * @return La liste de Joueurs par ordre de points décroissant.
     */
    public ArrayList<Joueur> getPalmares(){
        ArrayList<Joueur> palmares = new ArrayList<>();
        ArrayList<Joueur> temp = new ArrayList<>();
        temp.addAll(listeJoueurs);
        
        Joueur j = temp.get(0);
        while(!temp.isEmpty()){
            int nbPtsMax=-1;
            for(Iterator<Joueur> iter = temp.iterator(); iter.hasNext();)
            {
                Joueur k =iter.next();
                if(k.getNbrPoints()>nbPtsMax){
                    nbPtsMax = k.getNbrPoints();
                    j=k;
                }
            }
            if(palmares.isEmpty()){
                palmares.add(j);
                temp.remove(j);
            }
            else{
                if((palmares.get(palmares.size()-1).getNbrMenhirs())==j.getNbrMenhirs()){
                    if(palmares.get(palmares.size()-1).getNbrGraines()<j.getNbrGraines()){
                        palmares.add(palmares.size()-1, j);
                        temp.remove(j);
                    }
                    else{
                        palmares.add(j);
                        temp.remove(j);
                    }
                }
                else{
                    palmares.add(j);
                    temp.remove(j);
                }
            }
        }
        return palmares;
    }
    
    /**
     * Retourne le Joueur ayant le plus de menhirs.
     * @return Le Joueur ayant le plus de menhirs.
     */
    public Joueur getJoueurMaxMenhir(){
        int nbMax=-1;
        Joueur cible = null;
        while(cible==null){
            for(Joueur j:listeJoueurs)
            {
                if(j!=joueurActif&&j.getNbrMenhirs()>nbMax)
                {
                    nbMax=j.getNbrMenhirs();
                    cible = j;

                }
            }
        }
        return cible;
    }
    /**
     * Retourne le Joueur ayant le plus de graines.
     * @return Le Joueur ayant le plus de graines.
     */
    public Joueur getJoueurMaxGraines(){
        int nbMax=-1;
        Joueur cible=null;
        while(cible==null){
            for(Joueur j:listeJoueurs)
            {
                if(j!=joueurActif&&j.getNbrGraines()>nbMax)
                {
                    nbMax=j.getNbrGraines();
                    cible=j;
                }
            }
        }
        return cible;
    }
    public boolean isRunning(){
        return running;
    }
    public boolean isTourRunning(){
        return tourRunning;
    }
    public void setNbrManches(int n){
        nbrManches=n;
    }
   
    
    
    /**
     * Incrémente <code>nbrMancheActuelle</code>, modifie l'ordre des Joueurs (le premier devient dernier, les autres gagnent une place).
     * 
     */
    public void nextManche(){
        nbrMancheActuelle++;
        listeJoueurs.add(listeJoueurs.remove(0));
        tourRunning=false;
        if(nbrMancheActuelle>nbrManches){
            running = false;                
        }
        
    }
 
    /**
     * Incrément <code>nbrTourActuel</code> et la saison.
     */
    public void nextTour(){
        nbrTourActuel++;
        saison=saison.next();
        this.setChanged();
        this.notifyObservers();
        if(nbrTourActuel>4){
            nbrTourActuel=1;
            nextManche();
        }
    }
    
    
    /**
     * Distribue les <code>CarteIngredient</code> à tout les Joueurs.
     */
    private void distribCartes() {
        for(Joueur j:listeJoueurs){
            for(int i=0;i<4;i++){
                j.addCarteIng(this.listeCarteIng.pop());
            }   
            for(int i=0;i<4;i++)
        	this.addObserver(j.getCarte(i));
        }
        
    }
    
    /**
     * Donne une <code>CarteAllie</code> au Joueur j.
     * @param j
     * 		Le Joueur recevant la <code>CarteAllie</code>.
     */
    public void distribCarteAl(Joueur j){
        j.setCarteAllie(this.listeCarteAl.pop());
        this.addObserver(j.getCarteAl());
    }
    
    /**
     * Creer la liste de Joueurs, en fonction du nombre de JoueurIA et de JoueurHumain.
     * 
     * @param nbJH
     * 		Le nombre de JoueurHumain.
     * @param nbJIA
     * 		Le nombre de JoueurIA.
     */
    private void creerJoueur(int nbJH, int nbJIA){
    	Joueur.initNbrJoueurs();
        for(int i = 0;i<nbJH+nbJIA;i++)
        {
            if(i<nbJH)
            listeJoueurs.add(new JoueurHumain());
            else //if(i<=nbrJoueursH)
            listeJoueurs.add(new JoueurIA());
        }
    }
    
   /**	
     *	Récupère les cartes de chaque Joueur pour les remettre dans le la liste de cartes correspondante.<br>
     *  Pour une PartieAvancee cela restitue également les CarteAllie.
     */
    public void recupCartes() {
        for(Joueur j : listeJoueurs){
            this.listeCarteIng.addAll(j.rendreCarteIng());
        }    
        
        
    }
    public void update(Observable obs, Object o){
	if(obs instanceof Jeu){
            Jeu jeu = (Jeu)obs;
            this.listeCarteIng = jeu.getListeCarteIng();
            this.listeCarteAl = jeu.getListeCarteAl();
            this.affich = jeu.getAffichage();
	}
    }
    
    
    
}    
    
    
