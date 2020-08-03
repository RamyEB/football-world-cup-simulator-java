package data;

public class Entraineur {
	private int experience;
	private int motivateur;
	
	public Entraineur() {
		this.experience = 0;
		this.motivateur = 0;
	}
	
	public Entraineur(int experience, int motivateur) {
		this.experience = experience;
		this.motivateur = motivateur;
	}
	
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getMotivateur() {
		return motivateur;
	}
	public void setMotivateur(int motivateur) {
		this.motivateur = motivateur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + experience;
		result = prime * result + motivateur;
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
		Entraineur other = (Entraineur) obj;
		if (experience != other.experience)
			return false;
		if (motivateur != other.motivateur)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entraineur [experience=" + experience + ", motivateur=" + motivateur + "]";
	}
	
	
}
