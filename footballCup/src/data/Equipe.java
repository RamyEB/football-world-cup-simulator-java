package data;

import java.util.ArrayList;
import java.util.Random;

public class Equipe {
	
	private ArrayList<Joueur> listeJoueursTerrain = new ArrayList<Joueur>();
	private ArrayList<Joueur> listeJoueursBanc= new ArrayList<Joueur>();
	private String preferenceClimat;
	private String pays;
	private Entraineur entraineur;
	private Integer lastScore;
	
	
	public Equipe(ArrayList<Joueur> listeJoueursTerrain, ArrayList<Joueur> listeJoueursBanc, String preferenceClimat,
			String pays, Entraineur entraineur) {
		
		this.listeJoueursTerrain = listeJoueursTerrain;
		this.listeJoueursBanc = listeJoueursBanc;
		this.preferenceClimat = preferenceClimat;
		this.pays = pays;
		this.entraineur = entraineur;
		this.lastScore = 0;
	}

	
	public Integer getLastScore() {
		return lastScore;
	}


	public void setLastScore(Integer lastScore) {
		this.lastScore = lastScore;
	}


	public ArrayList<Joueur> getListeJoueursTerrain() {
		return listeJoueursTerrain;
	}


	public void setListeJoueursTerrain(ArrayList<Joueur> listeJoueursTerrain) {
		this.listeJoueursTerrain = listeJoueursTerrain;
	}


	public ArrayList<Joueur> getListeJoueursBanc() {
		return listeJoueursBanc;
	}


	public void setListeJoueursBanc(ArrayList<Joueur> listeJoueursBanc) {
		this.listeJoueursBanc = listeJoueursBanc;
	}


	public String getPreferenceClimat() {
		return preferenceClimat;
	}


	public void setPreferenceClimat(String preferenceClimat) {
		this.preferenceClimat = preferenceClimat;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	public Entraineur getEntraineur() {
		return entraineur;
	}


	public void setEntraineur(Entraineur entraineur) {
		this.entraineur = entraineur;
	}

	public Gardien trouveGardien() {
		ArrayList<Joueur> titulaires = listeJoueursTerrain;
		for(Joueur joueur : titulaires) {
			if(joueur instanceof Gardien) {
				return (Gardien)joueur;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String str = new String();
		str += pays;
				/*+ "\n\n";
		for(Joueur j : listeJoueursTerrain) {
			
			str += (j.toString());
		}*/
		return str;
	}
	
	
	public void setFormeForMatch(String meteo, String equipeOrganisatrice) {
		
		Random rand = new Random();
		
		for(Joueur j : listeJoueursTerrain) {
			
			int carac = rand.nextInt(20)+80;
			j.setForceMent(carac);
			
			carac = rand.nextInt(20)+80;
			j.setForcePhy(carac);
			
			if(pays.contentEquals(equipeOrganisatrice)) {
				
				j.setForceMent(j.getForceMent()+Utils.bonusDomicile);
			}
			if(meteo.contentEquals(preferenceClimat)) {
				
				j.setForceMent(j.getForceMent()+Utils.bonusMeteo);
			}
		}
	}
	



}
