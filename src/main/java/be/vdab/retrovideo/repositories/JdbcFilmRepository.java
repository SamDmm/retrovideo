package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.exceptions.ReservatieNietGeluktException;

@Repository
 class JdbcFilmRepository implements FilmRepository {
	private final JdbcTemplate template;
	
	 JdbcFilmRepository(JdbcTemplate template) {
		this.template = template;
	}
	
	private static final String SELECT_FILMS_PER_GENRE = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where genreId = ? order by titel";
	private final RowMapper<Film> filmRowMapper = (resultSet, rowNum) -> new Film(resultSet.getLong("id"), resultSet.getLong("genreid"), resultSet.getString("titel"), resultSet.getInt("voorraad"), resultSet.getInt("gereserveerd"), resultSet.getBigDecimal("prijs"));
	@Override
	public List<Film> findByGenre(long id) {
		return template.query(SELECT_FILMS_PER_GENRE, filmRowMapper, id);
	}
	private static final String READ = "select id, genreid, titel, voorraad, gereserveerd, prijs from films where id = ?";
	@Override
	public Optional<Film> read(long id) {
		try {
			return Optional.of(template.queryForObject(READ, filmRowMapper, id));
		} catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}
//	private static final String UPDATE_FILM = "update films set genreid=?, titel=?, voorraad=?, gereserveerd=?, prijs=? where id=?";
//	@Override
//	public void update(Film film) {
//		if (template.update(UPDATE_FILM, film.getGenreId(), film.getTitel(), film.getVoorraad(), film.getGereserveerd(), film.getPrijs(), film.getId()) == 0) {
//			throw new FilmNietGevondenException(String.valueOf(film.getId()));
//		}
//	}
	private static final String UPDATE_FILM_VOORRAAD = "update films set gereserveerd=(gereserveerd+1) where id=? and gereserveerd<voorraad";
	@Override
	public void updateFilmVoorraad(long filmId) {
		if ((template.update(UPDATE_FILM_VOORRAAD, filmId)) == 0) {
			throw new ReservatieNietGeluktException(filmId);
		}
	}
}
