package application.entites;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class ResultatsCalculs implements Serializable {
	private float poidsIdeal;
	private float BMI;
	private String interpretationBMI;
	private float MB;
	private float MG;
	private float masseMaigre;
	private float masseHydrique;
	private int frequenceCardiaqueMax;
	private float indiceRuffier;
	private String interpretationIndiceRuffier;
	private float indiceDickson;
	private String interpretationIndiceDickson;

	public ResultatsCalculs() {
		super();
	}

	public ResultatsCalculs(float poidsIdeal, float bMI, String interpretationBMI, float mB, float mG,
			float masseMaigre, float masseHydrique, int frequenceCardiaqueMax, float indiceRuffier,
			String interpretationIndiceRuffier, float indiceDickson, String interpretationIndiceDickson) {
		super();
		this.poidsIdeal = poidsIdeal;
		BMI = bMI;
		this.interpretationBMI = interpretationBMI;
		MB = mB;
		MG = mG;
		this.masseMaigre = masseMaigre;
		this.masseHydrique = masseHydrique;
		this.frequenceCardiaqueMax = frequenceCardiaqueMax;
		this.indiceRuffier = indiceRuffier;
		this.interpretationIndiceRuffier = interpretationIndiceRuffier;
		this.indiceDickson = indiceDickson;
		this.interpretationIndiceDickson = interpretationIndiceDickson;
	}

	public float getPoidsIdeal() {
		return poidsIdeal;
	}

	public void setPoidsIdeal(float poidsIdeal) {
		this.poidsIdeal = poidsIdeal;
	}

	public float getBMI() {
		return BMI;
	}

	public void setBMI(float bMI) {
		BMI = bMI;
	}

	public String getInterpretationBMI() {
		return interpretationBMI;
	}

	public void setInterpretationBMI(String interpretationBMI) {
		this.interpretationBMI = interpretationBMI;
	}

	public float getMB() {
		return MB;
	}

	public void setMB(float mB) {
		MB = mB;
	}

	public float getMG() {
		return MG;
	}

	public void setMG(float mG) {
		MG = mG;
	}

	public float getMasseMaigre() {
		return masseMaigre;
	}

	public void setMasseMaigre(float masseMaigre) {
		this.masseMaigre = masseMaigre;
	}

	public float getMasseHydrique() {
		return masseHydrique;
	}

	public void setMasseHydrique(float masseHydrique) {
		this.masseHydrique = masseHydrique;
	}

	public int getFrequenceCardiaqueMax() {
		return frequenceCardiaqueMax;
	}

	public void setFrequenceCardiaqueMax(int frequenceCardiaqueMax) {
		this.frequenceCardiaqueMax = frequenceCardiaqueMax;
	}

	public float getIndiceRuffier() {
		return indiceRuffier;
	}

	public void setIndiceRuffier(float indiceRuffier) {
		this.indiceRuffier = indiceRuffier;
	}

	public String getInterpretationIndiceRuffier() {
		return interpretationIndiceRuffier;
	}

	public void setInterpretationIndiceRuffier(String interpretationIndiceRuffier) {
		this.interpretationIndiceRuffier = interpretationIndiceRuffier;
	}

	public float getIndiceDickson() {
		return indiceDickson;
	}

	public void setIndiceDickson(float indiceDickson) {
		this.indiceDickson = indiceDickson;
	}

	public String getInterpretationIndiceDickson() {
		return interpretationIndiceDickson;
	}

	public void setInterpretationIndiceDickson(String interpretationIndiceDickson) {
		this.interpretationIndiceDickson = interpretationIndiceDickson;
	}
}