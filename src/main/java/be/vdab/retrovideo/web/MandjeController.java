package be.vdab.retrovideo.web;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Film;
import be.vdab.retrovideo.exceptions.ReservatieNietGeluktException;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.KlantenService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.valueobjects.Prijs;

@Controller
@RequestMapping("mandje")
class MandjeController {
	private final FilmService filmService;
	private final KlantenService klantenService;
	private final ReservatieService reservatieService;
	private final Mandje mandje;
	
	MandjeController(FilmService filmService, KlantenService klantenService, ReservatieService reservatieService, Mandje mandje) {
		this.filmService = filmService;
		this.klantenService = klantenService;
		this.reservatieService = reservatieService;
		this.mandje = mandje;
	}
	
	private static final String MANDJE_VIEW = "mandje";
	@GetMapping
	ModelAndView toonMandje() {
		ModelAndView modelAndView = new ModelAndView(MANDJE_VIEW);
		Set<Film> films = maakFilmsVanFilmIds(mandje.getFilmIds());
		modelAndView.addObject("films", films);
		BigDecimal prijsTotaal = films.stream().map(film -> film.getPrijs()).reduce(BigDecimal.ZERO, (vorigeSom, getal) -> vorigeSom.add(getal));
		modelAndView.addObject("prijsTotaal", new Prijs(prijsTotaal));
		return modelAndView;
	}
	
	private final Set<Film> maakFilmsVanFilmIds(Set<Long> filmIds) {
		Set<Film> films = new HashSet<>();
		for (long filmId : filmIds) {
			filmService.read(filmId).ifPresent(film -> films.add(film));
		}
		return films;
	}
	
	private static final String REDIRECT_MANDJE_VIEW = "redirect:/mandje";
	@PostMapping("/{id}")
	ModelAndView toonMandjeNaToevoegen(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(REDIRECT_MANDJE_VIEW);
		Optional<Film> filmOptional = filmService.read(id);
		if (filmOptional.isPresent() && filmOptional.get().getGereserveerd() < filmOptional.get().getVoorraad()) {
			mandje.addFilmId(id);
		}
		return modelAndView;
	}
	@PostMapping("verwijderen")
	ModelAndView deleteFilm(long[] verwijderFilmId) {
		if (verwijderFilmId != null) {
			for (long filmId : verwijderFilmId) {
			 mandje.deleteFilmId(filmId);
			}
		}
		return new ModelAndView(REDIRECT_MANDJE_VIEW);
	}
	private static final String BESTELLING_BEVESTIGEN_VIEW = "bestellingbevestigen";
	@GetMapping("{klantId}")
	ModelAndView bestellingBevestigen(@PathVariable long klantId) {
		ModelAndView modelAndView = new ModelAndView(BESTELLING_BEVESTIGEN_VIEW);
		modelAndView.addObject("aantalFilmsInMandje", mandje.getFilmIds().size());
		modelAndView.addObject("klant", klantenService.read(klantId).get());
		return modelAndView;
	}
	private static final String RESERVATIE_RAPPORT_VIEW = "reservatierapport";
	@PostMapping("/bevestigen/{klantId}")
	ModelAndView reservatieSchrijvenNaarDataBase(@PathVariable long klantId) {
		ModelAndView modelAndView = new ModelAndView(RESERVATIE_RAPPORT_VIEW);
		Set<Long> mislukteReservatieIds = new HashSet<>();
		for (Long filmId : mandje.getFilmIds()) {
			try {
				reservatieService.doeReservatie(klantId, filmId);
			} catch (ReservatieNietGeluktException ex) {
				mislukteReservatieIds.add(ex.getFilmId());
			}
		}
		
		mandje.maakLeeg();
		if (mislukteReservatieIds.isEmpty()) {
			return modelAndView;
		} else {
			Set<Film> filmsMislukteReservaties = maakFilmsVanFilmIds(mislukteReservatieIds);
			return modelAndView.addObject("filmsMislukteReservaties", filmsMislukteReservaties);
		}
	}
}
