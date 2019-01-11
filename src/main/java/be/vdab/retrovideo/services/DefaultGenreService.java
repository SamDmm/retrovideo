package be.vdab.retrovideo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import be.vdab.retrovideo.entities.Genre;
import be.vdab.retrovideo.repositories.GenreRepository;

@Service
 class DefaultGenreService implements GenreService {
	private final GenreRepository repository;
	
	 DefaultGenreService(GenreRepository repository) {
		this.repository = repository;
	}
	@Override
	public List<Genre> findAll() {
		return repository.findAll();
	}
	
}
