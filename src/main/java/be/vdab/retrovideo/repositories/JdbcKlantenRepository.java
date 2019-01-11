package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.entities.Klant;

@Repository
 class JdbcKlantenRepository implements KlantenRepository {
	JdbcTemplate template;

	 JdbcKlantenRepository(JdbcTemplate template) {
		this.template = template;
	}
	
	private final RowMapper<Klant> klantenRowMapper = (resultSet, rowNum) -> new Klant(resultSet.getLong("id"), resultSet.getString("familienaam"), resultSet.getString("voornaam"), resultSet.getString("straatNummer"), resultSet.getString("postcode"), resultSet.getString("gemeente"));
	private static final String SELECT_BY_NAAM_DEEL = "select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten where familienaam like ? order by familienaam";
	@Override
	public List<Klant> findByFamilieNaamDeel(String familieNaamDeel) {
		return template.query(SELECT_BY_NAAM_DEEL, klantenRowMapper, "%"+familieNaamDeel+"%");
	}
	private static final String READ = "select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten where id = ?";
	@Override
	public Optional<Klant> read(long id) {
		try {
			return Optional.of(template.queryForObject(READ, klantenRowMapper, id));
		} catch (IncorrectResultSizeDataAccessException ex) {
			return Optional.empty();
		}
	}
}
