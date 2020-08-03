package main;

import data.Simulation;
import gui.MainPane;
import traitement.SimulationBuilder;
import traitement.SimulationProcess;

public class Lancement {

	
	
	public static void main(String args[]) {
		
		Simulation simu = SimulationBuilder.createSimulation();
		
		SimulationProcess.phaseDePoule(simu);
		
		SimulationProcess.phaseFinale(simu);
		
		MainPane main = new MainPane(simu);
		
	}
}
