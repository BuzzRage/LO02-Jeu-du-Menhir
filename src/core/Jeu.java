package core;
import affich.console.Console;
import affich.Affichage;
import affich.gui.*;
import java.util.Observable;
import java.util.LinkedList;

public class Jeu extends Observable{
    
    private ChoixFinPartie choixFinPartie;
    private Affichage affich;
    private Partie part;
    private LinkedList<CarteIngredient> listeCarteIng;
    private LinkedList<CarteAllie> listeCarteAl;
    
    public Jeu(){
        if(Gui.GuiOrConsole())
            affich = Gui.getInstance();
        else
            affich = Console.getInstance();
        choixFinPartie = ChoixFinPartie.NOUVELLE_PARTIE;
        listeCarteIng = new LinkedList<>();
        listeCarteAl = new LinkedList<>();
    }
    
    /**
     *  Gère le démarrage d'une partie en mode console ou en mode graphique.
     */
    public void lancer(){
        while(choixFinPartie!=ChoixFinPartie.QUITTER){
            if(choixFinPartie==ChoixFinPartie.NOUVELLE_PARTIE){
                initJeu();
            }
            part.lancerPartie();
            choixFinPartie =affich.displayChoixFinPartie();
        }
    }
    
    private void initJeu(){
        creerCartes();
        int nbJoueurs = 0;
        int nbJH = 1;
        nbJoueurs = affich.getNbJoueurs();
        if(affich.getTypePartie())
            part = new PartieAvancee(nbJH,nbJoueurs-nbJH,affich);
        else
            part = new PartieRapide(nbJH,nbJoueurs-nbJH,affich);
        
        addObserver(part);
        setChanged();
        notifyObservers();
        affich.init();
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
        
        listeCarteIng.add(new CarteIngredient(lune1Effet,TypeCarte.LUNE_1));
        listeCarteIng.add(new CarteIngredient(lune2Effet,TypeCarte.LUNE_2));
        listeCarteIng.add(new CarteIngredient(lune3Effet,TypeCarte.LUNE_3));
        listeCarteIng.add(new CarteIngredient(sirene1Effet,TypeCarte.SIRENE_1));
        listeCarteIng.add(new CarteIngredient(sirene2Effet,TypeCarte.SIRENE_2));
        listeCarteIng.add(new CarteIngredient(sirene3Effet,TypeCarte.SIRENE_2));
        listeCarteIng.add(new CarteIngredient(dryade1Effet,TypeCarte.DRYADE_1));
        listeCarteIng.add(new CarteIngredient(dryade2Effet,TypeCarte.DRYADE_2));
        listeCarteIng.add(new CarteIngredient(dryade3Effet,TypeCarte.DRYADE_3));
        listeCarteIng.add(new CarteIngredient(fontaine1Effet,TypeCarte.FONTAINE_1));
        listeCarteIng.add(new CarteIngredient(fontaine2Effet,TypeCarte.FONTAINE_2));
        listeCarteIng.add(new CarteIngredient(fontaine3Effet,TypeCarte.FONTAINE_3));
        listeCarteIng.add(new CarteIngredient(or1Effet,TypeCarte.OR_1));
        listeCarteIng.add(new CarteIngredient(or2Effet,TypeCarte.OR_2));
        listeCarteIng.add(new CarteIngredient(or3Effet,TypeCarte.OR_3));
        listeCarteIng.add(new CarteIngredient(arcEnCiel1Effet,TypeCarte.ARC_EN_CIEL_1));
        listeCarteIng.add(new CarteIngredient(arcEnCiel2Effet,TypeCarte.ARC_EN_CIEL_2));
        listeCarteIng.add(new CarteIngredient(arcEnCiel3Effet,TypeCarte.ARC_EN_CIEL_3));
        listeCarteIng.add(new CarteIngredient(dolmen1Effet,TypeCarte.DOLMEN_1));
        listeCarteIng.add(new CarteIngredient(dolmen2Effet,TypeCarte.DOLMEN_2));
        listeCarteIng.add(new CarteIngredient(dolmen3Effet,TypeCarte.DOLMEN_3));
        listeCarteIng.add(new CarteIngredient(fee1Effet,TypeCarte.FEE_1));
        listeCarteIng.add(new CarteIngredient(fee2Effet,TypeCarte.FEE_2));
        listeCarteIng.add(new CarteIngredient(fee3Effet,TypeCarte.FEE_3));
        
        int[] taupe1Effet       = {1, 1, 1, 1};
        int[] taupe2Effet       = {0, 2, 2, 0};
        int[] taupe3Effet       = {0, 1, 2, 1};
        int[] chien1Effet       = {2, 0, 2, 0};
        int[] chien2Effet       = {1, 2, 0, 1};
        int[] chien3Effet       = {0, 1, 3, 0};
        
        listeCarteAl.add(new CarteTaupe(taupe1Effet,TypeCarte.TAUPE_1));
        listeCarteAl.add(new CarteTaupe(taupe2Effet,TypeCarte.TAUPE_2));
        listeCarteAl.add(new CarteTaupe(taupe3Effet,TypeCarte.TAUPE_3));
        listeCarteAl.add(new CarteChien(chien1Effet,TypeCarte.CHIEN_1));
        listeCarteAl.add(new CarteChien(chien2Effet,TypeCarte.CHIEN_2));
        listeCarteAl.add(new CarteChien(chien3Effet,TypeCarte.CHIEN_3));
    }
    public LinkedList<CarteIngredient> getListeCarteIng(){
        return listeCarteIng;
    }
    
    public LinkedList<CarteAllie> getListeCarteAl(){
        return listeCarteAl;
    }
    
}
