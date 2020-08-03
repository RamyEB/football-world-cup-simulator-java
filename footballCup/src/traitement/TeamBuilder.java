package traitement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import data.Attaquant;
import data.Defenseur;
import data.Entraineur;
import data.Equipe;
import data.Gardien;
import data.Joueur;
import data.Utils;



public class TeamBuilder {
	
	public static ArrayList<String> nomEquipe = new ArrayList<>(Arrays.asList(Utils.nomEquipe));
	
	public static Equipe createEquipe() {
		
		ArrayList<Joueur> joueursTerrain = new ArrayList<Joueur>();
		ArrayList<Joueur> joueursBanc = new ArrayList<Joueur>();
		
		Random rand = new Random();
		int moyenneEquipe = rand.nextInt(21) + 80;
		
		String country = getCountry();
		String meteo = getMeteo();
		
		Entraineur coach = createEntraineur(moyenneEquipe);
		
		joueursTerrain.add(createGardien(joueursTerrain, joueursBanc, moyenneEquipe));
		joueursBanc.add(createGardien(joueursTerrain, joueursBanc, moyenneEquipe));
		
		for(int i=0; i<4; i++) {
			
			joueursTerrain.add(createDefenseur(joueursTerrain, joueursBanc, moyenneEquipe));
			joueursBanc.add(createDefenseur(joueursTerrain, joueursBanc, moyenneEquipe));
			
			joueursTerrain.add(createMilieu(joueursTerrain, joueursBanc, moyenneEquipe));
			joueursBanc.add(createMilieu(joueursTerrain, joueursBanc, moyenneEquipe));
		}
		
		for(int i=0; i<2; i++) {
			
			joueursTerrain.add(createAttaquant(joueursTerrain, joueursBanc, moyenneEquipe));
			joueursBanc.add(createAttaquant(joueursTerrain, joueursBanc, moyenneEquipe));
		}
		
		return new Equipe(joueursTerrain, joueursBanc, meteo, country, coach);
	}
	
	public static String getCountry() {
		
		
		Random rand = new Random();
		int indice = rand.nextInt(nomEquipe.size());
		String country = nomEquipe.get(indice);
		nomEquipe.remove(country);
		return country;
	}
	
	public static String getMeteo() {
		
		Random rand = new Random();
		int indice = rand.nextInt(Utils.meteo.length);
		return Utils.meteo[indice];
	}
	
	public static Joueur createMilieu(ArrayList<Joueur> listeJoueursTerrain, ArrayList<Joueur> listeJoueursBanc, int moyenne) {
		
		String identite[] = getIdentity(listeJoueursBanc, listeJoueursBanc);
		ArrayList<Integer> caracs = getCarac(moyenne, 5);
		return new Joueur(identite[0], identite[1], caracs.get(0), caracs.get(1), caracs.get(2), caracs.get(3), caracs.get(4));
	}
	
	public static Defenseur createDefenseur(ArrayList<Joueur> listeJoueursTerrain, ArrayList<Joueur> listeJoueursBanc, int moyenne) {
		
		String identite[] = getIdentity(listeJoueursBanc, listeJoueursBanc);
		ArrayList<Integer> caracs = getCarac(moyenne, 8);
		return new Defenseur(identite[0], identite[1], caracs.get(0), caracs.get(1), caracs.get(2), caracs.get(3), caracs.get(4), caracs.get(5), caracs.get(6), caracs.get(7));
	}
	
	public static Attaquant createAttaquant(ArrayList<Joueur> listeJoueursTerrain, ArrayList<Joueur> listeJoueursBanc, int moyenne) {
		
		String identite[] = getIdentity(listeJoueursBanc, listeJoueursBanc);
		ArrayList<Integer> caracs = getCarac(moyenne, 7);
		return new Attaquant(identite[0], identite[1], caracs.get(0), caracs.get(1), caracs.get(2), caracs.get(3), caracs.get(4), caracs.get(5), caracs.get(6));
	}
	
	public static Gardien createGardien(ArrayList<Joueur> listeJoueursTerrain, ArrayList<Joueur> listeJoueursBanc, int moyenne) {
		
		String identite[] = getIdentity(listeJoueursBanc, listeJoueursBanc);
		ArrayList<Integer> caracs = getCarac(moyenne, 7);
		return new Gardien(identite[0], identite[1], caracs.get(0), caracs.get(1), caracs.get(2), caracs.get(3), caracs.get(4), caracs.get(5), caracs.get(6));
	}
	
	public static Entraineur createEntraineur(int moyenne) {
		
		ArrayList<Integer> carac = getCarac(moyenne, 2);
		return new Entraineur(carac.get(0), carac.get(1));
	}
	
	public static Boolean freeIdentity(String nom, String prenom, ArrayList<Joueur> listeJoueursTerrain, ArrayList<Joueur> listeJoueursBanc) {
		
		for(Joueur j : listeJoueursTerrain) {
			
			if(j.getNom().contentEquals(nom)) return false;
			if(j.getPrenom().contentEquals(prenom)) return false;
		}
		
		for(Joueur j : listeJoueursBanc) {
			
			if(j.getNom().contentEquals(nom)) return false;
			if(j.getPrenom().contentEquals(prenom)) return false;
		}
		
		return true;
	}

	public static String[] getIdentity(ArrayList<Joueur> listeJoueursTerrain, ArrayList<Joueur> listeJoueursBanc) {
		
		String nom, prenom;
		
		do {
				
			int rand1 = (int)(Math.random()*100)%Utils.tableauNoms.length;
			int rand2 = (int)(Math.random()*100)%Utils.tableauPrenoms.length;
			nom = Utils.tableauNoms[rand1];
			prenom = Utils.tableauPrenoms[rand2];
			
		} while(!freeIdentity(nom, prenom, listeJoueursTerrain, listeJoueursBanc));
		
		return new String[]{nom, prenom};
	
	}

	public static ArrayList<Integer> getCarac(int moyenne, int nb) {
		
		ArrayList<Integer> caracs = new ArrayList<Integer>();
		
		int max = moyenne+10;
		int min = moyenne-10;
		Random rand = new Random();
		
		for(int i=0; i<nb; i++) {
			
			int carac = rand.nextInt(max-min+1)+min;
			caracs.add(carac);
		}
		
		return caracs;
	}

	
}
	