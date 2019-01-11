package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.retrovideo.entities.Film;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcFilmRepository.class)
@Sql("/insertFilm.sql")
public class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	JdbcFilmRepository repository;
	@Test
	public void findByGenre() {
		List<Film> films = repository.findByGenre(1L);
		String vorigeTitel = "";
		for (Film film : films) {
			assertEquals(1, film.getGenreId());
			assertTrue(vorigeTitel.compareToIgnoreCase(film.getTitel()) <= 0);
			vorigeTitel = film.getTitel();
		}
		assertEquals(countRowsInTableWhere("films", "genreid=1"), films.size());
	}
	private long idVanTestFilm() {
		return super.jdbcTemplate.queryForObject("select id from films where titel = 'test'", long.class);
	}
	@Test
	public void read() {
		assertEquals("test", repository.read(idVanTestFilm()).get().getTitel());
	}
//	@Test
//	public void update() {
//		long idVanTestFilm = idVanTestFilm();
//		Film film = new Film(idVanTestFilm, 1, "test", 10, 2, BigDecimal.TEN);
//		repository.update(film);
//		assertTrue(2 == super.jdbcTemplate.queryForObject("select gereserveerd from films where id=?;", long.class, idVanTestFilm));
//	}
	private long voorraadVanTestFilm() {
		return super.jdbcTemplate.queryForObject("select voorraad from films where titel = 'test'", long.class);
	}
	@Test
	public void updateFilmVoorraad() {
		long idVanTestFilm = idVanTestFilm();
		long voorraadVanTestFilm = voorraadVanTestFilm();
		repository.updateFilmVoorraad(idVanTestFilm);
		assertTrue(voorraadVanTestFilm + 1 == super.jdbcTemplate.queryForObject("select voorraad from films where titel = 'test'", long.class));
	}
}
