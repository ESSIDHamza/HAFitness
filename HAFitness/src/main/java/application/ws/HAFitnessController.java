package application.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import application.dao.GestionCoacheRepository;
import application.dao.GestionFicheRepository;
import application.entites.Coache;
import application.entites.Donnees1RM;
import application.entites.DonneesCalculs;
import application.entites.DonneesMail;
import application.entites.DonneesTPMA;
import application.entites.Fiche;
import application.entites.ResultatsCalculs;
import application.metier.EnvoiMail;
import application.metier.GenerationFiche_PDF;
import application.metier.HAFitnessUtils;

@RestController
public class HAFitnessController {
	@Autowired
	private GestionCoacheRepository gestionCoacheRepository;
	@Autowired
	private GestionFicheRepository gestionFicheRepository;
	@Autowired
	private HAFitnessUtils haFitnessUtils;
	@Autowired
	private GenerationFiche_PDF generationFiche_PDF;
	@Autowired
	private EnvoiMail envoiMail;

	@RequestMapping(value = "/api/coaches", method = RequestMethod.POST)
	public void creerCoache(@RequestBody Coache coache) {
		coache.setFiche(new Fiche());
		gestionCoacheRepository.save(coache);
	}

	@RequestMapping(value = "/api/coaches/{idCoache}", method = RequestMethod.GET)
	public ResponseEntity<Coache> getCoache(@PathVariable("idCoache") int idCoache) {
		return new ResponseEntity<Coache>(gestionCoacheRepository.findOne(idCoache), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coaches/{idCoache}", method = RequestMethod.PUT)
	public ResponseEntity<Coache> modifierCoache(@PathVariable("idCoache") int idCoache, @RequestBody Coache coache) {
		coache.setId(idCoache);
		coache.setFiche(gestionCoacheRepository.findOne(idCoache).getFiche());
		gestionCoacheRepository.save(coache);
		return new ResponseEntity<Coache>(coache, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coaches", method = RequestMethod.GET)
	public ResponseEntity<List<Coache>> getAllCoaches() {
		return new ResponseEntity<List<Coache>>(gestionCoacheRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/1rm", method = RequestMethod.GET)
	public ResponseEntity<List<Donnees1RM>> getFiche(@PathVariable("idCoache") int idCoache) {
		Coache coache = gestionCoacheRepository.findOne(idCoache);
		Fiche fiche = coache.getFiche();
		List<Donnees1RM> donnees1rms = new ArrayList<Donnees1RM>(13);
		donnees1rms.add(new Donnees1RM("1/ Développé assis barre clavicule", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("2/ Développé assis barre nuque", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("3/ Curl au banc Larry Scott avec barre coudée en supination", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("4/ Extension debout à la poulie haute en pronation", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("5/ Soulevé de terre prise en pronation", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("6/ Développé couché horizontal avec la barre", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("7/ Extension des jambes à la machine (leg extension)", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("8/ Ischio-jambiers (leg curl)", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("9/ Tirage devant large prise en pronation", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("10/ Tirage nuque prise en pronation", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("11/ Squat avec barre guidée", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("12/ Extension debout avec barre guidée", 0, 0, 0));
		donnees1rms.add(new Donnees1RM("13/ 30\" Abdominaux", 0, 0, 0));
		for (int i = 0; i < 13; i++) {
			donnees1rms.get(i).setBarre(fiche.getTestForce1RMBarre().get(i));
			donnees1rms.get(i).setCharge(fiche.getTestForce1RMCharge().get(i));
			donnees1rms.get(i).setNbrRepetitions(fiche.getTestForce1RMNbrRepetitions().get(i));
		}
		return new ResponseEntity<List<Donnees1RM>>(donnees1rms, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/1rm", method = RequestMethod.PUT)
	public void modifierFiche(@PathVariable("idCoache") int idCoache, @RequestBody List<Donnees1RM> donnees1rms) {
		Coache coache = gestionCoacheRepository.findOne(idCoache);
		Fiche fiche = coache.getFiche();
		fiche.getTestForce1RMBarre().removeAll(fiche.getTestForce1RMBarre());
		fiche.getTestForce1RMCharge().removeAll(fiche.getTestForce1RMCharge());
		fiche.getTestForce1RMNbrRepetitions().removeAll(fiche.getTestForce1RMNbrRepetitions());
		for (Donnees1RM donnees1rm : donnees1rms) {
			fiche.getTestForce1RMBarre().add(donnees1rm.getBarre());
			fiche.getTestForce1RMCharge().add(donnees1rm.getCharge());
			fiche.getTestForce1RMNbrRepetitions().add(donnees1rm.getNbrRepetitions());
		}
		gestionFicheRepository.save(fiche);
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/tpma", method = RequestMethod.GET)
	public ResponseEntity<List<DonneesTPMA>> getFiche2(@PathVariable("idCoache") int idCoache) {
		Coache coache = gestionCoacheRepository.findOne(idCoache);
		Fiche fiche = coache.getFiche();
		List<DonneesTPMA> donneesTPMAs = new ArrayList<DonneesTPMA>(36);
		donneesTPMAs.add(new DonneesTPMA("Echauff...", 5, 5, 17.55f, 0));
		donneesTPMAs.add(new DonneesTPMA("1", 6.5f, 30, 22.82f, 0));
		donneesTPMAs.add(new DonneesTPMA("2", 6.8f, 30, 23.87f, 0));
		donneesTPMAs.add(new DonneesTPMA("3", 7.1f, 30, 24.92f, 0));
		donneesTPMAs.add(new DonneesTPMA("4", 7.4f, 30, 25.97f, 0));
		donneesTPMAs.add(new DonneesTPMA("5", 7.7f, 30, 27.03f, 0));
		donneesTPMAs.add(new DonneesTPMA("6", 8, 30, 28.08f, 0));
		donneesTPMAs.add(new DonneesTPMA("7", 8.3f, 30, 29.13f, 0));
		donneesTPMAs.add(new DonneesTPMA("8", 8.6f, 30, 30.19f, 0));
		donneesTPMAs.add(new DonneesTPMA("9", 8.9f, 30, 31.24f, 0));
		donneesTPMAs.add(new DonneesTPMA("10", 9.2f, 30, 32.29f, 0));
		donneesTPMAs.add(new DonneesTPMA("11", 9.5f, 30, 33.35f, 0));
		donneesTPMAs.add(new DonneesTPMA("12", 9.8f, 30, 34.4f, 0));
		donneesTPMAs.add(new DonneesTPMA("13", 10.1f, 30, 35.45f, 0));
		donneesTPMAs.add(new DonneesTPMA("14", 10.4f, 30, 36.5f, 0));
		donneesTPMAs.add(new DonneesTPMA("15", 10.7f, 30, 37.56f, 0));
		donneesTPMAs.add(new DonneesTPMA("16", 11, 30, 38.61f, 0));
		donneesTPMAs.add(new DonneesTPMA("17", 11.3f, 30, 39.66f, 0));
		donneesTPMAs.add(new DonneesTPMA("18", 11.66f, 30, 40.72f, 0));
		donneesTPMAs.add(new DonneesTPMA("19", 11.9f, 30, 41.77f, 0));
		donneesTPMAs.add(new DonneesTPMA("20", 12.2f, 30, 42.82f, 0));
		donneesTPMAs.add(new DonneesTPMA("21", 12.5f, 30, 43.88f, 0));
		donneesTPMAs.add(new DonneesTPMA("22", 12.8f, 30, 44.93f, 0));
		donneesTPMAs.add(new DonneesTPMA("23", 13.1f, 30, 45.98f, 0));
		donneesTPMAs.add(new DonneesTPMA("24", 13.4f, 30, 47.03f, 0));
		donneesTPMAs.add(new DonneesTPMA("25", 13.7f, 30, 48.09f, 0));
		donneesTPMAs.add(new DonneesTPMA("26", 14, 30, 49.14f, 0));
		donneesTPMAs.add(new DonneesTPMA("27", 14.3f, 30, 50.19f, 0));
		donneesTPMAs.add(new DonneesTPMA("28", 14.6f, 30, 51.25f, 0));
		donneesTPMAs.add(new DonneesTPMA("29", 14.9f, 30, 52.3f, 0));
		donneesTPMAs.add(new DonneesTPMA("30", 15.2f, 30, 53.35f, 0));
		donneesTPMAs.add(new DonneesTPMA("31", 15.5f, 30, 54.41f, 0));
		donneesTPMAs.add(new DonneesTPMA("32", 15.8f, 30, 57.56f, 0));
		donneesTPMAs.add(new DonneesTPMA("33", 16.1f, 30, 58.62f, 0));
		donneesTPMAs.add(new DonneesTPMA("34", 16.4f, 30, 59.67f, 0));
		donneesTPMAs.add(new DonneesTPMA("35", 16.7f, 30, 60.72f, 0));
		for (int i = 0; i < 36; i++)
			donneesTPMAs.get(i).setFc(fiche.getTestProgressifMaximalSurTapisRoulant().get(i));
		return new ResponseEntity<List<DonneesTPMA>>(donneesTPMAs, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/tpma", method = RequestMethod.PUT)
	public void modifierFiche2(@PathVariable("idCoache") int idCoache, @RequestBody List<DonneesTPMA> donneesTPMAs) {
		Coache coache = gestionCoacheRepository.findOne(idCoache);
		Fiche fiche = coache.getFiche();
		fiche.getTestProgressifMaximalSurTapisRoulant().removeAll(fiche.getTestProgressifMaximalSurTapisRoulant());
		for (DonneesTPMA donneesTPMA : donneesTPMAs)
			fiche.getTestProgressifMaximalSurTapisRoulant().add(donneesTPMA.getFc());
		gestionFicheRepository.save(fiche);
	}

	@RequestMapping(value = "/api/calculs", method = RequestMethod.POST)
	public ResponseEntity<ResultatsCalculs> calculer(@RequestBody DonneesCalculs donneesCalculs) {
		Coache coache = new Coache("", "", donneesCalculs.getAge(), donneesCalculs.getSexe(),
				donneesCalculs.getTaille(), donneesCalculs.getPoids(), "", "", donneesCalculs.getDisponibilite(), "",
				null, "", "", 0, 0, 0, 0, 0);
		Fiche fiche = new Fiche(new ArrayList<Float>(), new ArrayList<Float>(), new ArrayList<Integer>(),
				new ArrayList<Integer>(), donneesCalculs.getPouls1(), donneesCalculs.getPouls2(),
				donneesCalculs.getPouls3());
		return new ResponseEntity<ResultatsCalculs>(
				new ResultatsCalculs(haFitnessUtils.getPoidsIdeal(coache), haFitnessUtils.getBMI(coache),
						haFitnessUtils.getInterpretationBMI(coache), haFitnessUtils.getMB(coache),
						haFitnessUtils.getMG(coache), haFitnessUtils.getMasseMaigre(coache),
						haFitnessUtils.getMasseHydrique(coache), haFitnessUtils.getFrequenceCardiaqueMax(coache),
						haFitnessUtils.getIndiceRuffier(fiche), haFitnessUtils.getInterpretationIndiceRuffier(fiche),
						haFitnessUtils.getIndiceDickson(fiche), haFitnessUtils.getInterpretationIndiceDickson(fiche)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/all", method = RequestMethod.GET)
	public void genererFicheAll(@PathVariable("idCoache") int idCoache) throws Exception {
		generationFiche_PDF.setCoache(gestionCoacheRepository.findOne(idCoache));
		generationFiche_PDF.genererEntete();
		generationFiche_PDF.genererTableauMesuresAnthropometriques();
		generationFiche_PDF.genererTableau1RM();
		generationFiche_PDF.genererTableauTPMA();
		generationFiche_PDF.genererPiedsPage();
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/suivi_1rm", method = RequestMethod.GET)
	public void genererFiche1RM(@PathVariable("idCoache") int idCoache) throws Exception {
		generationFiche_PDF.setCoache(gestionCoacheRepository.findOne(idCoache));
		generationFiche_PDF.genererEntete();
		generationFiche_PDF.genererTableauMesuresAnthropometriques();
		generationFiche_PDF.genererTableau1RM();
		generationFiche_PDF.genererPiedsPage();
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/suivi_tpma", method = RequestMethod.GET)
	public void genererFicheTPMA(@PathVariable("idCoache") int idCoache) throws Exception {
		generationFiche_PDF.setCoache(gestionCoacheRepository.findOne(idCoache));
		generationFiche_PDF.genererEntete();
		generationFiche_PDF.genererTableauMesuresAnthropometriques();
		generationFiche_PDF.genererTableauTPMA();
		generationFiche_PDF.genererPiedsPage();
	}

	@RequestMapping(value = "/api/coaches/{idCoache}/fiche/min", method = RequestMethod.GET)
	public void genererFicheMin(@PathVariable("idCoache") int idCoache) throws Exception {
		generationFiche_PDF.setCoache(gestionCoacheRepository.findOne(idCoache));
		generationFiche_PDF.genererEntete();
		generationFiche_PDF.genererTableauMesuresAnthropometriques();
		generationFiche_PDF.genererPiedsPage();
	}

	@RequestMapping(value = "/api/coaches/mail", method = RequestMethod.POST)
	public void envoyerMail(@RequestBody DonneesMail donneesMail) throws Exception {
		envoiMail.setDestinataire(donneesMail.getDestinataire());
		envoiMail.setSujet(donneesMail.getSujet());
		envoiMail.setContenu(donneesMail.getContenu());
		envoiMail.setFiche(donneesMail.getFiche());
		envoiMail.envoyer();
	}

	@RequestMapping(value = "/api/coaches/{idCoache}", method = RequestMethod.DELETE)
	public void supprimerCoache(@PathVariable("idCoache") int idCoache) {
		gestionCoacheRepository.delete(gestionCoacheRepository.findOne(idCoache));
	}

	public void setGestionCoacheRepository(GestionCoacheRepository gestionCoacheRepository) {
		this.gestionCoacheRepository = gestionCoacheRepository;
	}

	public void setGestionFicheRepository(GestionFicheRepository gestionFicheRepository) {
		this.gestionFicheRepository = gestionFicheRepository;
	}

	public void setHaFitnessUtils(HAFitnessUtils haFitnessUtils) {
		this.haFitnessUtils = haFitnessUtils;
	}

	public void setGenerationFiche_PDF(GenerationFiche_PDF generationFiche_PDF) {
		this.generationFiche_PDF = generationFiche_PDF;
	}

	public void setEnvoiMail(EnvoiMail envoiMail) {
		this.envoiMail = envoiMail;
	}
}