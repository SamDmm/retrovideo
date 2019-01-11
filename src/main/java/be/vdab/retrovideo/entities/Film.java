package be.vdab.retrovideo.entities;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

import be.vdab.retrovideo.exceptions.ReservatieNietGeluktException;

public class Film {
	private long id;
	private long genreId;
	private String titel;
	private int voorraad;
	private int gereserveerd;
	@NumberFormat(pattern = "0.00")
	private BigDecimal prijs;
	
	public Film(long id, long genreId, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
		this.id = id;
		this.genreId = genreId;
		this.titel = titel;
		this.voorraad = voorraad;
		this.gereserveerd = gereserveerd;
		this.prijs = prijs;
	}
	public Film() {
	}

	public long getId() {
		return id;
	}
	public long getGenreId() {
		return genreId;
	}
	public String getTitel() {
		return titel;
	}
	public int getVoorraad() {
		return voorraad;
	}
	public int getGereserveerd() {
		return gereserveerd;
	}
	/*
	public void setGereserveerd(int gereserveerd) {
		this.gereserveerd = gereserveerd;
	}*/
	
	public void reserveer() {
		if (gereserveerd < voorraad) {
			gereserveerd++;
		} else {
			throw new ReservatieNietGeluktException(id);
		}
	}
	
	
	public BigDecimal getPrijs() {
		return prijs;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Film)) {
			return false;
		}
		Film film = (Film) o;
		return this.getId() == film.getId();
	}
	@Override
	public int hashCode() {
		return (int) id;
	}
}
