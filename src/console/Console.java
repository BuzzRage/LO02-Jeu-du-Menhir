/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;
import MenhirExceptions.*;
import core.*;
import java.util.*;

/**
 *
 * @author Apache
 */
public class Console{
    private final Scanner sc;
    private boolean continuer;
    private static Console instance =null;
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
    public void displayJoueur(Joueur j){
        System.out.println("Joueur "+Integer.toString(j.getNbr())+", à toi de jouer!");
        System.out.println("=========================");
    }
    
    public void displayEtatJoueur(Joueur j,ArrayList<Joueur> listeJoueurs, TypeSaison saison){
        
        System.out.println("Tu possèdes:");
        System.out.println(j.getNbrGraines()+"  Graines");
        System.out.println(j.getNbrMenhirs() +"  Menhirs");
        System.out.println();
        System.out.println("Liste des joueurs adverses:");
        displayJoueursAdverses(j,listeJoueurs);
        System.out.println("La saison actuelle est " + saison.toString());
    }
    
    public void displayFinManche(ArrayList<Joueur> listeJoueurs){
        System.out.print("Fin de la manche!");
        Joueur meuneur=listeJoueurs.get(0);
        int menMax=0;
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
    public void displayAction(Joueur joueurActif,TypeSaison saison){
        switch(joueurActif.getChoixJoueur().getAction()){
            case GEANT:
                System.out.print("Le joueur "+joueurActif.getNbr()+" obtient ");
                System.out.println(joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.GEANT, saison)+" graines");
                break;
            case ENGRAIS:
                System.out.print("Le joueur "+joueurActif.getNbr()+" fait pousser ");
                System.out.println(Math.min(joueurActif.getNbrGraines(), joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.ENGRAIS, saison))+" graines");
                break;
            case FARFADET:
                System.out.print("Le joueur "+joueurActif.getNbr()+" vole ");
                System.out.print(joueurActif.getChoixJoueur().getCarte().getEffet(TypeAction.FARFADET, saison)-joueurActif.getChoixJoueur().getCible().getProtecChien());
                System.out.println(" graines au joueur "+joueurActif.getChoixJoueur().getCible().getNbr());
                break;
        }
    }
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
    public void displayTypeAllie(Joueur joueurActif){
        if(joueurActif.isHuman()){
            if(joueurActif.getCarteAl() instanceof CarteChien)
                System.out.println("Tu as tiré une carte chien!");
            else
                System.out.println("Tu as tiré une carte taupe!");
        }
    }
    public boolean getChoixAllie() throws WrongNumberException,InputMismatchException{
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
    
    public void displayGagnant(ArrayList<Joueur> palmares){
        System.out.println("Fin de partie!");
        System.out.println("");
        System.out.println("Palmarès: ");
        for(Joueur player : palmares){
            System.out.println("Joueur "+player.getNbr()+": "+player.getNbrPoints()+" menhirs");
            System.out.println();
        }
        
        
    }
    
    public void displayNbManche(Partie p){
        System.out.println("Manche "+p.getNbrManche());
        
    }
    public boolean displayReaction(Joueur lanceur,ChoixJoueur choixJoueur,TypeSaison saison){
        System.out.print("Le joueur "+lanceur.getNbr()+" attaque le joueur "+choixJoueur.getCible().getNbr()+" !");
        System.out.println(" Il veut voler "+lanceur.getCarteAl().getEffet(saison)+" graines.");
        System.out.println("Joueur "+choixJoueur.getCible().getNbr()+", veux-tu utiliser ta carte Chien?");
        System.out.println(choixJoueur.getCible().getCarteAl().toString());
        System.out.println("La saison actuelle est: "+saison.toString());
        System.out.println("1. Oui");
        System.out.println("2. Non");
        while(true){
            try{
                return getReaction(choixJoueur, saison);
            }
            catch(InputMismatchException | WrongNumberException e){
                System.out.println(e.getMessage());
            }
        }
        
    }
    private boolean getReaction(ChoixJoueur choixJoueur,TypeSaison saison) throws WrongNumberException,InputMismatchException{
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
    
    public void displayChoixCarte(Joueur j,ChoixJoueur choixJoueur){
        int i=1;
        System.out.println("Quelle carte veux-tu jouer?");
        LinkedList<CarteIngredient> liste = j.getCartes();
        for (Iterator<CarteIngredient> it = liste.iterator(); it.hasNext();) {
            CarteIngredient c = it.next();
            System.out.println("Carte "+i);
            System.out.println(c.toString());
            i++;
        }
        while(!continuer){
            try{
                getChoixCarte(i,j, choixJoueur);
                continuer = true;
            }
            catch(WrongNumberException | InputMismatchException e){
                    System.out.println(e.getMessage());
            }

        }
        continuer = false;
    }
     private void getChoixCarte(int i,Joueur joueurActif,ChoixJoueur choixJoueur) throws WrongNumberException, InputMismatchException{
        int choix;
        try{
            choix = sc.nextInt();
            if(choix<1||choix>i-1)
                throw new WrongNumberException("Le nombre doit être comprit entre 1 et "+(i-1)+"!");
            else{
                choixJoueur.setCarte(joueurActif.getCarte(choix-1));
            }
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre comprit entre 1 et "+(i-1));
        }
    }
    
    public void displayChoixAction(ChoixJoueur choixJoueur){
        System.out.println("Quelle action veux-tu effectuer?");
        System.out.println();
        System.out.println("1. Géant");
        System.out.println("2. Engrais");
        System.out.println("3. Farfadet");
        System.out.println("0. Annuler");
        while(!continuer){
            try{
                this.getchoixAction(choixJoueur);
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
    private void getchoixAction(ChoixJoueur choixJoueur) throws WrongNumberException, InputMismatchException,AnnulerException{
        int choix;
        try{
            choix = sc.nextInt();
            if(choix<1||choix >3)
                throw new WrongNumberException("le nombre doit être comprit entre 0 et 3!");
            else if(choix ==0)
                throw new AnnulerException("");
            else{
                switch(choix){
                    case 1:
                        choixJoueur.setAction(TypeAction.GEANT);
                        break;
                    case 2:
                        choixJoueur.setAction(TypeAction.ENGRAIS);
                        break;
                    case 3:
                        choixJoueur.setAction(TypeAction.FARFADET);
                        break;
                }
            }
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre comprit entre 0 et 3");
        }
    }
       
    public void displayJoueursAdverses(Joueur joueurActif,ArrayList<Joueur> listeJoueurs){
        for(Joueur j : listeJoueurs){
            if(joueurActif!=j){
                System.out.println(j.toString());
            }
        }
    }
    
    
    public void displayJoueurCible(Joueur joueurActif,ChoixJoueur choixJoueur, ArrayList<Joueur> listeJoueurs){
        System.out.println("Choisis ta cible");
        System.out.println();
        displayJoueursAdverses(joueurActif,listeJoueurs);
        while(!continuer){
            try{
                this.getJoueurCible(joueurActif,choixJoueur,listeJoueurs);
                continuer = true;
            }
            catch(WrongNumberException | InputMismatchException e){
                System.out.println(e.getMessage());
            }
        }
        continuer = false;
        
    }
    private void getJoueurCible(Joueur joueurActif,ChoixJoueur choixJoueur, ArrayList<Joueur> listeJoueurs) throws WrongNumberException, InputMismatchException {//possible probleme sur le chiffre de choix
        int choix;
        try{
            choix = sc.nextInt();
            if(choix<1||choix>listeJoueurs.size())
                throw new WrongNumberException("Ce joueur n'existe pas. Choisis un joueur existant");
            else if(choix == joueurActif.getNbr())
                throw new WrongNumberException("Tu ne peux pas te viser toi-même!");
            else
                choixJoueur.setCible(listeJoueurs.get(choix-1));
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre comprit entre 1 et "+listeJoueurs.size());
        }
    }
    
    public boolean displayChoixCarteTaupe(Joueur joueurActif,ArrayList<Joueur> listeJoueurs){
        boolean bool = false;
        if(joueurActif.getCarteAl() instanceof CarteTaupe){
            displayJoueursAdverses(joueurActif,listeJoueurs);
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
    public int getNbJoueurs()throws WrongNumberException,InputMismatchException{
        int nbJoueurs;
        System.out.println("Combien de joueurs?");
        
        try{
            nbJoueurs = sc.nextInt();
            if(nbJoueurs<2||nbJoueurs>6)
                throw new WrongNumberException("Le nombre de joueurs doit être comprit entre 2 et 6!");
        }
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre doit être comprit entre 2 et 6");
        }
        return nbJoueurs;
        
    }
    
    public boolean getTypePartie(int nbJH, int nbJoueurs)throws WrongNumberException,InputMismatchException{
        boolean bool=false;
        int choix;
        System.out.println("Choisis un type de partie");
        System.out.println();
        System.out.println("1. Partie Simple");
        System.out.println("2. Partie Avancée");
        try{
            choix = sc.nextInt();
            if(choix<1||choix>2)
                throw new WrongNumberException("Le nombre doit être comprit entre 1 et 2!");
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
        catch(InputMismatchException e){
            sc.nextLine();
            throw new InputMismatchException("Entre un nombre entre 1 et 2");
        }
        return bool;
        
        
    }
}
