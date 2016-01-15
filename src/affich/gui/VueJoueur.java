package affich.gui;
import javax.swing.*;

import core.Joueur;

import java.util.Observable;
import java.util.Observer;

/**
 *
 *
 */
public class VueJoueur extends JPanel implements Observer{
    
    private Object[][] data;
    private JTable table;
    Joueur joueur;
    
    public void update(Observable o, Object arg) {
	if(o instanceof Joueur){
                Joueur j = (Joueur)o;
                data[0][0] = "Joueur "+j.getNbr();
                data[0][1] = "Points: "+j.getNbrPoints();
                data[1][0] = "Graines: "+j.getNbrGraines();
                data[1][1] = "Menhirs: "+j.getNbrMenhirs();
		repaint();
	    }
    }
   
    public VueJoueur(Joueur j){
        data = new Object[2][2];
        String[] title = {"",""};
        
        table = new JTable(data,title);
        table.setEnabled(false);
        
        add(table);
        setSize(100, 20);
        setVisible(true);
        repaint();
    }
    public void setJoueur(Joueur j){
        try{
            joueur.deleteObserver(this);
        }
        catch(NullPointerException e){
        }
        joueur =j;
        joueur.addObserver(this);
        update(joueur,null);
    }
    
}
