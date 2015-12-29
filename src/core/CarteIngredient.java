package core;

/**
 * La classe CarteIngredient hérite de Carte.<br>
 * Elle implémente la méthode <code>void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s)</code> de l'interface Jouable.<br>
 * La CarteIngredient permet de gagner des graines, de faire pousser des menhirs, et de voler des graines.<br>
 */
public class CarteIngredient extends Carte{

    public CarteIngredient(int[][] effet,TypeCarte type) {
        super(type);
        this.effet = new int[3][4];
        this.effet = effet;
    }
    
    /**
     * Joue la Carte si l'action en paramètre est GEANT ou ENGRAIS. <br>
     * Ajoute des graines au lanceur si l'action est GEANT.<br>
     * Ajoute des menhirs au lanceur et réduit le nombre de graines du même montant si l'action est ENGRAIS.<br>
     * @param lanceur
     * 		Le Joueur possèdant la CarteIngrédient.
     * @param a
     * 		L'action choisie.
     */
    public void jouer(Joueur lanceur,TypeAction a){
        switch(a){
            case GEANT:
                lanceur.addGraines(effet[a.toInteger()][saisonActuelle.toInteger()]);
                break;
            case ENGRAIS:
                int val = Math.min(effet[a.toInteger()][saisonActuelle.toInteger()],lanceur.getNbrGraines());
                lanceur.addMenhirs(val);
                lanceur.addGraines(-val);
                lanceur.addPoints(val);
                break;
                  
        }
        this.setPose(true);
        
    }
    
    /**
     * Redéfinition de la méthode jouer de CarteIngrédient. Utilisé pour le cas où l'action est FARFADET.
     * 
     * @param lanceur
     * 		Le Joueur possèdant la CarteIngrédient.
     * @param cible
     * 		Le Joueur pris pour cible.
     * @param a
     * 		L'action choisie.
     * @see core.Jouable#jouer(core.Joueur, core.Joueur, core.TypeAction)
     */
    public void jouer(Joueur lanceur, Joueur cible, TypeAction a){
        if(cible==null) //what the hell ??
            jouer(lanceur,a);
        else{
            if(cible.getNbrGraines()+cible.getProtecChien()<this.effet[a.toInteger()][saisonActuelle.toInteger()]){
                cible.setNbrGraines(0);
                lanceur.addGraines(cible.getNbrGraines());
            }
            else{
                int val = this.effet[a.toInteger()][saisonActuelle.toInteger()]-cible.getProtecChien();
                if (val<0)
                    val = 0;
                cible.addGraines(-val);
                lanceur.addGraines(val);
                cible.setProtecChien(0);
            }    
        }
        this.setPose(true);
    }
    
    
    /**
     * Affiche une représentation textuelle des informations de la cartes.
     * 
     * @return La chaine de caractère représentant la carte.
     */
    public String toString(){
        String str="";
        String action="";
        str = "   p   e   a   h\n";
        for(int i =0;i<this.effet.length;i++){
            if(i==0)
                action = "G  ";
            if(i==1)
                action = "E  ";
            if(i==2)
                action = "F  ";
            str+=action;
            for(int j=0;j<this.effet[i].length;j++)
            {
                str += Integer.toString(this.effet[i][j])+"   ";
            }
            str+="\n";
        }
        return str;
    }

    /**
     * @see core.Carte#jouer(core.Joueur, core.Joueur)
     */
    public void jouer(Joueur lanceur, Joueur cible) {
        if(cible==null) //what the hell ??
            jouer(lanceur,TypeAction.FARFADET);
        else{
            if(cible.getNbrGraines()+cible.getProtecChien()<this.effet[2][saisonActuelle.toInteger()]){
                cible.setNbrGraines(0);
                lanceur.addGraines(cible.getNbrGraines());
            }
            else{
                int val = this.effet[2][saisonActuelle.toInteger()]-cible.getProtecChien();
                if (val<0)
                    val = 0;
                cible.addGraines(-val);
                lanceur.addGraines(val);
                cible.setProtecChien(0);
            }    
        }
        this.setPose(true);
    }
}
