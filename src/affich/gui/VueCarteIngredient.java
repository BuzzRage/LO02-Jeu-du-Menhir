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
import core.TypeCarte;
/**
 *
 * @author Apache
 */
public class VueCarteIngredient extends JPanel implements ActionListener,Observer{    
    private ImageIcon img;
    private Image background;
    private final int transparence = 40;
    private final JButton engrais,geant,farfadet;
    private final int ratio = 2;
    private final JPanel boutons;
    
    public VueCarteIngredient(String url){
        img = new ImageIcon(TypeCarte.DOS_INGREDIENT.getImageUrl());
        background = Toolkit.getDefaultToolkit().getImage(TypeCarte.DOS_INGREDIENT.getImageUrl());
        Dimension size = new Dimension(img.getIconWidth()/ratio,img.getIconHeight()/ratio);
        Dimension dim = new Dimension((img.getIconWidth()-70)/ratio,46/ratio);
        Dimension dim2 = new Dimension(img.getIconWidth()/ratio,40/ratio);
        engrais = new MBouton("engrais.wav");
        geant = new MBouton("geant.wav");
        farfadet = new MBouton("farfadet.wav");
        
        engrais.setOpaque(true);
        engrais.setContentAreaFilled(false);
        engrais.setBorderPainted(true);
        engrais.setPreferredSize(dim);
        engrais.addActionListener(this);
        
        geant.setOpaque(false);
        geant.setContentAreaFilled(false);
        geant.setBorderPainted(true);
        geant.setPreferredSize(dim);
        geant.addActionListener(this);
        
        farfadet.setOpaque(false);
        farfadet.setContentAreaFilled(false);
        farfadet.setBorderPainted(true);
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
    public void actionPerformed(ActionEvent event){
        String imageUrl = TypeCarte.DOS_INGREDIENT.getImageUrl();
        img = new ImageIcon(imageUrl);
        background = Toolkit.getDefaultToolkit().getImage(imageUrl);
        repaint();
        boutons.setVisible(false);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x=this.getWidth()/2-img.getIconWidth()/(2*ratio)+1;
        int y=this.getHeight()/2-img.getIconHeight()/(2*ratio);
        int width=img.getIconWidth()/ratio;
        int height=img.getIconHeight()/ratio;
        
        g.drawImage(background, x, y, width,height,null);
        if(boutons.isVisible()){
            Color c = new Color(255,255,0,255*transparence/100);
            g.setColor(c);
            int saison =0;
            int a=x+(img.getIconWidth()-32+saison*104)/(2*ratio);
            int b=y+(img.getIconHeight()+24)/(2*ratio);
            int largeur=30/ratio;
            int hauteur=176/ratio;
            g.fillRect(a, b, largeur, hauteur);
        }
        
    }
    public void update(Observable obs, Object o){
        String imageUrl = ((CarteIngredient)obs).getTypeCarte().getImageUrl();
        img = new ImageIcon(imageUrl);
        background = Toolkit.getDefaultToolkit().getImage(imageUrl);
    }
    
}
