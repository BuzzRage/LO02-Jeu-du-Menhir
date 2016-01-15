package affich.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import core.CarteIngredient;
import core.TypeAction;
import core.TypeCarte;
import core.TypeSaison;

/**
 * Cette classe représente graphiquement une CarteIngredient. Elle hérite de JPanel et implémente ActionListener et Observer.<br>
 *  <code>private ImageIcon img;</code> est l'image de la carte représenté.<br>
 *  <code>private Image background;</code> est le fond de la carte représenté.<br>
 *  <code>private final int transparence = 40;</code> est la transparence du curseur sur la saison en cours.<br>
 *  <code>private final MBouton engrais,geant,farfadet;</code> sont les boutons associés aux actions de la carte.<br>
 *  <code>private final int ratio = 2;</code> est le rapport de la taille de l'image dans l'interface graphique par rapport à la taille de l'image réel.<br>
 *  <code>private final JPanel boutons;</code> est le container des MBouton engrais,geant,farfadet.<br>
 *  <code>private TypeSaison saisonActuelle;</code> représente la saison en cours.<br>
 */
public class VueCarteIngredient extends JPanel implements ActionListener,Observer{    
    private ImageIcon img;
    private Image background;
    private final int transparence = 40;
    private final MBouton engrais,geant,farfadet;
    private final int ratio = 2;
    private final JPanel boutons;
    private TypeSaison saisonActuelle;
    
    public VueCarteIngredient(){
        saisonActuelle = TypeSaison.PRINTEMPS;
        img = new ImageIcon(TypeCarte.DOS_INGREDIENT.getImageUrl());
        background = Toolkit.getDefaultToolkit().getImage(TypeCarte.DOS_INGREDIENT.getImageUrl());
        Dimension size = new Dimension(img.getIconWidth()/ratio,img.getIconHeight()/ratio);
        Dimension dim = new Dimension((img.getIconWidth()-70)/ratio,46/ratio);
        Dimension dim2 = new Dimension(img.getIconWidth()/ratio,40/ratio);
        engrais = new MBouton(TypeAction.ENGRAIS);
        geant = new MBouton(TypeAction.GEANT);
        farfadet = new MBouton(TypeAction.FARFADET);
        
        engrais.setPreferredSize(dim);
        engrais.addActionListener(this);
        
        geant.setPreferredSize(dim);
        geant.addActionListener(this);
        
        farfadet.setPreferredSize(dim);
        farfadet.addActionListener(this);
        
        boutons = new JPanel();
        boutons.setLayout(new GridLayout(3,1,0,0));
        boutons.add(geant);
        boutons.add(engrais);
        boutons.add(farfadet);
        boutons.setOpaque(false);
        boutons.setMaximumSize(dim2);
        boutons.setSize(dim2);
        boutons.setMinimumSize(dim2);
        boutons.setVisible(false);
        
        
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx=0;
        gbc.gridy=0;
       
        gbc.gridheight=1;
        gbc.gridwidth=1;
        gbc.insets = new Insets(((img.getIconHeight()+20)/(2*ratio)),0,0,0);
       add(boutons, gbc);
        
    }
    
    public MBouton getBoutonEngrais(){
        return engrais;
    }
    public MBouton getBoutonGeant(){
        return geant;
    }
    public MBouton getBoutonFarfadet(){
        return farfadet;
    }
    
    /**	Rend les MBouton geant, engrais et farfadet actif ou inactif.
     * @param en
     * 		Si en vaut true, les boutons seront actifs. Sinon ils seront inactifs.
     */
    public void setBoutonsEnabled(boolean en){
        geant.setEnabled(en);
        engrais.setEnabled(en);
        farfadet.setEnabled(en);
    }
    
    /**
     * Modifie la CarteIngredient représenté graphiquement par la vue.
     * @param carteIng
     * 		La nouvelle carte à représenter.
     */
    public void setCarteIng(CarteIngredient carteIng){
        
        img = new ImageIcon(carteIng.getTypeCarte().getImageUrl());
        background = Toolkit.getDefaultToolkit().getImage(carteIng.getTypeCarte().getImageUrl());
        try{
            geant.getCarte().deleteObserver(this);
            engrais.getCarte().deleteObserver(this);
            farfadet.getCarte().deleteObserver(this);
        }
        catch(NullPointerException e){
        }
        
        geant.setCarte(carteIng);
        engrais.setCarte(carteIng);
        farfadet.setCarte(carteIng);
        carteIng.addObserver(this);
        
        
        boutons.setVisible(true);
        repaint();
    }
    
    
    /** 
     * Retourne graphiquement la carte après que le joueur ait choisi son action et désactive la visibilité des boutons associés.
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent event){
        
        String imageUrl = TypeCarte.DOS_INGREDIENT.getImageUrl();
        img = new ImageIcon(imageUrl);
        background = Toolkit.getDefaultToolkit().getImage(imageUrl);
        boutons.setVisible(false);
        repaint();
        
    }
    
    /**
     * Redéfinition de paintComponent. La méthode applique une image à l'objet et déplace le curseur rouge en surbrillance sur la saison en cours.
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x=getWidth()/2-img.getIconWidth()/(2*ratio)+1;
        int y=getHeight()/2-img.getIconHeight()/(2*ratio);
        int width=img.getIconWidth()/ratio;
        int height=img.getIconHeight()/ratio;
        
        g.drawImage(background, x, y, width,height,null);
        if(boutons.isVisible()){
            Color c = new Color(255,0,0,255*transparence/100);
            g.setColor(c);
            int a=x+(img.getIconWidth()+saisonActuelle.toInteger()*104-32)/(2*ratio);
            int b=y+(img.getIconHeight()+24)/(2*ratio);
            int largeur=30/ratio;
            int hauteur=176/ratio;
            g.fillRect(a, b, largeur, hauteur);
        }   
    }
    
    public void update(Observable obs, Object o){

        if(obs instanceof CarteIngredient){
            CarteIngredient carte = (CarteIngredient)obs;
            if(!carte.isPose()){
                String imageUrl = carte.getTypeCarte().getImageUrl();
                img = new ImageIcon(imageUrl);
                background = Toolkit.getDefaultToolkit().getImage(imageUrl);
                saisonActuelle = carte.getSaison();
                repaint();
            }
        }
    }
}
