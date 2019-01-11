package be.vdab.retrovideo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;

@Controller
@RequestMapping("/")
class IndexController {
	private final GenreService genreService;
	private final FilmService filmService;
	
	IndexController(GenreService genreService, FilmService filmService) {
		this.genreService = genreService;
		this.filmService = filmService;
	}
	
	private static final String INDEX_VIEW = "index";
	@GetMapping
	ModelAndView startPagina() {
		return new ModelAndView(INDEX_VIEW, "genres", genreService.findAll());
	}
	@GetMapping("{id}")
	ModelAndView startPagina(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(INDEX_VIEW);
		modelAndView.addObject("genres", genreService.findAll());
		modelAndView.addObject("filmsVanGenre", filmService.findByGenre(id));
		return modelAndView;
	}
}
