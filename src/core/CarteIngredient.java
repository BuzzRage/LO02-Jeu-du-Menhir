package core;
import MenhirExceptions.*;

public class CarteIngredient extends Carte{

    public CarteIngredient(int[][] effet) {
        super();
        this.effet = new int[3][4];
        this.effet = effet;
    }
    public void jouer(Joueur lanceur,TypeAction a,TypeSaison s){
        switch(a){
            case GEANT:
                lanceur.addGraines(effet[a.toInteger()][s.toInteger()]);
                break;
            case ENGRAIS:
                int val = Math.min(effet[a.toInteger()][s.toInteger()],lanceur.getNbrGraines());
                lanceur.addMenhirs(val);
                lanceur.addGraines(-val);
                lanceur.addPoints(val);
                break;
                  
        }
        this.setPose(true);
        
    }
    
    public void jouer(Joueur lanceur, Joueur cible, TypeAction a, TypeSaison s ){
        if(cible==null)
            jouer(lanceur,a,s);
        else{
            if(cible.getNbrGraines()+cible.getProtecChien()<this.effet[a.toInteger()][s.toInteger()]){
                cible.setNbrGraines(0);
                lanceur.addGraines(cible.getNbrGraines());
            }
            else{
                int val = this.effet[a.toInteger()][s.toInteger()]-cible.getProtecChien();
                if (val<0)
                    val = 0;
                cible.addGraines(-val);
                lanceur.addGraines(val);
                cible.setProtecChien(0);
            }    
        }
        this.setPose(true);
    }
    
    public String toString(){
        String str=new String("");
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
}
