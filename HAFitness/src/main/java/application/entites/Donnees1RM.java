package application.entites;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Donnees1RM implements Serializable {
	private String atelier;
	private float barre;
	private float charge;
	private int nbrRepetitions;

	public Donnees1RM() {
		super();
	}

	public Donnees1RM(String atelier, float barre, float charge, int nbrRepetitions) {
		super();
		this.atelier = atelier;
		this.barre = barre;
		this.charge = charge;
		this.nbrRepetitions = nbrRepetitions;
	}

	public String getAtelier() {
		return atelier;
	}

	public void setAtelier(String atelier) {
		this.atelier = atelier;
	}

	public float getBarre() {
		return barre;
	}

	public void setBarre(float barre) {
		this.barre = barre;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
	}

	public int getNbrRepetitions() {
		return nbrRepetitions;
	}

	public void setNbrRepetitions(int nbrRepetitions) {
		this.nbrRepetitions = nbrRepetitions;
	}
}