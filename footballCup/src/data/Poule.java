package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Poule {

	Simulation simu;
	HashMap<Equipe, Integer> scores;
	ArrayList<Equipe> equipes;
	ArrayList<Match> matchs;
	ArrayList<Equipe> vainqueurs;
	
	
	
	public ArrayList<Equipe> getVainqueurs() {
		return vainqueurs;
	}

	public void setVainqueurs(ArrayList<Equipe> vainqueurs) {
		this.vainqueurs = vainqueurs;
	}

	public Simulation getSimu() {
		return simu;
	}

	public void setSimu(Simulation simu) {
		this.simu = simu;
	}

	public HashMap<Equipe, Integer> getScores() {
		return scores;
	}

	public void setScores(HashMap<Equipe, Integer> scores) {
		this.scores = scores;
	}

	public ArrayList<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(ArrayList<Equipe> equipes) {
		this.equipes = equipes;
	}

	public ArrayList<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(ArrayList<Match> matchs) {
		this.matchs = matchs;
	}

	public Poule(ArrayList<Equipe> equipes, Simulation simu) {
		
		this.equipes = equipes;
		this.scores = new HashMap<Equipe, Integer>();
		
		for(Equipe e : equipes) {
			
			this.scores.put(e, 0);
		}
		this.simu = simu;
		this.matchs = new ArrayList<Match>();
		this.vainqueurs = new ArrayList<Equipe>();
	}
	
	public void matchsDeLaPoule() {
		
		for(Equipe A : equipes) {
				
			for(Equipe B : equipes) {
				
				if(!A.equals(B)) {
					
					Match m = new Match(A, B, simu);
					
					if(!alreadyPlayed(m)) {
						
						scores.put(m.vainqueur, scores.get(m.vainqueur)+1);
						matchs.add(m);
					}
				}
			}
		}
		
		this.vainqueurs = vainqueursPoule();
	}
	
	public Boolean alreadyPlayed(Match m) {
		
		for(Match ma : matchs) {
			
			if((ma.A.equals(m.A) && ma.B.equals(m.B))) {
				
				return true;
			}
			if((ma.B.equals(m.A) && ma.A.equals(m.B))) {
				
				return true;
			}
				
		}
		
		return false;
	}
	
	public ArrayList<Equipe> vainqueursPoule() {
		
		ArrayList<Equipe> vainqueurs = new ArrayList<Equipe>();
		
		vainqueurs.add(max());
		vainqueurs.add(max());
		
		return vainqueurs;
		
	}
	
	public Equipe max() {
		
		int score = 0;
		Equipe selectionne = null;
		for(Map.Entry<Equipe, Integer> mpe : scores.entrySet()) {
			
			if(mpe.getValue() >= score) {
				
				selectionne = mpe.getKey();
				score = mpe.getValue();
			}
		}
		scores.remove(selectionne);
		return selectionne;
	}
}
