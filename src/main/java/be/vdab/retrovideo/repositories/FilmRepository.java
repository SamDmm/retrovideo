package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Film;

public interface FilmRepository {
	List<Film> findByGenre(long id);
	Optional<Film> read(long id);
//	void update(Film film);
	void updateFilmVoorraad(long id);
}
