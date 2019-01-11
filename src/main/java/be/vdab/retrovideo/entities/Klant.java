package be.vdab.retrovideo.entities;

public class Klant {
	private long id;
	private String familienaam;
	private String voornaam;
	private String straatnummer;
	private String postcode;
	private String gemeente;
	
	
	public Klant(long id, String familienaam, String voornaam, String straatnummer, String postcode, String gemeente) {
		this.id = id;
		this.familienaam = familienaam;
		this.voornaam = voornaam;
		this.straatnummer = straatnummer;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}

	public Klant() {
	}
	
	public long getId() {
		return id;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public String getStraatnummer() {
		return straatnummer;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getGemeente() {
		return gemeente;
	}
}
