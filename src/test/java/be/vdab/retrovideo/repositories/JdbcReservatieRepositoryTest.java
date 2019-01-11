package be.vdab.retrovideo.repositories;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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

import be.vdab.retrovideo.valueobjects.Reservatie;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertKlant.sql")
@Sql("/insertFilm.sql")
@Sql("/insertReservatie.sql")
@Import(JdbcReservatieRepository.class)
public class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	JdbcReservatieRepository repository;
	@Test
	public void create() {
		int aantalRecords = super.countRowsInTable("reservaties");
		Reservatie reservatie = new Reservatie(1, 1, LocalDateTime.now());
		repository.create(reservatie);
		assertEquals(aantalRecords + 1, super.countRowsInTable("reservaties"));
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");		 
		//assertEquals(1, super.countRowsInTableWhere("reservaties", "reservatie='" + reservatie.getReservatie().format(formatter)+"'"));
	}

}
