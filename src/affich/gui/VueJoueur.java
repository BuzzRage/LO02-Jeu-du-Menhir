package affich.gui;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTable;

import core.Joueur;

/**
 * Cette classe représente graphiquement une VueJoueur. Elle hérite de JPanel et implémente Observer.<br>
 *  <code>private Object[][] data;</code> contient les données du joueurs (numéro, points, graines et menhirs).<br>
 *  <code>private JTable table;</code> sert à créer un tableau remplit par <code>data</code>.<br>
 *  <code>Joueur joueur;</code> est le joueur à représenter.<br>
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
        
        this.add(table);
        this.setSize(100, 20);
        this.setVisible(true);
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
