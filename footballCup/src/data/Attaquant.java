package data;

public class Attaquant extends Joueur{

	private int puissanceTir = 0;
	private int precisionTir = 0;
	
	public Attaquant(String nom, String prenom, int experience, int attaque, int defense, int passe, int dribble,
			int puissanceTir, int precisionTir) {
		super(prenom, nom, experience, attaque, defense, passe, dribble);
		this.puissanceTir = puissanceTir;
		this.precisionTir = precisionTir;
	}

	public Attaquant() {
		super();
	}

	public int getPuissanceTir() {
		return puissanceTir;
	}

	public void setPuissanceTir(int puissanceTir) {
		this.puissanceTir = puissanceTir;
	}

	public int getPrecisionTir() {
		return precisionTir;
	}

	public void setPrecisionTir(int precisionTir) {
		this.precisionTir = precisionTir;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + precisionTir;
		result = prime * result + puissanceTir;
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attaquant other = (Attaquant) obj;
		if (precisionTir != other.precisionTir)
			return false;
		if (puissanceTir != other.puissanceTir)
			return false;
		return true;
	}

	
	
	
}
