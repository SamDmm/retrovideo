package be.vdab.retrovideo.valueobjects;

import java.time.LocalDateTime;

public class Reservatie {
	private long klantId;
	private long filmId;
	private LocalDateTime reservatie;
	
	public Reservatie(long klantId, long filmId, LocalDateTime reservatie) {
		this.klantId = klantId;
		this.filmId = filmId;
		this.reservatie = reservatie;
	}
	public Reservatie() {
	}
	
	public long getKlantId() {
		return klantId;
	}
	public long getFilmId() {
		return filmId;
	}
	public LocalDateTime getReservatie() {
		return reservatie;
	}
}
