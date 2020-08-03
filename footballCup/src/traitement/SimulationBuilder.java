package traitement;

import java.util.ArrayList;
import java.util.Random;

import data.Equipe;
import data.Simulation;

public class SimulationBuilder {

	
	public static Simulation createSimulation() {
		
		
		ArrayList<Equipe> equipesTournois = new ArrayList<>();
		
		for(int i=0; i<16; i++) {
			
			equipesTournois.add(TeamBuilder.createEquipe());
		}
		
		Random rand = new Random();
		String equipeOrganisatrice = equipesTournois.get(rand.nextInt(equipesTournois.size())).getPays();
		
		
		return new Simulation(equipesTournois, equipeOrganisatrice);
	}
}
