package application.entites;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class DonneesTPMA implements Serializable {
	private String palier;
	private float vitesse;
	private int duree;
	private float vo2;
	private int fc;

	public DonneesTPMA() {
		super();
	}

	public DonneesTPMA(String palier, float vitesse, int duree, float vo2, int fc) {
		super();
		this.palier = palier;
		this.vitesse = vitesse;
		this.duree = duree;
		this.vo2 = vo2;
		this.fc = fc;
	}

	public String getPalier() {
		return palier;
	}

	public void setPalier(String palier) {
		this.palier = palier;
	}

	public float getVitesse() {
		return vitesse;
	}

	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public float getVo2() {
		return vo2;
	}

	public void setVo2(float vo2) {
		this.vo2 = vo2;
	}

	public int getFc() {
		return fc;
	}

	public void setFc(int fc) {
		this.fc = fc;
	}
}