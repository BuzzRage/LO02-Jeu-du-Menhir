/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich.console;
import MenhirExceptions.*;
import affich.Affichage;
import core.*;
import java.util.*;


/**
 * La classe console permet d'afficher du texte écran, et de saisir des entrées claviers,
 * elle propose une vue en console du jeu du menhir.<br>
 * Elle n'est instanciable qu'une fois, grâce au patter singleton.<br>
 * Elle possède les attributs suivants:<br>
 * <code>private final Scanner sc</code> pour la gestion des entrées claviers<br>
 * <code>private boolean continuer</code> pour la gestion des boucles<br>
 * <code>private static Console instance</code> initialisé à <code>null</code> pour s'assurer de l'unicité de l'instance de la Console.<br>
 */
public class Console extends Affichage{

	
    private final Scanner sc;
    private boolean continuer;
    private static Console instance =null;
    
    /**
     * C'est le design pattern singleton. Il s'assure de n'avoir qu'une instance de Console.
     * @return une Console.
     */
    public static Console getInstance(){
        if (instance == null)
        {
            instance = new Console();
        }
        return instance;
    }
    
    public Console(){
        continuer=false;
        sc = new Scanner(System.in);
        
    }
    public void displayTour(){
        displayJoueur();
        displayEtatJoueur();
        displayChoixCarte();
        displayChoixAction();
    }
    /**
     * Affiche le numéro du joueur
     * 
     * @param j
     * 		Le joueur dont on doit afficher le numéro.
     * 
     */
    private void displayJoueur(){
        System.out.println("Joueur "+Integer.toString(joueurActif.getNbr())+", à toi de jouer!");
        System.out.println("=========================");
    }
    //public void displayTour()
    
    /**
     * Affiche le nombre de graines, le nombre de menhirs, la liste de joueurs de la partie, la saison en cours.
     * 
     * @param j
     * 		Le joueur dont on doit afficher le nombre de graines et de menhirs.
     * @param listeJoueurs
     * 		La liste de joueurs de la partie en cours.
     */
    private void displayEtatJoueur(){
        
        System.out.println("Tu possèdes:");
        System.out.println(joueurActif.getNbrGraines()+"  Graines");
        System.out.println(joueurActif.getNbrMenhirs() +"  Menhirs");
        System.out.println();
        System.out.println("Liste des joueurs adverses:");
        displayJoueursAdverses();
        System.out.println("La saison actuelle est " + saisonActuelle.toString());
    }
    
    /**
     * Affiche la fin de de la manche et son gagnant.
     * 
     * @param listeJoueurs
     * 		La liste des joueurs de la partie.
     */
    public void displayFinManche(){
        System.out.print("Fin de la manche!");
        Joueur meuneur=listeJoueurs.get(0);
        int menMax=-1;
        for(Joueur j:listeJoueurs){
            if(j.getNbrMenhirs()>menMax){
                menMax = j.getNbrPoints();
                meuneur = j;
            }
        }
        System.out.println("Le meneur est le joueur "+meuneur.getNbr()+" avec "+menMax+" points!");
        System.out.println();
        System.out.println("Liste des joueurs: ");
        for(Joueur j : listeJoueurs){
            System.out.println("Joueur "+j.getNbr());
            System.out.println(j.getNbrPoints()+" points");
            System.out.println();
            
        }
    }
    
    /**
     * Affiche l'action effectué par le joueur en cours.
     * @param joueurActif
     * 		Le joueur dont on veut afficher la valeur de l'effet de son action.
     */
    public void displayAction(){
        switch(joueurActif.getChoixJoueur().getAction()){
            case GEANT:
                System.out.print("Le joueur "+joueurActif.getNbr()+" obtient ");
                System.out.println(joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.GEANT)+" graines");
                break;
            case ENGRAIS:
                System.out.print("Le joueur "+joueurActif.getNbr()+" fait pousser ");
                System.out.println(Math.min(joueurActif.getNbrGraines(), joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.ENGRAIS))+" graines");
                break;
            case FARFADET:
                int nbFarf=joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.FARFADET);
                int effetReel=nbFarf;
                
                if(joueurActif.getChoixJoueur().getCible().hasAllie()&&joueurActif.getChoixJoueur().getCible().getCarteAl()instanceof CarteChien){
                    effetReel -= joueurActif.getChoixJoueur().getCible().getProtecChien();
                    if(effetReel<0){
                	effetReel=0;
                    }
                
                }
                
                if(effetReel>joueurActif.getNbrGraines()){
                    effetReel=joueurActif.getNbrGraines();
                }
   
                System.out.print("Le joueur "+joueurActif.getNbr()+" vole ");
                System.out.print(effetReel);
                System.out.println(" graines au joueur "+joueurActif.getChoixJoueur().getCible().getNbr());
                break;
        }
    }
    
    /**
     * Affiche le choix entre prendre une carte allié ou prendre deux graines. 
     * @return true si le joueur choisi de prendre une carte allié. false si le joueur choisi de prendre 2 graines.
     * 
     */
    public boolean displayChoixAllie(){
        while(true){
            try{
                return getChoixAllie();
                
            }
            catch(WrongNumberException | InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        
    }
    /**
     * Affiche la carte tirée par le joueur actif s'il est humain.
     * @param joueurActif
     * 		Le joueur dont on veut afficher la carte alliée.
     */
    public void displayTypeAllie(){
        if(joueurActif.isHuman()){
            if(joueurActif.getCarteAl() instanceof CarteChien)
                System.out.println("Tu as tiré une carte chien!");
            else
                System.out.println("Tu as tiré une carte taupe!");
        }
    }
    /**
     * Demande le choix entre prendre une carte allié ou prendre deux graines. 
     * @return true pour une carte allié. false pour 2 graines.
     * @throws WrongNumberException si le choix est différent de 1 ou 2
     * @throws InputMismatchException
     */
    private boolean getChoixAllie() throws WrongNumberException,InputMismatchException{
        int choix;
        boolean bool = false;
        System.out.println("Choix Allie");
        System.out.println("1. 2 Graines");
        System.out.println("2. 1 Carte Allié");
        try {
            choix = sc.nextInt();
            if(choix<1||choix>2)
                throw new WrongNumberException("Le choix doit être comprit entre 1 et 2!");
            else{
                switch(choix){
                    case 1:
                        bool= false;
                        break;
                    case 2:
                        bool = true;
                        break;
                        
                }        
            }
        }
        catch (InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre en chiffre comprit entre 1 et 2");
        }
        return bool;
    }
    
    /**
     * Affiche le palmares.
     * @param palmares
     * 		Le classement des joueurs.
     */
    public void displayGagnant(ArrayList<Joueur> palmares){
        System.out.println("Fin de partie!");
        System.out.println("");
        System.out.println("Palmarès: ");
        for(Joueur player : palmares){
            System.out.println("Joueur "+player.getNbr()+": "+player.getNbrPoints()+" menhirs");
            System.out.println();
        }
        
        
    }
    
    /**
     * Affiche le numéro de la manche.
     * @param p
     * 		La partie en cours.
     */
    public void displayNbManche(){
        System.out.println("Manche "+nbMancheActuelle);
        
    }
    /**
     * Demande au joueur s'il veut réagir contre une attaque de farfadet.
     * @param lanceur
     * 		Le joueur attaquant.
     * @param choixJoueur
     * 		Le ChoixJoueur du lanceur
     * @return
     * 		true si le joueur décide de réagir. false sinon.
     */
    public boolean displayReaction(){ //Pq ne pas utiliser lanceur.getChoixJoueur() ?
        System.out.print("Le joueur "+joueurActif.getNbr()+
                " attaque le joueur "+joueurActif.getChoixJoueur().getCible().getNbr()+" !");
        System.out.println(" Il veut voler "+joueurActif.getCarteAl().getEffet()+" graines.");
        System.out.println("Joueur "+joueurActif.getChoixJoueur().getCible().getNbr()+
                ", veux-tu utiliser ta carte Chien?");
        System.out.println(joueurActif.getChoixJoueur().getCible().getCarteAl().toString());
        System.out.println("La saison actuelle est: "+saisonActuelle.toString());
        System.out.println("1. Oui");
        System.out.println("2. Non");
        while(true){
            try{
                return getReaction();
            }
            catch(InputMismatchException | WrongNumberException e){
                System.out.println(e.getMessage());
            }
        }
        
    }
    /**
     * Demande au joueur s'il veut réagir.
     * @return true si le joueur décide de réagir. false sinon.
     * @throws WrongNumberException si le choix est différent de 1 ou 2
     * @throws InputMismatchException
     */
    private boolean getReaction() throws WrongNumberException,InputMismatchException{
        int choix;
        try{
            choix = sc.nextInt();
            if (choix<1||choix>2)
                throw new WrongNumberException("Le choix doit être comprit entre 1 et 2!");
            else{
                if(choix==1)  
                    return true;
                else 
                    return false;
            }
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre entre 1 et 2");
        }
        
    }
    
    
    /**
     * Paramètre la Carte du ChoixJoueur du joueur j.
     * @param j
     * 		Le joueur dont on veut modifier la Carte de son ChoixJoueur.
     * 
     * @see ChoixJoueur
     */
    private void displayChoixCarte(){
    
        int i=1;
        System.out.println("Quelle carte veux-tu jouer?");
        LinkedList<CarteIngredient> liste = joueurActif.getCartes();
        for (Iterator<CarteIngredient> it = liste.iterator(); it.hasNext();) {
            CarteIngredient c = it.next();
            System.out.println("Carte "+i);
            System.out.println(c.toString());
            i++;
        }
        while(!continuer){
            try{
                getChoixCarte(i,joueurActif);
                continuer = true;
            }
            catch(WrongNumberException | InputMismatchException e){
                    System.out.println(e.getMessage());
            }

        }
        continuer = false;
    }
     private void getChoixCarte(int i,Joueur joueurActif) throws WrongNumberException, InputMismatchException{
        int choix;
        try{
            choix = sc.nextInt();
            if(choix<1||choix>i-1)
                throw new WrongNumberException("Le nombre doit être comprit entre 1 et "+(i-1)+"!");
            else{
                joueurActif.getChoixJoueur().setCarte(joueurActif.getCarte(choix-1));
            }
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre comprit entre 1 et "+(i-1));
        }
    }
    
    /**
     * Demande au joueur de choisir son action.
     * @param choixJoueur
     * 		Le ChoixJoueur du joueur en cours.
     */
    private void displayChoixAction(){
        System.out.println("Quelle action veux-tu effectuer?");
        System.out.println();
        System.out.println("1. Géant");
        System.out.println("2. Engrais");
        System.out.println("3. Farfadet");
        System.out.println("0. Annuler");
        while(!continuer){
            try{
                this.getchoixAction();
                continuer = true;
            }
            catch(WrongNumberException | InputMismatchException e){
                    System.out.println(e.getMessage());
            }
            catch(AnnulerException e){

            }

        }
        continuer = false;
    }
    private void getchoixAction() throws WrongNumberException, InputMismatchException,AnnulerException{
        int choix;
        TypeAction action = TypeAction.ENGRAIS;
        try{
            choix = sc.nextInt();
            if(choix<0||choix >3)
                throw new WrongNumberException("le nombre doit être comprit entre 0 et 3!");
            else if(choix ==0)
                throw new AnnulerException("");
            else{
                switch(choix){
                    case 1:
                        action = TypeAction.GEANT;
                        break;
                    case 2:
                        action = TypeAction.ENGRAIS;
                        break;
                    case 3:
                        action = TypeAction.FARFADET;
                        break;
                }
                joueurActif.getChoixJoueur().setAction(action);
            }
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre comprit entre 0 et 3");
        }
    }
       
    /**
     * Afficher la liste des joueurs adverses, leur nombre de graines et de menhirs.
     * @param joueurActif
     * 		Le joueur en cours
     * @param listeJoueurs
     * 		La liste de joueurs de la partie.
     */
    private void displayJoueursAdverses(){
        for(Joueur j : listeJoueurs){
            if(joueurActif!=j){
                System.out.println(j.toString());
            }
        }
    }
    
    
    /**
     * Demande au joueur de choisir sa cible.
     * @param joueurActif
     * 		Le joueur en cours.
     * @param choixJoueur
     * 		Le ChoixJoueur du joueurActif.
     * @param listeJoueurs
     * 		La liste des joueurs de la partie.
     */
    public void displayJoueurCible(){
        System.out.println("Choisis ta cible");
        System.out.println();
        displayJoueursAdverses();
        while(!continuer){
            try{
                this.getJoueurCible();
                continuer = true;
            }
            catch(WrongNumberException | InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        continuer = false;
        
    }
    private void getJoueurCible() throws WrongNumberException, InputMismatchException {//possible probleme sur le chiffre de choix
        int choix;
        try{
            choix = sc.nextInt();
            if(choix<1||choix>listeJoueurs.size())
                throw new WrongNumberException("Ce joueur n'existe pas. Choisis un joueur existant");
            else if(choix == joueurActif.getNbr())
                throw new WrongNumberException("Tu ne peux pas te viser toi-même!");
            else
                joueurActif.getChoixJoueur().setCible(listeJoueurs.get(choix-1));
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre comprit entre 1 et "+listeJoueurs.size());
        }
    }
    
    /**
     * Demande au joueur s'il veut jouer sa carte Taupe
     * @param joueurActif
     * 		Le joueur en cours.
     * @param listeJoueurs
     * 		La liste de joueurs de la Partie.
     * @return true si le joueur joue sa carte taupe. false sinon.
     */
    public boolean displayChoixCarteTaupe(){
        boolean bool = false;
        if(joueurActif.getCarteAl() instanceof CarteTaupe){
            displayJoueursAdverses();
            System.out.println("Veux-tu jouer ta carte Taupe?");
            System.out.println(joueurActif.getCarteAl().toString());
            System.out.println("1. Oui");
            System.out.println("2. Non");
            
            while(!continuer){
                try{

                    bool = getCarteTaupe();
                    continuer = true;
                }
                catch(WrongNumberException | InputMismatchException e){
                    System.out.println(e.getMessage());
                }
            }
            continuer = false;
        }
        return bool;
    }
    private boolean getCarteTaupe()throws WrongNumberException, InputMismatchException {
        int choix;
        boolean bool;
        try{
            choix = sc.nextInt();
            if(choix<1||choix>2)
                throw new WrongNumberException("Le nombre doit être comprit entre 1et 2!");
            else{
                if(choix==1)
                    bool = true;
                else
                    bool = false;
            }
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre comprit entre 1 et 2");
        }
        return bool;
    }
    
    /**
     * Demande à l'utilisateur s'il veut une revanche, faire une nouvelle partie ou quitter.
     * @return Le choix du joueur.
     */
    public ChoixFinPartie displayChoixFinPartie(){
        System.out.println("La partie est terminée! Que veux-tu faire?");
        System.out.println("1. Revanche (même paramètres)");
        System.out.println("2. Nouvelle partie");
        System.out.println("3. Quitter");
        while(true){
                try{
                    return getChoixFinPartie();
                }
                catch(InputMismatchException | WrongNumberException e){
                    System.out.println(e.getMessage());
                }    
            }
        
    }
    private ChoixFinPartie getChoixFinPartie()throws WrongNumberException, InputMismatchException{
        int choix;
        ChoixFinPartie choixFinPartie = ChoixFinPartie.QUITTER;
        try {
            choix = sc.nextInt();
            if(choix<1||choix>3)
                throw new WrongNumberException("Le choix doit être comprit entre 1 et 3!");
            else{
                switch(choix){
                    case 1:
                        choixFinPartie = ChoixFinPartie.REJOUER;
                        break;
                    case 2:
                        choixFinPartie = ChoixFinPartie.NOUVELLE_PARTIE;
                        break;
                    case 3:
                        choixFinPartie = ChoixFinPartie.QUITTER;
                        break;
                }
            }
        }
        catch (InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre en chiffre comprit entre 1 et 3");
            
        }
       return choixFinPartie;
    }
    
    /**
     * Demande à l'utilisateur de rentrer le nombre de joueurs Humains
     * @param nbJoueurs 
     * 		Le nombre de joueurs.
     * @return
     * 		Le nombre de joueurs humains
     * @throws WrongNumberException si le nombre de joueurs humains n'est pas entre 0 et nbJoueurs.
     * @throws InputMismatchException
     */
    public int getNbJH(int nbJoueurs) throws WrongNumberException,InputMismatchException{
        int nbJH;
        System.out.println("Combien de joueurs humains?");
        try{
            nbJH = sc.nextInt();
            if(nbJH<0||nbJH>nbJoueurs)
                throw new WrongNumberException("Le nombre de joueurs Humains doit être comprit entre 1 et "+nbJoueurs+"!");
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre doit être comprit entre 1 et 6");
        }
        return nbJH;
    }
    
    /**
     * Demande à l'utilisateur de rentrer le nombre de joueurs.
     * @return 
     * 		Le nombre de joueur
     * @throws InputMismatchException
     */
    public int getNbJoueurs(){
        int nbJoueurs;
        //boolean continuer = false;
        System.out.println("Combien de joueurs?");
        while(true){
            try{
                nbJoueurs = sc.nextInt();
                if(nbJoueurs<2||nbJoueurs>6)
                    System.out.println("Le nombre de joueurs doit être comprit entre 2 et 6!");
                else
                    return nbJoueurs;
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Entre un nombre doit être comprit entre 2 et 6");
            }
        }
        
    }
    
    /**
     * Demande à l'utilisateur de choisir le type de partie.
     * @param nbJH 
     * 		Le nombre de joueurs humains.
     * @param nbJoueurs
     * 		Le nombre de joueurs.
     * @return
     * 		true si l'utilisateur choisi une partie simple. false sinon.
     * @throws InputMismatchException
     */
    public boolean getTypePartie(){
        int choix;
        System.out.println("Choisis un type de partie");
        System.out.println();
        System.out.println("1. Partie Simple");
        System.out.println("2. Partie Avancée");
        while(true){
            try{
                choix = sc.nextInt();
                if(choix<1||choix>2)
                    System.out.println("Le nombre doit être comprit entre 1 et 2!");
                else{
                    switch(choix){
                        case 1:
                            return false;
                        case 2:
                            return true;
                    }
                }    
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println("Entre un nombre entre 1 et 2");
            }
        }
    }
}
