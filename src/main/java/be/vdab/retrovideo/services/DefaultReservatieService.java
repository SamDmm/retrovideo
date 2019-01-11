package be.vdab.retrovideo.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.retrovideo.repositories.FilmRepository;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import be.vdab.retrovideo.valueobjects.Reservatie;

@Service
class DefaultReservatieService implements ReservatieService {
	private final ReservatieRepository reservatieRepository;
	private final FilmRepository filmRepository;
	
	public DefaultReservatieService(ReservatieRepository reservatieRepository, FilmRepository filmRepository) {
		this.reservatieRepository = reservatieRepository;
		this.filmRepository = filmRepository;
	}
	
	@Transactional (propagation = Propagation.REQUIRES_NEW, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void doeReservatie(long klantId, long filmId) {
		filmRepository.updateFilmVoorraad(filmId);
		reservatieRepository.create(new Reservatie(klantId, filmId, LocalDateTime.now()));
	}
}