package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.repositories.FilmRepository;

@Service
class DefaultFilmService implements FilmService {
	private final FilmRepository repository;
	
	public DefaultFilmService(FilmRepository repository) {
		this.repository = repository;
	}
	@Override
	public List<Film> findByGenre(long id) {
		return repository.findByGenre(id);
	}
	@Override
	public Optional<Film> read(long id) {
		return repository.read(id);
	}
}
