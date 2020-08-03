package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

public class BoutonDrapeau extends JButton{

	public Image img;
	public String texte;
	
	public BoutonDrapeau(Image img, String texte) {
		super(texte);
		this.img = img;
		this.texte = texte;
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(150, 100));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 2, 2, 34, 34, this);
		g.drawString(texte, 50, 25);
	}
}
