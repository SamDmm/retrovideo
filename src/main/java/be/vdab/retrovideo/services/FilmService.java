package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Film;

public interface FilmService {
	List<Film> findByGenre(long id);
	Optional<Film> read(long id);
}
