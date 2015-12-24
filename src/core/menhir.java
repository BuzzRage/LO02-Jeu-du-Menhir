package core;
import java.util.Scanner;
import affich.*;
import affich.gui.*;
import javax.swing.JFrame;

/**
 * Cette classe débute le programme. Elle contient la méthode main().
 *
 */
public class menhir {

	public static void main(String[] args) {
            
            Gui gui = new Gui();

            Jeu jeu = new Jeu();
            jeu.lancer();
	}

}
