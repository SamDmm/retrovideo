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

import be.vdab.retrovideo.entities.Klant;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcKlantenRepository.class)
@Sql("/insertKlant.sql")
public class JdbcKlantenRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	JdbcKlantenRepository repository;
	@Test
	public void findByFamilieNaamDeel() {
		List<Klant> klanten = repository.findByFamilieNaamDeel("e");
		String vorigeFamilienaam = "";
		for (Klant klant : klanten) {
			assertTrue(klant.getFamilienaam().contains("e"));
			assertTrue(klant.getFamilienaam().compareToIgnoreCase(vorigeFamilienaam) >= 0);
			vorigeFamilienaam = klant.getFamilienaam();
		}
		long aantalKlanten = super.jdbcTemplate.queryForObject("select count(*) from klanten where familienaam like '%e%'", long.class);
		assertEquals(aantalKlanten, klanten.size());
	}
	private long idVanTestKlant() {
		return super.jdbcTemplate.queryForObject("select id from klanten where familienaam = 'test'", long.class);
	}
	@Test
	public void read() {
		assertEquals("test", repository.read(idVanTestKlant()).get().getFamilienaam());
	}
}
