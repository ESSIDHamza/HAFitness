package application.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "FICHES")
public class Fiche implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@ElementCollection
	@CollectionTable(name = "TESTS_FORCE_1_RM_BARRE")
	private List<Float> testForce1RMBarre = new ArrayList<Float>(13);
	@ElementCollection
	@CollectionTable(name = "TESTS_FORCE_1_RM_CHARGE")
	private List<Float> testForce1RMCharge = new ArrayList<Float>(13);
	@ElementCollection
	@CollectionTable(name = "TESTS_FORCE_1_RM_NOMBRE_REPETITIONS")
	private List<Integer> testForce1RMNbrRepetitions = new ArrayList<Integer>(13);
	@ElementCollection
	@CollectionTable(name = "TESTS_PROGRESSIFS_MAXIMAUX_SUR_TAPIS_ROULANT")
	private List<Integer> testProgressifMaximalSurTapisRoulant = new ArrayList<Integer>(36);
	@Transient
	private int pouls1;
	@Transient
	private int pouls2;
	@Transient
	private int pouls3;

	public Fiche() {
		super();
		for (int i = 0; i < 13; i++) {
			testForce1RMBarre.add(0f);
			testForce1RMCharge.add(0f);
			testForce1RMNbrRepetitions.add(0);
		}
		for (int i = 0; i < 36; i++)
			testProgressifMaximalSurTapisRoulant.add(0);
	}

	public Fiche(List<Float> testForce1RMBarre, List<Float> testForce1RMCharge,
			List<Integer> testForce1RMNbrRepetitions, List<Integer> testProgressifMaximalSurTapisRoulant, int pouls1,
			int pouls2, int pouls3) {
		super();
		for (int i = 0; i < 13; i++) {
			testForce1RMBarre.add(0f);
			testForce1RMCharge.add(0f);
			testForce1RMNbrRepetitions.add(0);
		}
		for (int i = 0; i < 36; i++)
			testProgressifMaximalSurTapisRoulant.add(0);
		this.testForce1RMBarre = testForce1RMBarre;
		this.testForce1RMCharge = testForce1RMCharge;
		this.testForce1RMNbrRepetitions = testForce1RMNbrRepetitions;
		this.testProgressifMaximalSurTapisRoulant = testProgressifMaximalSurTapisRoulant;
		this.pouls1 = pouls1;
		this.pouls2 = pouls2;
		this.pouls3 = pouls3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Float> getTestForce1RMBarre() {
		return testForce1RMBarre;
	}

	public void setTestForce1RMBarre(List<Float> testForce1RMBarre) {
		this.testForce1RMBarre = testForce1RMBarre;
	}

	public List<Float> getTestForce1RMCharge() {
		return testForce1RMCharge;
	}

	public void setTestForce1RMCharge(List<Float> testForce1RMCharge) {
		this.testForce1RMCharge = testForce1RMCharge;
	}

	public List<Integer> getTestForce1RMNbrRepetitions() {
		return testForce1RMNbrRepetitions;
	}

	public void setTestForce1RMNbrRepetitions(List<Integer> testForce1RMNbrRepetitions) {
		this.testForce1RMNbrRepetitions = testForce1RMNbrRepetitions;
	}

	public List<Integer> getTestProgressifMaximalSurTapisRoulant() {
		return testProgressifMaximalSurTapisRoulant;
	}

	public void setTestProgressifMaximalSurTapisRoulant(List<Integer> testProgressifMaximalSurTapisRoulant) {
		this.testProgressifMaximalSurTapisRoulant = testProgressifMaximalSurTapisRoulant;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + pouls1;
		result = prime * result + pouls2;
		result = prime * result + pouls3;
		result = prime * result + ((testForce1RMBarre == null) ? 0 : testForce1RMBarre.hashCode());
		result = prime * result + ((testForce1RMCharge == null) ? 0 : testForce1RMCharge.hashCode());
		result = prime * result + ((testForce1RMNbrRepetitions == null) ? 0 : testForce1RMNbrRepetitions.hashCode());
		result = prime * result + ((testProgressifMaximalSurTapisRoulant == null) ? 0
				: testProgressifMaximalSurTapisRoulant.hashCode());
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
		Fiche other = (Fiche) obj;
		if (id != other.id)
			return false;
		if (pouls1 != other.pouls1)
			return false;
		if (pouls2 != other.pouls2)
			return false;
		if (pouls3 != other.pouls3)
			return false;
		if (testForce1RMBarre == null) {
			if (other.testForce1RMBarre != null)
				return false;
		} else if (!testForce1RMBarre.equals(other.testForce1RMBarre))
			return false;
		if (testForce1RMCharge == null) {
			if (other.testForce1RMCharge != null)
				return false;
		} else if (!testForce1RMCharge.equals(other.testForce1RMCharge))
			return false;
		if (testForce1RMNbrRepetitions == null) {
			if (other.testForce1RMNbrRepetitions != null)
				return false;
		} else if (!testForce1RMNbrRepetitions.equals(other.testForce1RMNbrRepetitions))
			return false;
		if (testProgressifMaximalSurTapisRoulant == null) {
			if (other.testProgressifMaximalSurTapisRoulant != null)
				return false;
		} else if (!testProgressifMaximalSurTapisRoulant.equals(other.testProgressifMaximalSurTapisRoulant))
			return false;
		return true;
	}
}