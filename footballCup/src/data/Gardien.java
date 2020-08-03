package data;

public class Gardien extends Joueur {

	private int reactivite = 0;
	private int detente = 0;

	public Gardien(String prenom, String nom, int experience, int attaque, int defense, int passe, int dribble,
			int reactivite, int detente) {
		super(prenom, nom, experience, attaque, defense, passe, dribble);
		this.reactivite = reactivite;
		this.detente = detente;
	}

	public Gardien(int reactivite, int detente) {
		super();
		this.reactivite = reactivite;
		this.detente = detente;
	}

	public int getReactivite() {
		return reactivite;
	}

	public void setReactivite(int reactivite) {
		this.reactivite = reactivite;
	}

	public int getDetente() {
		return detente;
	}

	public void setDetente(int detente) {
		this.detente = detente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + detente;
		result = prime * result + reactivite;
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
		Gardien other = (Gardien) obj;
		if (detente != other.detente)
			return false;
		if (reactivite != other.reactivite)
			return false;
		return true;
	}

	

}
