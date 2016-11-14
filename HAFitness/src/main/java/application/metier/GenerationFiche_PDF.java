package application.metier;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import application.entites.Coache;
import application.entites.DonneesTPMA;

@Service
public class GenerationFiche_PDF {
	private Document document;
	private Coache coache;

	public static final String CHEMIN_FICHE = "C:\\HAFitness\\fiches";
	public static final String CHEMIN_LOGO = "C:\\HAFitness\\img\\HAFitness.png";
	public static final String CHEMIN_IMAGE_PIEDS_PAGE = "C:\\HAFitness\\img\\contacts.png";

	public GenerationFiche_PDF() {
		super();
	}

	public GenerationFiche_PDF(Coache coache) {
		super();
		this.coache = coache;
	}

	public void genererEntete() throws Exception {
		document = new Document(PageSize.A4.rotate());
		File file = new File(CHEMIN_FICHE);
		if (!file.exists())
			file.mkdirs();
		PdfWriter.getInstance(document, new FileOutputStream(CHEMIN_FICHE + "\\" + coache.getId() + ".pdf"));
		document.open();
		Paragraph entete = new Paragraph();
		Image logo = Image.getInstance(CHEMIN_LOGO);
		String texteTitre = "Fiche du suivi du coaché";
		if (coache.getSexe() == 'F')
			texteTitre += "e";
		Paragraph titre = new Paragraph(texteTitre + " " + coache.getNom() + " " + coache.getPrenom());
		titre.setAlignment(Element.ALIGN_CENTER);
		entete.add(logo);
		entete.add(titre);
		document.add(entete);
		document.add(new Paragraph("\n"));
	}

	public void genererTableauMesuresAnthropometriques() throws Exception {
		PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1, 1 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell("Tour du biceps (cm)");
		table.addCell("Tour des pectoraux (cm)");
		table.addCell("Tour de taille (cm)");
		table.addCell("Tour de hanches (cm)");
		table.addCell("Tour du mollet (cm)");
		table.setHeaderRows(1);
		PdfPCell[] cells = table.getRow(0).getCells();
		for (int i = 0; i < cells.length; i++)
			cells[i].setBackgroundColor(BaseColor.GRAY);
		table.addCell(coache.getTourBiceps() + "");
		table.addCell(coache.getTourPectoraux() + "");
		table.addCell(coache.getTourTaille() + "");
		table.addCell(coache.getTourHanches() + "");
		table.addCell(coache.getTourMollet() + "");
		document.add(table);
		document.add(new Paragraph("\n"));
	}

	public void genererTableau1RM() throws Exception {
		PdfPTable table = new PdfPTable(new float[] { 3, 1, 1, 1, 1 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell("Ateliers");
		table.addCell("Barre (Kg)");
		table.addCell("Charge (Kg)");
		table.addCell("N° Répétition");
		table.addCell("1 RM (Kg)");
		table.setHeaderRows(1);
		PdfPCell[] cells = table.getRow(0).getCells();
		for (int i = 0; i < cells.length; i++)
			cells[i].setBackgroundColor(BaseColor.GRAY);
		String[] ateliers = new String[] { "1/ Développé assis barre clavicule", "2/ Développé assis barre nuque",
				"3/ Curl au banc Larry Scott avec barre coudée en supination",
				"4/ Extension debout à la poulie haute en pronation", "5/ Soulevé de terre prise en pronation",
				"6/ Développé couché horizontal avec la barre", "7/ Extension des jambes à la machine (leg extension)",
				"8/ Ischio-jambiers (leg curl)", "9/ Tirage devant large prise en pronation",
				"10/ Tirage nuque prise en pronation", "11/ Squat avec barre guidée",
				"12/ Extension debout avec barre guidée", "13/ 30\" Abdominaux" };
		for (int i = 0; i < coache.getFiche().getTestForce1RMBarre().size(); i++) {
			table.addCell(ateliers[i]);
			table.addCell(coache.getFiche().getTestForce1RMBarre().get(i) + "");
			table.addCell(coache.getFiche().getTestForce1RMCharge().get(i) + "");
			table.addCell(coache.getFiche().getTestForce1RMNbrRepetitions().get(i) + "");
			float max1RM = (coache.getFiche().getTestForce1RMBarre().get(i)
					+ coache.getFiche().getTestForce1RMCharge().get(i))
					/ ((100 - coache.getFiche().getTestForce1RMNbrRepetitions().get(i) * 2) / 100f);
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			table.addCell(decimalFormat.format(max1RM));
		}
		document.add(table);
		document.add(new Paragraph("\n"));
	}

	public void genererTableauTPMA() throws Exception {
		PdfPTable table = new PdfPTable(new float[] { 1, 1, 1, 1, 1 });
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell("Palier");
		table.addCell("Vitesse (Km/h)");
		table.addCell("Durée");
		table.addCell("VO_2 max (ml/min)");
		table.addCell("FC");
		table.setHeaderRows(1);
		PdfPCell[] cells = table.getRow(0).getCells();
		for (int i = 0; i < cells.length; i++)
			cells[i].setBackgroundColor(BaseColor.GRAY);
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
			donneesTPMAs.get(i).setFc(coache.getFiche().getTestProgressifMaximalSurTapisRoulant().get(i));
		for (int i = 0; i < coache.getFiche().getTestProgressifMaximalSurTapisRoulant().size(); i++) {
			table.addCell(donneesTPMAs.get(i).getPalier());
			table.addCell(donneesTPMAs.get(i).getVitesse() + "");
			table.addCell(donneesTPMAs.get(i).getDuree() + "");
			table.addCell(donneesTPMAs.get(i).getVo2() + "");
			table.addCell(donneesTPMAs.get(i).getFc() + "");
		}
		document.add(table);
		document.add(new Paragraph("\n"));
	}

	public void genererPiedsPage() throws Exception {
		Paragraph piedsPage = new Paragraph();
		Image imgPiedsPage = Image.getInstance(CHEMIN_IMAGE_PIEDS_PAGE);
		imgPiedsPage.setAlignment(Element.ALIGN_CENTER);
		piedsPage.add(imgPiedsPage);
		document.add(piedsPage);
		document.close();
	}

	public void setCoache(Coache coache) {
		this.coache = coache;
	}
}