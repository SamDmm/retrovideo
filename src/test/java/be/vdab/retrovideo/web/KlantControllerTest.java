package be.vdab.retrovideo.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.services.KlantenService;

public class KlantControllerTest {
	private KlantController controller;
	@Mock
	private KlantenService dummyKlantenService;
	@Before
	public void before() {
		controller = new KlantController(dummyKlantenService);
	}
	@Test
	public void klantWerktSamenMetDeJspKlant() {
		ModelAndView modelAndView = controller.klant();
		assertEquals("klant", modelAndView.getViewName());
	}
}
