package biblio.ihmswing;

public class Livre {

	private int id;
	private String titre;
	private int anneePublication;
	private Auteur auteur;

	// Constructeur
	public Livre(int id, String titre, int anneePublication, Auteur auteur) {
		this.id = id;
		this.titre = titre;
		this.anneePublication = anneePublication;
		this.auteur = auteur;
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public int getAnneePublication() {
		return anneePublication;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	// Setters
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setAnneePublication(int anneePublication) {
		this.anneePublication = anneePublication;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	// Methodes
	public void afficherDetails() {
		System.out.println("Titre : " + this.titre + " | Paru en " + this.anneePublication + " | Écrit par " + this.getAuteur().getPrenom() + " " + this.getAuteur().getNom());
	}
}
