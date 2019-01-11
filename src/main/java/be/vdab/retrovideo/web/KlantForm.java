package be.vdab.retrovideo.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

class KlantForm {
	@NotEmpty
	@NotBlank
	private String familieNaamDeel;

	public String getFamilieNaamDeel() {
		return familieNaamDeel;
	}
	public void setFamilieNaamDeel(String familieNaamDeel) {
		this.familieNaamDeel = familieNaamDeel;
	}
}
