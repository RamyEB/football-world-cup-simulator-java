package traitement;

import java.util.ArrayList;
import java.util.Random;

import data.Entraineur;
import data.Equipe;
import data.Gardien;
import data.Joueur;
import data.Match;
import data.Poule;
import data.Simulation;
import data.Utils;

public class SimulationProcess {
	
	public static Equipe match(Simulation simu, Equipe A, Equipe B) {

		Random rand = new Random();
		String meteo = Utils.meteo[rand.nextInt(Utils.meteo.length)];
		Equipe equipeGagnante;

		// Bonus et malus lies a la meteo
		A.setFormeForMatch(meteo, simu.getEquipeOrganisatrice());
		B.setFormeForMatch(meteo, simu.getEquipeOrganisatrice());

		// Premiere mi-temps
		int nbButMarqueB1 = calculButs(B);
		
		int nbButMarqueA1 = calculButs(A);

		if (nbButMarqueA1 > nbButMarqueB1)
			equipeGagnante = A;
		else
			equipeGagnante = B;

		miTemps(A, B, equipeGagnante);

		// Deuxieme mi-temps
		int nbButMarqueB2 = calculButs(B);

		int nbButMarqueA2 = calculButs(A);

		int nbButsA = nbButMarqueA1 + nbButMarqueA2;
		
		int nbButsB = nbButMarqueB1 + nbButMarqueB2;
		
		if (nbButsA > nbButsB)
			equipeGagnante = A;
		else
			equipeGagnante = B;
		
		if(nbButsA == nbButsB) {
			
			miTemps(A, B, equipeGagnante);
			
			int nbButMarqueB3 = calculButs(B);

			int nbButMarqueA3 = calculButs(A);

			nbButsA = nbButMarqueA1 + nbButMarqueA2 + nbButMarqueA3;
			nbButsB = nbButMarqueB1 + nbButMarqueB2 + nbButMarqueB3;
			
			if (nbButsA > nbButsB)
				equipeGagnante = A;
			else
				equipeGagnante = B;
		}
		
		A.setLastScore(nbButsA);
		B.setLastScore(nbButsB);
		
		System.out.println("Score de "+A.getPays()+" : "+A.getLastScore());
		System.out.println("Score de "+B.getPays()+" : "+B.getLastScore());
		
		return equipeGagnante;
	}

	public static int calculButs(Equipe A) {
		
		Random rand = new Random();
		
		int nbButs = (int)(rand.nextFloat()*2.5) * (NoteCalcul.notationAttaque(A) + NoteCalcul.notationGlobale(A))
				/ (NoteCalcul.notationDefense(A));

		if(nbButs != 0) {
			
			if((NoteCalcul.notationGoal(A)/NoteCalcul.notationAttaque(A))*100 > 50) {
				
				nbButs -= rand.nextInt(nbButs);
			}
			else {
				
				if(nbButs > 1)
					nbButs -= rand.nextInt(nbButs-1);
			}
		}
		
		return nbButs;
	}
	
	public static void miTemps(Equipe A, Equipe B, Equipe vainqueurPremiereMiTemps) {

		Random rand = new Random();
		Entraineur coachA = A.getEntraineur();
		Entraineur coachB = B.getEntraineur();

		if (A.equals(vainqueurPremiereMiTemps)) {

			Boolean reussiteA = reussite((coachA.getExperience() / 110) * 10);
			Boolean reussiteB = reussite((coachB.getMotivateur() / 110) * 10);

			for (Joueur j : A.getListeJoueursTerrain()) {

				j.setForcePhy(j.getForcePhy() - rand.nextInt(10));

				if (!reussiteA)
					j.setForceMent(j.getForceMent() - Utils.malusPriseDeConfiance);
			}

			for (Joueur j : B.getListeJoueursTerrain()) {

				j.setForcePhy(j.getForcePhy() - rand.nextInt(10));

				if (reussiteB)
					j.setForceMent(j.getForceMent() + Utils.bonusMotivation);
				else
					j.setForceMent(j.getForceMent() - Utils.bonusMotivation);
			}
		}

		else {

			Boolean reussiteA = reussite((coachA.getMotivateur() / 110) * 10);
			Boolean reussiteB = reussite((coachB.getExperience() / 110) * 10);

			for (Joueur j : B.getListeJoueursTerrain()) {

				j.setForcePhy(j.getForcePhy() - rand.nextInt(10));

				if (!reussiteA)
					j.setForceMent(j.getForceMent() - Utils.malusPriseDeConfiance);
			}

			for (Joueur j : A.getListeJoueursTerrain()) {

				j.setForcePhy(j.getForcePhy() - rand.nextInt(10));

				if (reussiteB)
					j.setForceMent(j.getForceMent() + Utils.bonusMotivation);
				else
					j.setForceMent(j.getForceMent() - Utils.bonusMotivation);
			}
		}
	}

	public static Boolean reussite(int probaReussite) {

		int i = new Random().nextInt(10);
		if (i < probaReussite) {
			return false;
		} else {
			return true;
		}
	}

	public static void phaseDePoule(Simulation simu) {
		
		ArrayList<Equipe> equipesTournoi = simu.getEquipeTournoi();
		ArrayList<Equipe> vainqueursPhasePoule = new ArrayList<Equipe>();
		
		ArrayList<ArrayList<Equipe>> equipesPoule = new ArrayList<ArrayList<Equipe>>();
		Poule poules[] = {null, null, null, null };
		int compteur = 1;
		int compteurEquipe = 0;
		
		for(int i=0; i<4; i++)
			equipesPoule.add(new ArrayList<Equipe>());
		
		while(compteur<17) {
			
			equipesPoule.get(compteurEquipe).add(equipesTournoi.get(compteur-1));; 
			
			if(compteur % 4 == 0) compteurEquipe ++;
			
			compteur++;
		}
		
		for(int i=0; i<4; i++) {
			
			poules[i] = new Poule(equipesPoule.get(i), simu);
			poules[i].matchsDeLaPoule();
			vainqueursPhasePoule.addAll(poules[i].getVainqueurs());
		}
		
		simu.setVainqueursPhasesPoule(vainqueursPhasePoule);
		simu.setPoules(poules);
	}

	public static ArrayList<Equipe> phaseFinale(Simulation simu) {
		
		ArrayList<Equipe> winnersPoule = simu.getVainqueursPhasesPoule();
		ArrayList<Equipe> classement = new ArrayList<Equipe>(); 
		
		//4 premiers matchs 
		Match quart1 = new Match(winnersPoule.get(0),  winnersPoule.get(3), simu); 
		Match quart2 = new Match(winnersPoule.get(1),  winnersPoule.get(2), simu); 
		Match quart3 = new Match(winnersPoule.get(4),  winnersPoule.get(7), simu); 
		Match quart4 = new Match(winnersPoule.get(5),  winnersPoule.get(6), simu); 

		ArrayList<Match> quarts = new ArrayList<Match>();
		quarts.add(quart1);
		quarts.add(quart2);
		quarts.add(quart3);
		quarts.add(quart4);
		
		simu.setQuarts(quarts);
		
		//2 matchs demi
		Match demi1 = new Match(quart1.getVainqueur(),  quart2.getVainqueur(), simu); 
		Match demi2 = new Match(quart3.getVainqueur(),  quart4.getVainqueur(), simu); 

		ArrayList<Match> demis = new ArrayList<Match>();
		demis.add(demi1);
		demis.add(demi2);
		
		simu.setDemis(demis);
		
		//Match final
		Match final1 = new Match(demi1.getVainqueur(),  demi2.getVainqueur(), simu); 

		ArrayList<Match> finale = new ArrayList<Match>();
		finale.add(final1);
		
		simu.setFinale(finale);
		
		classement.add(final1.getVainqueur());
		classement.add(final1.getPerdant());
		
		//Match 3ieme et 4ieme
		Match perdantsFinale = new Match(demi1.getPerdant(),  demi2.getPerdant(), simu); 
		classement.add(perdantsFinale.getVainqueur());
		classement.add(perdantsFinale.getPerdant());
		
		//Matchs 5ieme � 8ieme
		Match perdantsPhaseFinale1 = new Match(quart1.getPerdant(),  quart3.getPerdant(), simu); 
		Match perdantsPhaseFinale2 = new Match(quart2.getPerdant(),  quart4.getPerdant(), simu); 
		classement.add(perdantsPhaseFinale1.getVainqueur());
		classement.add(perdantsPhaseFinale1.getPerdant());
		classement.add(perdantsPhaseFinale2.getVainqueur());
		classement.add(perdantsPhaseFinale2.getPerdant());

		return classement;		
	}

	public static ArrayList<Joueur> getMeilleursTireurs(Equipe team) {
		
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

	public String penaltyProcess(Match match) {

		ArrayList<Joueur> tireurEquipeDomicile = getMeilleursTireurs(match.getA());
		ArrayList<Joueur> tireurEquipeExterieur = getMeilleursTireurs(match.getB());
		Gardien gardienEquipeDomicile = match.getA().trouveGardien();
		Gardien gardienEquipeExterieur = match.getB().trouveGardien();
		
		int calculGardienDomicile = gardienEquipeDomicile.getReactivite()+gardienEquipeDomicile.getDetente();
		int calculGardienExterieur = gardienEquipeExterieur.getReactivite()+gardienEquipeDomicile.getDetente();
		
		int scoreEquipeDomicile = 0;
		int scoreEquipeExterieur = 0;
		
		int calculJoueur = 0;
		
		while(scoreEquipeDomicile == scoreEquipeExterieur) {
		
			for(int indexTireur = 0; indexTireur < tireurEquipeDomicile.size(); indexTireur++) {
				
				calculJoueur = tireurEquipeDomicile.get(indexTireur).getAttaque()*2;
				
				if(calculJoueur > 5+calculGardienExterieur) {
					scoreEquipeDomicile++;
				}
				
				//System.out.println(match.getA().getPays()+" "+ scoreEquipeDomicile +"-"+ scoreEquipeExterieur+" "+ match.getB().getPays() );
				calculJoueur = tireurEquipeExterieur.get(indexTireur).getAttaque()*2;
				
				if(calculJoueur > 5+calculGardienDomicile) {
					scoreEquipeExterieur++;
				}
				//System.out.println(match.getA().getPays()+" "+ scoreEquipeDomicile +"-"+ scoreEquipeExterieur+" "+ match.getB().getPays() );
				
			}
			
		}
		
		return null;
	}
}
