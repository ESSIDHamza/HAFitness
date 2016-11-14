package application.entites;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class DonneesMail implements Serializable {
	private String destinataire;
	private String sujet;
	private String contenu;
	private String fiche;

	public DonneesMail() {
		super();
	}

	public DonneesMail(String destinataire, String sujet, String contenu, String fiche) {
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
}