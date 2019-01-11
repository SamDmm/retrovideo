package be.vdab.retrovideo.exceptions;

public class ReservatieNietGeluktException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final long filmId;
	
	public ReservatieNietGeluktException(long filmId) {
		super("Reservatie mislukt voor film " + filmId);
		this.filmId = filmId;
	}

	public long getFilmId() {
		return filmId;
	}
}
