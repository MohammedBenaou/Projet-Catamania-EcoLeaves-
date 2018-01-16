package serverBay.beans;

public class Echange {
    /* Propriétés du bean */
    private Utilisateur utilisateur;
    private String intitule;
    private String categorie;
    private String descriptif;
    
	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}
	
	/**
	 * @param intitule the intitule to set
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}
	
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	/**
	 * @return the descriptif
	 */
	public String getDescriptif() {
		return descriptif;
	}
	
	/**
	 * @param descriptif the descriptif to set
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
}
