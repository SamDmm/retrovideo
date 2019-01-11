package be.vdab.retrovideo.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
	private IndexController controller;
	@Mock
	private GenreService dummyGenreService;
	private FilmService dummyFilmService;
	@Before
	public void before() {
//		List<Film> filmList= new ArrayList<>();
//		filmList.add(new Film(1, 1, "titel", 1, 0, BigDecimal.TEN));
//		when(dummyFilmService.findByGenre(1)).thenReturn(filmList);
		controller = new IndexController(dummyGenreService, dummyFilmService);
	}
	@Test
	public void StartPaginaWerktSamenMetDeJspIndex() {
		ModelAndView modelAndView = controller.startPagina();
		assertEquals("index", modelAndView.getViewName());
	}
	@Test
	public void StartPaginaGeeftGenresDoor() {
		ModelAndView modelAndView = controller.startPagina();
		assertTrue(modelAndView.getModel().get("genres") instanceof List);
	}
//	@Test
//	public void StartPaginaMetIdWerktSamenMetDeJspIndex() {
//		ModelAndView modelAndView = controller.startPagina(1);
//		assertEquals("index", modelAndView.getViewName());
//	}
//	@Test
//	public void StartPaginaMetIdGeeftGenresDoor() {
//		ModelAndView modelAndView = controller.startPagina(1);
//		assertTrue(modelAndView.getModel().get("genres") instanceof List);
//	}
//	@Test
//	public void StartPaginaMetIdGeeftFilmsVanGenresDoor() {
//		ModelAndView modelAndView = controller.startPagina(1);
//		assertTrue(modelAndView.getModel().get("filmsVanGenre") instanceof List);
//	}
	
}
