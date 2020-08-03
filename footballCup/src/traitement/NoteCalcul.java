package traitement;

import java.util.ArrayList;

import data.Attaquant;
import data.Defenseur;
import data.Equipe;
import data.Gardien;
import data.Joueur;

public class NoteCalcul {

	public static int notationAttaque(Equipe team) {
		
		int notationAttaque = 0;
		ArrayList<Joueur> players = team.getListeJoueursTerrain();
		
		for(Joueur j : players) {
			
			if(j instanceof Attaquant) {
				
				Attaquant att = (Attaquant)j;
				notationAttaque += att.getAttaque() + att.getPuissanceTir() + att.getPasse() + att.getDribble() + att.getPrecisionTir() + att.getExperience();
			}
		}
		
		return notationAttaque;
	}
	
	public static int notationDefense(Equipe team) {
		
		int notationDefense = 0;
		ArrayList<Joueur> players = team.getListeJoueursTerrain();
		
		for(Joueur j : players) {
			
			if(j instanceof Defenseur) {
				
				Defenseur def = (Defenseur)j;
				notationDefense += def.getDefense() + def.getTacle() + def.getMarquage() + def.getInterception() + def.getExperience();
			}
		}
		
		return notationDefense;
	}

	public static int notationGoal(Equipe team) {
	
		int notationGoal = 0;
		ArrayList<Joueur> players = team.getListeJoueursTerrain();
		
		for(Joueur j : players) {
			
			if(j instanceof Gardien) {
				
				Gardien goal = (Gardien)j;
				notationGoal += goal.getReactivite() + goal.getDetente();
			}
		}
		
		return notationGoal;
	}

	public static int notationGlobale(Equipe team) {
		
		int notationGlobale = 0;
		
		ArrayList<Joueur> players = team.getListeJoueursTerrain();
		
		for(Joueur j : players) {
				
			notationGlobale += j.getForceMent() + j.getForcePhy();
		}
		
		return notationGlobale;
	}

	
}
