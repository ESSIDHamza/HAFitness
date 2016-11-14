package application.entites;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class DonneesCalculs implements Serializable {
	private int age;
	private char sexe;
	private float taille;
	private float poids;
	private int pouls1;
	private int pouls2;
	private int pouls3;
	private int disponibilite;

	public DonneesCalculs() {
		super();
	}

	public DonneesCalculs(int age, char sexe, float taille, float poids, int pouls1, int pouls2, int pouls3,
			int disponibilite) {
		super();
		this.age = age;
		this.sexe = sexe;
		this.taille = taille;
		this.poids = poids;
		this.pouls1 = pouls1;
		this.pouls2 = pouls2;
		this.pouls3 = pouls3;
		this.disponibilite = disponibilite;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public float getTaille() {
		return taille;
	}

	public void setTaille(float taille) {
		this.taille = taille;
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public int getPouls1() {
		return pouls1;
	}

	public void setPouls1(int pouls1) {
		this.pouls1 = pouls1;
	}

	public int getPouls2() {
		return pouls2;
	}

	public void setPouls2(int pouls2) {
		this.pouls2 = pouls2;
	}

	public int getPouls3() {
		return pouls3;
	}

	public void setPouls3(int pouls3) {
		this.pouls3 = pouls3;
	}

	public int getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(int disponibilite) {
		this.disponibilite = disponibilite;
	}
}