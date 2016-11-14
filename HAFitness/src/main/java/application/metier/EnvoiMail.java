package application.metier;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EnvoiMail {
	private String destinataire;
	private String sujet;
	private String contenu;
	private String fiche;

	public static final String EMETTEUR = "hafitness55@gmail.com";
	private static final String MDP_Emetteur = "***** **** *** ** * ** *** **** *****";

	public EnvoiMail() {
		super();
	}

	public EnvoiMail(String destinataire, String sujet, String contenu, String fiche) {
		super();
		this.destinataire = destinataire;
		this.sujet = sujet;
		this.contenu = contenu;
		this.fiche = fiche;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getFiche() {
		return fiche;
	}

	public void setFiche(String fiche) {
		this.fiche = fiche;
	}

	public void envoyer() throws Exception {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", 465);

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMETTEUR, MDP_Emetteur);
			}
		});

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(EMETTEUR));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinataire));
		message.setSubject(sujet);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(contenu);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		messageBodyPart = new MimeBodyPart();
		String filename = GenerationFiche_PDF.CHEMIN_FICHE + "\\" + fiche + ".pdf";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(fiche + ".pdf");
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		Transport.send(message);
	}
}