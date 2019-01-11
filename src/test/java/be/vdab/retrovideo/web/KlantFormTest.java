package be.vdab.retrovideo.web;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class KlantFormTest {
	private Validator validator;
	@Before
	public void before() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	@Test
	public void familieNaamDeelOk() {
		assertTrue(validator.validateValue(KlantForm.class, "familieNaamDeel", "deelVanEenFamilienaam").isEmpty());
	}
	@Test
	public void familieNaamDeelMoetIngevuldZijn() {
		assertFalse(validator.validateValue(KlantForm.class, "familieNaamDeel", "").isEmpty());
	}
	@Test
	public void familieNaamDeelMagNietNullZijn() {
		assertFalse(validator.validateValue(KlantForm.class, "familieNaamDeel", null).isEmpty());
	}
	@Test
	public void familieNaamDeelMagNietEnkelSpatiesBevatten() {
		assertFalse(validator.validateValue(KlantForm.class, "familieNaamDeel", "  ").isEmpty());
	}
}
