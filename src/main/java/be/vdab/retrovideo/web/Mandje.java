package be.vdab.retrovideo.web;

import java.util.Set;

interface Mandje {
	void addFilmId(long filmId);
	Set<Long> getFilmIds();
	void deleteFilmId(long filmId);
	void maakLeeg();
}
