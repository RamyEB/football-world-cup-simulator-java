package data;

import traitement.SimulationProcess;

public class Match {

	Equipe A;
	Equipe B;
	Integer scores[];
	Equipe vainqueur;
	Equipe perdant;
	Simulation simu;
	
	
	
	public Equipe getA() {
		return A;
	}

	public void setA(Equipe a) {
		A = a;
	}

	public Equipe getB() {
		return B;
	}

	public void setB(Equipe b) {
		B = b;
	}

	public Integer[] getScores() {
		return scores;
	}

	public void setScores(Integer[] scores) {
		this.scores = scores;
	}

	public Equipe getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(Equipe vainqueur) {
		this.vainqueur = vainqueur;
	}

	public Equipe getPerdant() {
		return perdant;
	}

	public void setPerdant(Equipe perdant) {
		this.perdant = perdant;
	}

	public Simulation getSimu() {
		return simu;
	}

	public void setSimu(Simulation simu) {
		this.simu = simu;
	}

	public Match(Equipe A, Equipe B, Simulation simu) {
		
		this.simu = simu;
		this.A = A;
		this.B = B;
		this.vainqueur = SimulationProcess.match(simu, A, B);
		
		if(vainqueur.equals(A))
			this.perdant = B;
		else
			this.perdant = A;
		
		this.scores = new Integer[] {A.getLastScore(), B.getLastScore()};
	}

	public boolean equals(Match m) {
		
		if((this.A.equals(m.A) && this.B.equals(m.B)) || (this.A.equals(m.B) && this.B.equals(m.A)))
				return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		
		return A.getPays() + " contre " + B.getPays(); 
	}
}
