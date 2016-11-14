package application.metier;

import org.springframework.stereotype.Service;

import application.entites.Coache;
import application.entites.Fiche;

@Service
public class HAFitnessUtils {

	public float getPoidsIdeal(Coache coache) {
		float formuleLorentzAge = 50 + (coache.getTaille() - 150) / 4 + (coache.getAge() - 20) / 4f;
		float formuleLorentz;
		if (coache.getSexe() == 'M')
			formuleLorentz = coache.getTaille() - 100 - (coache.getTaille() - 150) / 4f;
		else
			formuleLorentz = coache.getTaille() - 100 - (coache.getTaille() - 150) / 2f;
		float resultatLorentz;
		if ((coache.getAge() > 18) && (coache.getTaille() < 220) && (coache.getTaille() > 140))
			resultatLorentz = (formuleLorentzAge + formuleLorentz) / 2;
		else
			resultatLorentz = formuleLorentzAge;
		float resultatPeckPounds;
		if (coache.getAge() > 18)
			if (coache.getSexe() == 'M')
				resultatPeckPounds = (float) (-130.736 + 4.064 * coache.getTaille() / 2.54);
			else
				resultatPeckPounds = (float) (-111.621 + 3.636 * coache.getTaille() / 2.54);
		else if (coache.getSexe() == 'M')
			resultatPeckPounds = (float) (-59.6035 + 5.2878 * coache.getTaille() / 2.54
					- 0.123939 * Math.pow(coache.getTaille() / 2.54, 2)
					+ 0.00128936 * Math.pow(coache.getTaille() / 2.54, 3));
		else
			resultatPeckPounds = (float) (-77.55796 + 6.93728 * coache.getTaille() / 2.54
					- 0171703. * Math.pow(coache.getTaille() / 2.54, 2)
					+ 0.001726 * Math.pow(coache.getTaille() / 2.54, 3));
		float resultatPeck = (float) (resultatPeckPounds * 0.454);
		return (resultatLorentz + resultatPeck) / 2;
	}

	public float getBMI(Coache coache) {
		return (float) (coache.getPoids() / Math.pow(coache.getTaille() / 100, 2));
	}

	public String getInterpretationBMI(Coache coache) {
		if (getBMI(coache) < 18.5)
			return "Vous êtes maigre !";
		else if ((getBMI(coache) >= 18.5) && (getBMI(coache) < 24.9))
			if (coache.getSexe() == 'M')
				return "Vous êtes normal !";
			else
				return "Vous êtes normale !";
		else if ((getBMI(coache) >= 24.9) && (getBMI(coache) < 29.9))
			return "Vous êtes en surpoids !";
		else if ((getBMI(coache) >= 29.9) && (getBMI(coache) < 40))
			return "Vous êtes obèse !";
		else
			return "Vous êtes massivement obèse !";
	}

	public float getMB(Coache coache) {
		float resultatHarrisBenedictMJ;
		if (coache.getSexe() == 'M')
			resultatHarrisBenedictMJ = (float) (0.276 + 0.0573 * coache.getPoids() + 2.073 * coache.getTaille() / 100
					- 0.0285 * coache.getAge());
		else
			resultatHarrisBenedictMJ = (float) (2.741 + 0.0402 * coache.getPoids() + 0.711 * coache.getTaille() / 100
					- 0.0197 * coache.getAge());
		float resultatBlackMJ;
		if (coache.getSexe() == 'M')
			resultatBlackMJ = (float) (1.083 * Math.pow(coache.getPoids(), 0.48)
					* Math.pow(coache.getTaille() / 100, 0.5) * Math.pow(coache.getAge(), -0.13));
		else
			resultatBlackMJ = (float) (0.963 * Math.pow(coache.getPoids(), 0.48)
					* Math.pow(coache.getTaille() / 100, 0.5) * Math.pow(coache.getAge(), -0.13));
		float resultatMJ = (resultatHarrisBenedictMJ + resultatBlackMJ) / 2;
		return (float) (resultatMJ * 239.006);
	}

	public float getMG(Coache coache) {
		if (coache.getDisponibilite() <= 2)
			return getMB(coache) + getMB(coache) * 0.3f;
		if ((coache.getDisponibilite() >= 3) && (coache.getDisponibilite() <= 4))
			return getMB(coache) + getMB(coache) * 0.5f;
		else
			return getMB(coache) + getMB(coache) * 0.8f;
	}

	public float getMasseMaigre(Coache coache) {
		if ((coache.getAge() >= 18) && (coache.getAge() <= 80) && (coache.getPoids() >= 35)
				&& (coache.getPoids() <= 130) && (coache.getTaille() >= 140) && (coache.getTaille() <= 185))
			if (coache.getSexe() == 'M')
				return (float) (1.10 * coache.getPoids()
						- 128 * Math.pow(coache.getPoids(), 2) / Math.pow(coache.getTaille(), 2));
			else
				return (float) (1.07 * coache.getPoids()
						- 148 * Math.pow(coache.getPoids(), 2) / Math.pow(coache.getTaille(), 2));
		else
			return -1;
	}

	public float getMasseHydrique(Coache coache) {
		float resultatPourcentagePoids;
		if (coache.getSexe() == 'M')
			resultatPourcentagePoids = coache.getPoids() * 60 / 100f;
		else
			resultatPourcentagePoids = coache.getPoids() * 55 / 100f;
		float resultatWatson;
		if (coache.getSexe() == 'M')
			resultatWatson = (float) (2.447 + 0.3362 * coache.getPoids() + 0.1074 * coache.getTaille()
					- 0.09156 * coache.getAge());
		else
			resultatWatson = (float) (-2.097 + 0.2466 * coache.getPoids() + 0.1069 * coache.getTaille());
		return (resultatPourcentagePoids + resultatWatson) / 2;
	}

	public int getFrequenceCardiaqueMax(Coache coache) {
		return 220 - coache.getAge();
	}

	public float getIndiceRuffier(Fiche fiche) {
		return (fiche.getPouls1() + fiche.getPouls2() + fiche.getPouls3() - 200) / 10f;
	}

	public String getInterpretationIndiceRuffier(Fiche fiche) {
		if (getIndiceRuffier(fiche) < 0)
			return "Vous avez une très bonne adaptation à l'effort !";
		else if ((getIndiceRuffier(fiche) >= 0) && (getIndiceRuffier(fiche) < 5))
			return "Vous avez une bonne adaptation à l'effort !";
		else if ((getIndiceRuffier(fiche) >= 5) && (getIndiceRuffier(fiche) < 10))
			return "Vous avez une adaptation moyenne à l'effort !";
		else if ((getIndiceRuffier(fiche) >= 10) && (getIndiceRuffier(fiche) < 15))
			return "Vous avez une adaptation insuffisante à l'effort !";
		else
			return "Vous avez une mauvaise adaptation à l'effort ! (bilan complémentaire nécessaire)";
	}

	public float getIndiceDickson(Fiche fiche) {
		return ((fiche.getPouls2() - 70) + 2 * (fiche.getPouls3() - fiche.getPouls1())) / 10f;
	}

	public String getInterpretationIndiceDickson(Fiche fiche) {
		if (getIndiceDickson(fiche) < 0)
			return "Excellent !";
		else if ((getIndiceDickson(fiche) >= 0) && (getIndiceDickson(fiche) < 2))
			return "Très bon !";
		else if ((getIndiceDickson(fiche) >= 2) && (getIndiceDickson(fiche) < 4))
			return "Bon !";
		else if ((getIndiceDickson(fiche) >= 4) && (getIndiceDickson(fiche) < 6))
			return "Moyen !";
		else if ((getIndiceDickson(fiche) >= 6) && (getIndiceDickson(fiche) < 8))
			return "Faible !";
		else if ((getIndiceDickson(fiche) >= 8) && (getIndiceDickson(fiche) < 10))
			return "Très faible !";
		else
			return "Mauvaise adaptation !";
	}

}