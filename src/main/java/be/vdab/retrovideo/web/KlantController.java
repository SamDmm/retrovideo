package be.vdab.retrovideo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.retrovideo.entities.Klant;
import be.vdab.retrovideo.services.KlantenService;

@Controller
@RequestMapping("klant")
class KlantController {
	private final KlantenService klantenService;
	public KlantController(KlantenService klantenService) {
		this.klantenService = klantenService;
	}
	private static final String KLANT_VIEW = "klant";
	@GetMapping
	ModelAndView klant() {
		ModelAndView modelAndView = new ModelAndView(KLANT_VIEW);
		modelAndView.addObject(new KlantForm());
		return modelAndView;
	}
	@GetMapping(params = "familieNaamDeel")
	ModelAndView toonKlanten(@Valid KlantForm klantForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(KLANT_VIEW);
		if (bindingResult.hasErrors()) {
			return modelAndView;
		}
		List<Klant> klanten = klantenService.findByFamilieNaamDeel(klantForm.getFamilieNaamDeel());
		if (klanten.isEmpty()) {
			bindingResult.reject("geenKlanten");
		} else {
			modelAndView.addObject("klanten", klanten);
		}
		return modelAndView;
	}
}
