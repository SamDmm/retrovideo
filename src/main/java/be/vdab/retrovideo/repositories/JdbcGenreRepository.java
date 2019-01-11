package be.vdab.retrovideo.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Genre;

@Repository
 class JdbcGenreRepository implements GenreRepository {
	private final JdbcTemplate template;
	
	 JdbcGenreRepository(JdbcTemplate template) {
		this.template = template;
	}
	private static final String SELECT_ALL_GENRES = "select id, naam from genres order by naam";
	private final RowMapper<Genre> genreRowMapper = (resultSet, rowNum) -> new Genre(resultSet.getLong("id"), resultSet.getString("naam"));
	@Override
	public List<Genre> findAll() {
		return template.query(SELECT_ALL_GENRES, genreRowMapper);
	}
	
}
