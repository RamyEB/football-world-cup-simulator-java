package traitement;

import java.util.ArrayList;

import data.Equipe;
import data.Joueur;

public class PenaltyProcess {

	public static ArrayList<Joueur> getBestPlayers(Equipe team) {
		
		ArrayList<Joueur> bestPlayers = new ArrayList<>();
		
		for(Joueur j : team.getListeJoueursTerrain()) {
			
			if(bestPlayers.size() <= 5 ) {
				
				bestPlayers.add(j);
			}
			else {
				
				if(findWorstKicker(bestPlayers).getAttaque() < j.getAttaque()) {
					
					bestPlayers.remove(findWorstKicker(bestPlayers));
					bestPlayers.add(j);
				}
			}
		}
		
		return bestPlayers;
	}
	
	public static Joueur findWorstKicker(ArrayList<Joueur> team) {
		
		int min = 1000;
		Joueur worstPlayer = null;
		
		for(Joueur j : team) {
			
			if(j.getAttaque() < min) {
				
				min = j.getAttaque();
				worstPlayer = j;
			}
		}
		return worstPlayer;
	}
}
