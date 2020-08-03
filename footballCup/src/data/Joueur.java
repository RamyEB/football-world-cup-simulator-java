package data;

public class Joueur {

	private int forcePhy = 0;
	private int forceMent = 0;

	private int experience = 0;
	private int attaque = 0;
	private int defense = 0;
	private int passe = 0;
	private int dribble = 0;
	private String prenom=null;
	private String nom=null;
	
	public Joueur(String prenom, String nom, int experience, int attaque, int defense, int passe, int dribble) {
		
		super();
		this.forcePhy = 0;
		this.forceMent = 0;
		this.experience = experience;
		this.attaque = attaque;
		this.defense = defense;
		this.passe = passe;
		this.dribble = dribble;
		this.prenom = prenom;
		this.nom = nom;
	}

	public Joueur() {
		super();
	}

	public int getForcePhy() {
		return forcePhy;
	}

	public void setForcePhy(int forcePhy) {
		this.forcePhy = forcePhy;
	}

	public int getForceMent() {
		return forceMent;
	}

	public void setForceMent(int forceMent) {
		this.forceMent = forceMent;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getPasse() {
		return passe;
	}

	public void setPasse(int passe) {
		this.passe = passe;
	}

	public int getDribble() {
		return dribble;
	}

	public void setDribble(int dribble) {
		this.dribble = dribble;
	}
	

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	

	@Override
	public String toString() {
		return prenom + " " + nom+"\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attaque;
		result = prime * result + defense;
		result = prime * result + dribble;
		result = prime * result + experience;
		result = prime * result + forceMent;
		result = prime * result + forcePhy;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + passe;
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (attaque != other.attaque)
			return false;
		if (defense != other.defense)
			return false;
		if (dribble != other.dribble)
			return false;
		if (experience != other.experience)
			return false;
		if (forceMent != other.forceMent)
			return false;
		if (forcePhy != other.forcePhy)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (passe != other.passe)
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

}
