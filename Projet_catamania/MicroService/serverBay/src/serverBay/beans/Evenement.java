package serverBay.beans;

public class Evenement {
    /* Propriétés du bean */
    private Utilisateur utilisateur;
    private String intitule;
    private String descriptif;
    private String lieu;
    private String horaire;
    
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
	
	/**
	 * @return the lieu
	 */
	public String getLieu() {
		return lieu;
	}
	
	/**
	 * @param lieu the lieu to set
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	/**
	 * @return the horaire
	 */
	public String getHoraire() {
		return horaire;
	}
	
	/**
	 * @param horaire the horaire to set
	 */
	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}
}