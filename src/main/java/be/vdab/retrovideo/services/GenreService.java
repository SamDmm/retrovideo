package be.vdab.retrovideo.services;

import java.util.List;

import be.vdab.retrovideo.entities.Genre;

public interface GenreService {
	List<Genre> findAll();
}
