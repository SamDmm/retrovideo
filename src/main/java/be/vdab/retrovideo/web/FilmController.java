package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.FilmService;

@Controller
@RequestMapping("film")
class FilmController {
	private final FilmService filmService;
	
	FilmController(FilmService filmService) {
		this.filmService = filmService;
	}
	
	private static final String FILM_VIEW = "film";
	@GetMapping("{filmId}")
	ModelAndView toonFilmGegevens(@PathVariable long filmId) {
		ModelAndView modelAndView = new ModelAndView(FILM_VIEW);
		filmService.read(filmId).ifPresent((film) -> modelAndView.addObject("film", film));
		return modelAndView;
	}
}
