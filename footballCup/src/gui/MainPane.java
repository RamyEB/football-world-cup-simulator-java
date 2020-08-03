package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import data.Equipe;
import data.Joueur;
import data.Match;
import data.Poule;
import data.Simulation;

public class MainPane extends JFrame {
	
	Simulation simu;
	String pays;
	
	public MainPane(Simulation simu) {
		
		this.simu = simu;
		this.pays = "France";
		this.setTitle("Coupe du monde de Football");
				
		JTabbedPane pane = new JTabbedPane();
		
		ImageIcon icon = new ImageIcon("src/images/footballer.png");
		ImageIcon icon2 = new ImageIcon("src/images/poule.png");
		ImageIcon icon3 = new ImageIcon("src/images/coupe.jpg");
		
		pane.addTab("Composition du tournoi", icon,teamsPage());
		pane.addTab("Phases de poule", icon2, poulesPage());
		pane.addTab("Phases éliminatoires", icon3, elimPage());
		
		//getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		getContentPane().add(pane);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public JPanel elimPage() {
		
		
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(1,4, 20, 20));
		
		JPanel quart = new JPanel();
		quart.setLayout(new GridLayout(15,1));
		
		quart.add(new JLabel("Quart de finale", JLabel.CENTER));
		
		for(Match m : simu.getQuarts()) {
			
			BoutonDrapeau button1 = new BoutonDrapeau(getImage("src/images/"+m.getA().getPays()+".png"), m.getA().getPays()+"  |  "+m.getScores()[0]);
			BoutonDrapeau button2 = new BoutonDrapeau(getImage("src/images/"+m.getB().getPays()+".png"), m.getB().getPays()+"  |  "+m.getScores()[1]);
			
			quart.add(button1);
			quart.add(button2);
			
			if(!m.equals(simu.getQuarts().get(3))) {
				
				quart.add(new JLabel(""));
				quart.add(new JLabel(""));
			} 
		}
		
		JPanel demis = new JPanel();
		demis.setLayout(new GridLayout(15,1));
		
		demis.add(new JLabel("Demis-finale", JLabel.CENTER));
		
		demis.add(new JLabel(""));
		demis.add(new JLabel(""));
		demis.add (new BoutonDrapeau(getImage("src/images/"+simu.getDemis().get(0).getA().getPays()+".png"),simu.getDemis().get(0).getA().getPays()+"  |  "+ String.valueOf(simu.getDemis().get(0).getScores()[0])));
		demis.add (new BoutonDrapeau(getImage("src/images/"+simu.getDemis().get(0).getB().getPays()+".png"),simu.getDemis().get(0).getB().getPays()+"  |  "+ String.valueOf(simu.getDemis().get(0).getScores()[1])));
		demis.add(new JLabel(""));
		demis.add(new JLabel(""));
		demis.add(new JLabel(""));
		demis.add(new JLabel(""));
		demis.add(new JLabel(""));
		demis.add(new JLabel(""));
		demis.add (new BoutonDrapeau(getImage("src/images/"+simu.getDemis().get(1).getA().getPays()+".png"),simu.getDemis().get(1).getA().getPays()+"  |  "+ String.valueOf(simu.getDemis().get(1).getScores()[0])));
		demis.add (new BoutonDrapeau(getImage("src/images/"+simu.getDemis().get(1).getB().getPays()+".png"),simu.getDemis().get(1).getB().getPays()+"  |  "+ String.valueOf(simu.getDemis().get(1).getScores()[1])));
		demis.add(new JLabel(""));
		demis.add(new JLabel(""));
		
		JPanel finale = new JPanel();
		
		finale.add(new JLabel("Finale", JLabel.CENTER));
		
		finale.setLayout(new GridLayout(15,1));
		
		for(int i=0; i<6; i++) finale.add(new JLabel(""));
		
		finale.add (new BoutonDrapeau(getImage("src/images/"+simu.getFinale().get(0).getA().getPays()+".png"),simu.getFinale().get(0).getA().getPays()+"  |  "+ String.valueOf(simu.getFinale().get(0).getScores()[0])));
		finale.add (new BoutonDrapeau(getImage("src/images/"+simu.getFinale().get(0).getB().getPays()+".png"),simu.getFinale().get(0).getB().getPays()+"  |  "+ String.valueOf(simu.getFinale().get(0).getScores()[1])));
		
		for(int i=0; i<6; i++) finale.add(new JLabel(""));
		
		JPanel classement = new JPanel();
		classement.setLayout(new GridLayout(16, 1, 15,15));
		classement.add(new JLabel("Vainqueur"));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new BoutonDrapeau(getImage("src/images/"+simu.getFinale().get(0).getVainqueur().getPays()+".png"), simu.getFinale().get(0).getVainqueur().getPays()));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		classement.add(new JLabel(""));
		
		main.add(quart);
		main.add(demis);
		main.add(finale);
		main.add(classement);
		
		return main;
	}
	
	public JPanel teamsPage() {
		
		JPanel panel = new JPanel();
		JPanel teamsPanel = new JPanel();
		JPanel playersPanel = new JPanel();
		JPanel capacityPanel = new JPanel();
		
		panel.setLayout(new GridLayout(1, 3, 20, 20));
		teamsPanel.setLayout(new GridLayout(18, 1));
		playersPanel.setLayout(new GridLayout(22, 1));
		capacityPanel.setLayout(new BoxLayout(capacityPanel, BoxLayout.PAGE_AXIS));
		
		
		ActionListener actionPlayers = new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
            	
            	capacityPanel.removeAll();
            	
            	String joueur = ((JButton)(e.getSource())).getActionCommand();
            	String identite[] = joueur.split(" ");
            	
            	for(Equipe eq : simu.getEquipeTournoi()) {
            		
            		if(pays.contentEquals(eq.getPays())) {
            			
            			for(Joueur j : eq.getListeJoueursTerrain()) {
            				
            				if(j.getNom().contentEquals(identite[1]) && j.getPrenom().contentEquals(identite[0])) {
            					
            					for(int i=0; i<20; i++) capacityPanel.add(new JLabel("\n"));
            					
            					capacityPanel.add(new JLabel("Attaque : "+String.valueOf(j.getAttaque())));
            					capacityPanel.add(new JLabel("Defense : "+String.valueOf(j.getDefense())));
            					capacityPanel.add(new JLabel("Drible : "+String.valueOf(j.getDribble())));
            					
            				}
            			}
            		}
            	}
            	
            	capacityPanel.revalidate();
            	capacityPanel.repaint();
            	
            }
        };
		
		ActionListener actionTeams = new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
            	
            	playersPanel.removeAll();
            	capacityPanel.removeAll();
            	
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	
            	pays = ((JButton)(e.getSource())).getActionCommand();
            	
            	for(Equipe eq : simu.getEquipeTournoi()) {
            		
            		if(pays.contentEquals(eq.getPays())) {
            			
            			for(int i=0; i<11; i++) {
            	
            				JButton button = new JButton(eq.getListeJoueursTerrain().get(i).getPrenom() + " " + eq.getListeJoueursTerrain().get(i).getNom());
            				button.setBackground(Color.white);
            				button.addActionListener(actionPlayers);
            				playersPanel.add(button);
            				
            			}
            		}
            	}
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	playersPanel.add(new JLabel(""));
            	
            	playersPanel.revalidate();
				playersPanel.repaint();
				capacityPanel.revalidate();
				capacityPanel.repaint();
            }
        };
		
        teamsPanel.add(new JLabel(""));
		for(int i=0; i<16; i++) {
			
			BoutonDrapeau button = new BoutonDrapeau(getImage("src/images/"+simu.getEquipeTournoi().get(i).getPays()+".png"), simu.getEquipeTournoi().get(i).getPays());
			button.addActionListener(actionTeams);
			
			button.setPreferredSize(new Dimension(200,50));
			teamsPanel.add(button);
		}
		teamsPanel.add(new JLabel(""));
		
		panel.add(teamsPanel);
		panel.add(playersPanel);
		panel.add(capacityPanel);
		
		return panel;
		
	}
	
	public Image getImage(String path) {
		
		Image image = null;
		
		try {
			image = ImageIO.read(new File(path));
			return image;
		} catch(IOException e) {e.printStackTrace();}
		
		return null;
	}

	public JPanel poulesPage() {
		
		JPanel main = new JPanel();
		main.setLayout(new GridLayout(2,2, 30,30));
		
		Poule poules[] = simu.getPoules();
		
		for(int i=0; i<poules.length; i++) {
			
			JPanel poule = new JPanel();
			poule.setLayout(new GridLayout(7,3));
			
			BoutonDrapeau equipe1 = new BoutonDrapeau(getImage("src/images/"+poules[i].getEquipes().get(0).getPays()+".png"), poules[i].getEquipes().get(0).getPays());
			BoutonDrapeau equipe2 = new BoutonDrapeau(getImage("src/images/"+poules[i].getEquipes().get(1).getPays()+".png"), poules[i].getEquipes().get(1).getPays());
			BoutonDrapeau equipe3 = new BoutonDrapeau(getImage("src/images/"+poules[i].getEquipes().get(2).getPays()+".png"), poules[i].getEquipes().get(2).getPays());
			BoutonDrapeau equipe4 = new BoutonDrapeau(getImage("src/images/"+poules[i].getEquipes().get(3).getPays()+".png"), poules[i].getEquipes().get(3).getPays());
			
			BoutonDrapeau vainqueur1 = new BoutonDrapeau(getImage("src/images/"+poules[i].getVainqueurs().get(0).getPays()+".png"), poules[i].getVainqueurs().get(0).getPays());
			BoutonDrapeau vainqueur2 = new BoutonDrapeau(getImage("src/images/"+poules[i].getVainqueurs().get(1).getPays()+".png"), poules[i].getVainqueurs().get(1).getPays());
			
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(equipe1);
			poule.add(new JLabel(""));
			poule.add(new JLabel("Poule "+String.valueOf(i+1)));
			poule.add(equipe2);
			poule.add(new JLabel(""));
			poule.add(vainqueur1);
			poule.add(equipe3);
			poule.add(new JLabel(""));
			poule.add(vainqueur2);
			poule.add(equipe4);
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			poule.add(new JLabel(""));
			
			main.add(poule);
		}
		
		return main;
	}
	
}
