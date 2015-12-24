package core;

import affich.console.Console;
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
public abstract class Partie extends Observable{

    protected int nbrManches;
    protected int nbrMancheActuelle;
    protected int nbrTourActuel;
    protected Console console;
    protected Joueur joueurActif;
    protected TypeSaison saison;
    protected ArrayList<Joueur> listeJoueurs;
    protected LinkedList<CarteIngredient> listeCarteIng;
    protected LinkedList<CarteAllie> listeCarteAl;
    protected boolean running;
    protected boolean tourRunning;
    protected boolean partAvancee;
    
    public Partie(int nbJH,int nbJIA){
        console = Console.getInstance();
        
        listeJoueurs = new ArrayList<>();
        listeCarteIng = new LinkedList<>();
        running =false;
        tourRunning = false;
        nbrMancheActuelle =1;
        nbrTourActuel = 1;
        saison = TypeSaison.PRINTEMPS;
        creerJoueur(nbJH,nbJIA);
        creerCartes();
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
                console.displayNbManche(this);
            }
            if(partAvancee){
                if(saison==TypeSaison.PRINTEMPS)
                    for(Joueur j:listeJoueurs){
                        joueurActif = j;
                        if(joueurActif.choixAllie()){
                            distribCarteAl(j);
                            console.displayTypeAllie(joueurActif);
                        }
                        else
                            joueurActif.setNbrGraines(2);
                    }
            }
            
                    
            for(Joueur j:listeJoueurs){
                joueurActif = j;
                if(partAvancee&&saison!=TypeSaison.PRINTEMPS)
                    if(joueurActif.hasAllie()&&joueurActif.getCarteAl() instanceof CarteTaupe)
                        joueurActif.jouerAllie(this);
                joueurActif.jouerTour(this);
                if(partAvancee)
                    if(joueurActif.getChoixJoueur().getAction()==TypeAction.FARFADET)
                        if(joueurActif.getChoixJoueur().getCible().hasAllie())
                            if(joueurActif.getChoixJoueur().getCible().getCarteAl() instanceof CarteChien)
                                joueurActif.getChoixJoueur().getCible().deciderReaction(this);
                
                console.displayAction(joueurActif,saison);
                joueurActif.jouerCarte(saison);
                joueurActif.setChoixJoueur(new ChoixJoueur());

            }
            nextTour();
            if(!tourRunning){
                this.recupCartes();
                if(partAvancee)
                    console.displayFinManche(listeJoueurs);
            } 
        }while(running);
        listeJoueurs.add(listeJoueurs.remove(0));
        console.displayGagnant(this.getPalmares());
        
    }
    

    /**
     * Initialise le nombre de points, de graines et de menhir de chaque joueur à 0.
     */
    protected void initPartie(){
        for(Joueur j:listeJoueurs){
            j.setNbrPoints(0);
            j.setNbrGraines(0);
            j.setNbrMenhir(0);
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
        nbrMancheActuelle = 1;
        
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
        }
    }
    
    /**
     * Donne une <code>CarteAllie</code> au Joueur j.
     * @param j
     * 		Le Joueur recevant la <code>CarteAllie</code>.
     */
    public void distribCarteAl(Joueur j){
        j.setCarteAllie(this.listeCarteAl.pop());
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
    
    /**
     * Creer les <code>CarteIngredient</code> et les ajoutent à <code>listeCarteIng</code>.<br>
     * Pour une PartieAvancee, cette méthode créer également les CarteAllie.
     */
    protected void creerCartes() {
        int[][] lune1Effet      = {{1, 1, 1, 1}, {2, 0, 1, 1}, {2, 0, 2, 0}};
        int[][] lune2Effet      = {{2, 0, 1, 1}, {1, 3, 0, 0}, {0, 1, 2, 1}};
        int[][] lune3Effet      = {{0, 0, 4, 0}, {0, 2, 2, 0}, {0, 0, 1, 3}};
        int[][] sirene1Effet    = {{1, 3, 1, 0}, {1, 2, 1, 1}, {1, 0, 4, 0}};
        int[][] sirene2Effet    = {{2, 1, 1, 1}, {1, 0, 2, 2}, {3, 0, 0, 2}};
        int[][] sirene3Effet    = {{1, 2, 2, 0}, {1, 1, 2, 1}, {2, 0, 1, 2}};
        int[][] dryade1Effet    = {{2, 1, 1, 2}, {1, 1, 1, 3}, {2, 0, 2, 2}};
        int[][] dryade2Effet    = {{0, 3, 0, 3}, {2, 1, 3, 0}, {1, 1, 3, 1}};
        int[][] dryade3Effet    = {{1, 2, 1, 2}, {1, 0, 1, 4}, {2, 4, 0, 0}};
        int[][] fontaine1Effet  = {{1, 3, 1, 2}, {2, 1, 2, 2}, {0, 0, 3, 4}};
        int[][] fontaine2Effet  = {{2, 2, 0, 3}, {1, 1, 4, 1}, {1, 2, 1, 3}};
        int[][] fontaine3Effet  = {{2, 2, 3, 1}, {2, 3, 0, 3}, {1, 1, 3, 3}};
        int[][] or1Effet        = {{2, 2, 3, 1}, {2, 3, 0, 3}, {1, 1, 3, 3}};
        int[][] or2Effet        = {{2, 2, 2, 2}, {0, 4, 4, 0}, {1, 3, 2, 2}};
        int[][] or3Effet        = {{3, 1, 3, 1}, {1, 4, 2, 1}, {2, 4, 1, 1}};
        int[][] arcEnCiel1Effet = {{4, 1, 1, 1}, {1, 2, 1, 3}, {1, 2, 2, 2}};
        int[][] arcEnCiel2Effet = {{2, 3, 2, 0}, {0, 4, 3, 0}, {2, 1, 1, 3}};
        int[][] arcEnCiel3Effet = {{2, 2, 3, 0}, {1, 1, 1, 4}, {2, 0, 3, 2}};
        int[][] dolmen1Effet    = {{3, 1, 4, 1}, {2, 1, 3, 3}, {2, 3, 2, 2}};
        int[][] dolmen2Effet    = {{2, 4, 1, 2}, {2, 2, 2, 3}, {1, 4, 3, 1}};
        int[][] dolmen3Effet    = {{3, 3, 3, 0}, {1, 3, 3, 2}, {2, 3, 1, 3}};
        int[][] fee1Effet       = {{1, 2, 2, 1}, {1, 2, 3, 0}, {0, 2, 2, 2}};
        int[][] fee2Effet       = {{4, 0, 1, 1}, {1, 1, 3, 1}, {0, 0, 3, 3}};
        int[][] fee3Effet       = {{2, 0, 3, 1}, {0, 3, 0, 3}, {1, 2, 2, 1}};
        
        listeCarteIng.add(new CarteIngredient(lune1Effet));
        listeCarteIng.add(new CarteIngredient(lune2Effet));
        listeCarteIng.add(new CarteIngredient(lune3Effet));
        listeCarteIng.add(new CarteIngredient(sirene1Effet));
        listeCarteIng.add(new CarteIngredient(sirene2Effet));
        listeCarteIng.add(new CarteIngredient(sirene3Effet));
        listeCarteIng.add(new CarteIngredient(dryade1Effet));
        listeCarteIng.add(new CarteIngredient(dryade2Effet));
        listeCarteIng.add(new CarteIngredient(dryade3Effet));
        listeCarteIng.add(new CarteIngredient(fontaine1Effet));
        listeCarteIng.add(new CarteIngredient(fontaine2Effet));
        listeCarteIng.add(new CarteIngredient(fontaine3Effet));
        listeCarteIng.add(new CarteIngredient(or1Effet));
        listeCarteIng.add(new CarteIngredient(or2Effet));
        listeCarteIng.add(new CarteIngredient(or3Effet));
        listeCarteIng.add(new CarteIngredient(arcEnCiel1Effet));
        listeCarteIng.add(new CarteIngredient(arcEnCiel2Effet));
        listeCarteIng.add(new CarteIngredient(arcEnCiel3Effet));
        listeCarteIng.add(new CarteIngredient(dolmen1Effet));
        listeCarteIng.add(new CarteIngredient(dolmen2Effet));
        listeCarteIng.add(new CarteIngredient(dolmen3Effet));
        listeCarteIng.add(new CarteIngredient(fee1Effet));
        listeCarteIng.add(new CarteIngredient(fee2Effet));
        listeCarteIng.add(new CarteIngredient(fee3Effet));
    }
}    
    
    
