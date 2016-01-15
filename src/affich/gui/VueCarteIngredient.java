/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package affich.gui;
import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import core.CarteIngredient;
import core.TypeAction;
import core.TypeCarte;
import core.TypeSaison;
/**
 *
 * @author Apache
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
	//this.carte=carte;
	//this.carte.addObserver(this);
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
    public void setBoutonsEnabled(boolean en){
        geant.setEnabled(en);
        engrais.setEnabled(en);
        farfadet.setEnabled(en);
    }
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
    public void actionPerformed(ActionEvent event){
        
        String imageUrl = TypeCarte.DOS_INGREDIENT.getImageUrl();
        img = new ImageIcon(imageUrl);
        background = Toolkit.getDefaultToolkit().getImage(imageUrl);
        boutons.setVisible(false);
        repaint();
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x=this.getWidth()/2-img.getIconWidth()/(2*ratio)+1;
        int y=this.getHeight()/2-img.getIconHeight()/(2*ratio);
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
