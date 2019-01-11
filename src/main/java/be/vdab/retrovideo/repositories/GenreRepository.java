package be.vdab.retrovideo.repositories;

import java.util.List;

import be.vdab.retrovideo.entities.Genre;

public interface GenreRepository {
	List<Genre> findAll();
}
