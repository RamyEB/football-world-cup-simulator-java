package data;

import java.util.ArrayList;

import traitement.SimulationProcess;

public class Simulation {

	private ArrayList<Equipe> equipesTournoi;
	private Poule poules[];
	private ArrayList<Equipe> vainqueursPhasesPoule;
	private ArrayList<Match> quarts;
	private ArrayList<Match> demis;
	private ArrayList<Match> finale;
	private String equipeOrganisatrice;
	
	
	public ArrayList<Match> getQuarts() {
		return quarts;
	}
	public void setQuarts(ArrayList<Match> quarts) {
		this.quarts = quarts;
	}
	public ArrayList<Match> getDemis() {
		return demis;
	}
	public void setDemis(ArrayList<Match> demi) {
		this.demis = demi;
	}
	public ArrayList<Match> getFinale() {
		return finale;
	}
	public void setFinale(ArrayList<Match> finale) {
		this.finale = finale;
	}
	public Poule[] getPoules() {
		return poules;
	}
	public void setPoules(Poule[] poules) {
		this.poules = poules;
	}
	public ArrayList<Equipe> getVainqueursPhasesPoule() {
		return vainqueursPhasesPoule;
	}
	public void setVainqueursPhasesPoule(ArrayList<Equipe> vainqueursPhasesPoule) {
		this.vainqueursPhasesPoule = vainqueursPhasesPoule;
	}
	public ArrayList<Equipe> getEquipeTournoi() {
		return equipesTournoi;
	}
	public void setEquipeTournoi(ArrayList<Equipe> equipeTournoi) {
		this.equipesTournoi = equipeTournoi;
	}
	public String getEquipeOrganisatrice() {
		return equipeOrganisatrice;
	}
	public void setEquipeOrganisatrice(String equipeOrganisatrice) {
		this.equipeOrganisatrice = equipeOrganisatrice;
	}
	
	public Simulation(ArrayList<Equipe> equipeTournoi, String equipeOrganisatrice) {
		
		this.equipesTournoi = equipeTournoi;
		this.equipeOrganisatrice = equipeOrganisatrice;
		this.vainqueursPhasesPoule = new ArrayList<Equipe>();
		this.quarts = new ArrayList<Match>();
		this.demis = new ArrayList<Match>();
		this.finale = new ArrayList<Match>();
		this.poules = null;
	}
	
	public void dispSimu() {
		
		System.out.println("Equipe organisatrice du tournoi : "+equipeOrganisatrice);
		
		for(Equipe e : equipesTournoi) {
			
			System.out.println(e.toString());
		}
	}
	
}


