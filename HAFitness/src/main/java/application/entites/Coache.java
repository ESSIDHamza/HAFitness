package application.entites;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "COACHES")
public class Coache implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "NOM")
	private String nom;
	@Column(name = "PRENOM")
	private String prenom;
	@Column(name = "AGE")
	private int age;
	@Column(name = "SEXE")
	private char sexe;
	@Column(name = "TAILLE")
	private float taille;
	@Column(name = "POIDS")
	private float poids;
	@Column(name = "MORPHOLOGIE")
	private String morphologie;
	@Column(name = "OBJECTIF")
	private String objectif;
	@Column(name = "DISPONIBILITE")
	private int disponibilite;
	@Column(name = "BLESSURE")
	private String blessure;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FICHE_ID")
	private Fiche fiche;
	@Column(name = "NIVEAU")
	private String niveau;
	@Column(name = "EMAIL")
	private String mail;
	@Column(name = "TOUR_BICEPS")
	private int tourBiceps;
	@Column(name = "TOUR_PECTORAUX")
	private int tourPectoraux;
	@Column(name = "TOUR_TAILLE")
	private int tourTaille;
	@Column(name = "TOUR_HANCHES")
	private int tourHanches;
	@Column(name = "TOUR_MOLLET")
	private int tourMollet;

	public Coache() {
		super();
	}

	public Coache(String nom, String prenom, int age, char sexe, float taille, float poids, String morphologie,
			String objectif, int disponibilite, String blessure, Fiche fiche, String niveau, String mail,
			int tourBiceps, int tourPectoraux, int tourTaille, int tourHanches, int tourMollet) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.sexe = sexe;
		this.taille = taille;
		this.poids = poids;
		this.morphologie = morphologie;
		this.objectif = objectif;
		this.disponibilite = disponibilite;
		this.blessure = blessure;
		this.fiche = fiche;
		this.niveau = niveau;
		this.mail = mail;
		this.tourBiceps = tourBiceps;
		this.tourPectoraux = tourPectoraux;
		this.tourTaille = tourTaille;
		this.tourHanches = tourHanches;
		this.tourMollet = tourMollet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getMorphologie() {
		return morphologie;
	}

	public void setMorphologie(String morphologie) {
		this.morphologie = morphologie;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public int getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(int disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getBlessure() {
		return blessure;
	}

	public void setBlessure(String blessure) {
		this.blessure = blessure;
	}

	public Fiche getFiche() {
		return fiche;
	}

	public void setFiche(Fiche fiche) {
		this.fiche = fiche;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getTourBiceps() {
		return tourBiceps;
	}

	public void setTourBiceps(int tourBiceps) {
		this.tourBiceps = tourBiceps;
	}

	public int getTourPectoraux() {
		return tourPectoraux;
	}

	public void setTourPectoraux(int tourPectoraux) {
		this.tourPectoraux = tourPectoraux;
	}

	public int getTourTaille() {
		return tourTaille;
	}

	public void setTourTaille(int tourTaille) {
		this.tourTaille = tourTaille;
	}

	public int getTourHanches() {
		return tourHanches;
	}

	public void setTourHanches(int tourHanches) {
		this.tourHanches = tourHanches;
	}

	public int getTourMollet() {
		return tourMollet;
	}

	public void setTourMollet(int tourMollet) {
		this.tourMollet = tourMollet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((blessure == null) ? 0 : blessure.hashCode());
		result = prime * result + disponibilite;
		result = prime * result + ((fiche == null) ? 0 : fiche.hashCode());
		result = prime * result + id;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((morphologie == null) ? 0 : morphologie.hashCode());
		result = prime * result + ((niveau == null) ? 0 : niveau.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((objectif == null) ? 0 : objectif.hashCode());
		result = prime * result + Float.floatToIntBits(poids);
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + sexe;
		result = prime * result + Float.floatToIntBits(taille);
		result = prime * result + tourBiceps;
		result = prime * result + tourHanches;
		result = prime * result + tourMollet;
		result = prime * result + tourPectoraux;
		result = prime * result + tourTaille;
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
		Coache other = (Coache) obj;
		if (age != other.age)
			return false;
		if (blessure == null) {
			if (other.blessure != null)
				return false;
		} else if (!blessure.equals(other.blessure))
			return false;
		if (disponibilite != other.disponibilite)
			return false;
		if (fiche == null) {
			if (other.fiche != null)
				return false;
		} else if (!fiche.equals(other.fiche))
			return false;
		if (id != other.id)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (morphologie == null) {
			if (other.morphologie != null)
				return false;
		} else if (!morphologie.equals(other.morphologie))
			return false;
		if (niveau == null) {
			if (other.niveau != null)
				return false;
		} else if (!niveau.equals(other.niveau))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (objectif == null) {
			if (other.objectif != null)
				return false;
		} else if (!objectif.equals(other.objectif))
			return false;
		if (Float.floatToIntBits(poids) != Float.floatToIntBits(other.poids))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (sexe != other.sexe)
			return false;
		if (Float.floatToIntBits(taille) != Float.floatToIntBits(other.taille))
			return false;
		if (tourBiceps != other.tourBiceps)
			return false;
		if (tourHanches != other.tourHanches)
			return false;
		if (tourMollet != other.tourMollet)
			return false;
		if (tourPectoraux != other.tourPectoraux)
			return false;
		if (tourTaille != other.tourTaille)
			return false;
		return true;
	}
}