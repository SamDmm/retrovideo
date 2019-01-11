package be.vdab.retrovideo.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.retrovideo.exceptions.ReservatieNietGeluktException;
import be.vdab.retrovideo.valueobjects.Reservatie;

@Repository
 class JdbcReservatieRepository implements ReservatieRepository {
	private final SimpleJdbcInsert insert;
	
	 JdbcReservatieRepository(JdbcTemplate template) {
		this.insert = new SimpleJdbcInsert(template);
		insert.withTableName("reservaties");
	}

	@Override
	public void create(Reservatie reservatie) {
		Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("klantid", reservatie.getKlantId());
		kolomWaarden.put("filmid", reservatie.getFilmId());
		kolomWaarden.put("reservatie", reservatie.getReservatie());
		if (insert.execute(kolomWaarden) == 0) {
			throw new ReservatieNietGeluktException(reservatie.getFilmId());
		}
	}
}
