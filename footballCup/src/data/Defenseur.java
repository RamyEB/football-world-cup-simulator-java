package data;

public class Defenseur extends Joueur {

	private int marquage = 0;
	private int tacle = 0;
	private int interception = 0;

	public Defenseur(String nom, String prenom, int experience, int attaque, int defense, int passe, int dribble,
			int marquage, int tacle, int interception) {
		
		super(prenom, nom, experience, attaque, defense, passe, dribble);
		this.marquage = marquage;
		this.tacle = tacle;
		this.interception = interception;
	}

	public Defenseur() {
		super();
	}

	public int getMarquage() {
		return marquage;
	}

	public void setMarquage(int marquage) {
		this.marquage = marquage;
	}

	public int getTacle() {
		return tacle;
	}

	public void setTacle(int tacle) {
		this.tacle = tacle;
	}

	public int getInterception() {
		return interception;
	}

	public void setInterception(int interception) {
		this.interception = interception;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + interception;
		result = prime * result + marquage;
		result = prime * result + tacle;
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
		Defenseur other = (Defenseur) obj;
		if (interception != other.interception)
			return false;
		if (marquage != other.marquage)
			return false;
		if (tacle != other.tacle)
			return false;
		return true;
	}

	

}
